package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class Server {
	 private static final int PORT = 1500;
     private Socket newSocket;
	 private ServerSocket serverSocket;
	 private Connection connection;
	 ExecutorService executor = Executors.newCachedThreadPool();
	//con newFixedThreadPool(i) posso scegliere quanti thread creare
	//classe executor per gestire più tread senza dover creare
	//continuamente ogni singolo thread
	 private List<Connection> connections = new ArrayList<Connection>();
	 private Map<String, Connection> waitingConnection = new HashMap<>(4);
	 private int number = 1;
	 
	 public Server() throws IOException {
		 this.serverSocket = new ServerSocket(PORT);
	 }
		

	 public void startServer() throws SocketException{/*crea la server socket e si mette in ascolto pronto a ricevere
														le connessioni. Man mano che un client si connette viene messo 
														nell'arraylist connections*/ 		 
		 System.out.println("[SERVER]  Ready on Port: "+PORT);		 
		 while(true){ 		 			
					try{
					newSocket = serverSocket.accept(); //crea una connessione tra server e client				
					InetAddress infoclient = newSocket.getInetAddress();  //ritorna l'indirizzo dal quale il client si connette al socket
					String client = infoclient.getHostAddress();    //ritorna l'indirizzo IP del client 		
					System.out.println("[SERVER]  Connessione ricevuta dal client: " + client);		
					connection = new Connection(newSocket, this);
					addConnection(connection);
					executor.submit(connection);//fa partire Connection
					}catch (IOException e){
						System.out.println("Errore nella connessione");
					}
		}			 	
	}
	 
	/*aggiunge all'arrayList le connessioni attive*/
	 private synchronized void addConnection(Connection c){
		connections.add(c);
		System.out.println("[SERVER]  Aggiunto il client alle connessioni");//solo come prova
	}
	 
	 public synchronized void removeConnection(Connection c){
		 connections.remove(c);			
	 }
	 
	 //permette di gestire più partite contemporaneamente
	 public synchronized void match(Connection c, String name) throws IOException{					 	
		 	waitingConnection.put(name, c);	
			System.out.println("[SERVER]  Aggiunto il giocatore alla coda di attesa");									
			if(waitingConnection.size() ==  2){				
				System.out.println("[SERVER]  2 giocatori raggiunti, il gioco ha inizio");				
				Game game = new Game(waitingConnection);
				executor.submit(game);
				waitingConnection.clear();
				System.out.println("[SERVER]  Creata la partita numero: " + number);
				number++;
			}						
	 }
	 
	 //MAIN
	 public static void main(String[] args){
			Server server;
			try{
				server = new Server();
				server.startServer();
			}catch (IOException e){
				System.err.println("Server not ready, "+e.getMessage() + "!");
			}						
		}
						
}



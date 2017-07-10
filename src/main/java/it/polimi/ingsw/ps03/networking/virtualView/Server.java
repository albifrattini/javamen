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
import it.polimi.ingsw.ps03.billboard_pack.Timer;




public class Server {
	 private static final int PORT = 1500;
     private Socket newSocket;
	 private ServerSocket serverSocket;
	 private Connection connection;
	 ExecutorService executor = Executors.newCachedThreadPool();
	 private List<Connection> connections = new ArrayList<Connection>();
	 private Map<String, Connection> waitingConnection = new HashMap<>(4);
	 private int number = 1;
	 private boolean active = true;
	 
	 public Server() throws IOException {
		 this.serverSocket = new ServerSocket(PORT);
	 }
		

	 public void startServer() throws SocketException{/*crea la server socket e si mette in ascolto pronto a ricevere
														le connessioni. Man mano che un client si connette viene messo 
														nell'arraylist connections*/ 		 
		 System.out.println("[SERVER]  Ready on Port: "+PORT);		 
		 	while(isActive()){ 		 			
					try{
						newSocket = serverSocket.accept(); //crea una connessione tra server e client				
						InetAddress infoclient = newSocket.getInetAddress();  //ritorna l'indirizzo dal quale il client si connette al socket
						String client = infoclient.getHostAddress();    //ritorna l'indirizzo IP del client 		
						System.out.println("[SERVER]  Connessione ricevuta dal client: " + client);		
						connection = new Connection(newSocket, this);
						addConnection(connection);
						executor.submit(connection);//fa partire Connection									
					}catch (IOException e){
						System.out.println("[SERVER] Errore nella connessione");
					}
		 	}	
					close();
	}
	 
	/*aggiunge all'arrayList le connessioni attive*/
	 private synchronized void addConnection(Connection c){
		connections.add(c);
		System.out.println("[SERVER]  Aggiunto il client alle connessioni");//solo come prova
	}
	 
	 public synchronized void removeConnection(Connection c){
		 connections.remove(c);			
	 }
	 
	 public boolean isActive(){
		 return active;
	 }
	 
	 public void close(){
		 active = false;
	 }
	 

	 public void waitTimer(){
		 Timer timer = new Timer(15);
		 timer.startTimer();
		 while(timer.stillRunning()){
			 try {
				Thread.sleep(1000);
				if(waitingConnection.size() == 4){
					timer.stopTimer();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		 match(waitingConnection);		 
	 }
	 
	 public void waitingRoom(Connection c, String name) throws IOException{
		 		waitingConnection.put(name, c);	
		 		System.out.println("[SERVER]  Aggiunto il giocatore alla coda di attesa");		
		 		if(waitingConnection.size() == 2){
		 			waitTimer();
		 		}
	 }	
	 
	 public void match(Map<String, Connection> waitingConnection){
		 	Game game = new Game(waitingConnection);
		 	executor.submit(game);
		 	waitingConnection.clear();
			System.out.println("[SERVER]  Creata la partita numero: " + number);
			number++;
	 }
	 
	 //MAIN
	 public static void main(String[] args){
			Server server;
			try{
				server = new Server();
				server.startServer();
			}catch (IOException e){
				System.err.println("[SERVER] Server not ready, "+e.getMessage() + "!");
			}						
		}
						
}



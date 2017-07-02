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
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCards;
import it.polimi.ingsw.ps03.networking.controller.Controller;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.players.PlayerColor;




public class Server {
	 private Server server;
	 private int number = 1;
	 private static final int PORT = 1500;
     private Socket newSocket;
	 private ServerSocket serverSocket;
	 private Connection connection;
	 ExecutorService executor = Executors.newCachedThreadPool();//con newFixedThreadPool(i) posso scegliere quanti thread creare
		 														//classe executor per gestire più tread senza dover creare
	 															//continuamente ogni singolo thread
	 private List<Connection> connections = new ArrayList<Connection>();
	 
	 private List<String> playersName = new ArrayList<String>();
	 
	 private Map<String, Connection> waitingConnection = new HashMap<>(4);//inserisco fino a 4 giocatori in coda, che attendono di giocare
		
	 
	 public Server() throws IOException {
		 this.serverSocket = new ServerSocket(PORT);
	 }
		

	 public void startServer() throws SocketException{/*crea la server socket e si mette in ascolto pronto a ricevere
														le connessioni. Man mano che un client si connette viene messo 
														nell'arraylist connections*/ 
		 

		 	System.out.println("Server Ready on Port: "+PORT);
		 
		 while(true){ 		 
			
				 try{
					 newSocket = serverSocket.accept(); //crea una connessione tra server e client
				
					 InetAddress infoclient = newSocket.getInetAddress();  //ritorna l'indirizzo dal quale il client si connette al socket
					 String client = infoclient.getHostAddress();    //ritorna l'indirizzo IP del client 
		
					 System.out.println("Connessione ricevuta dal client: "+ client);
			
					 connection = new Connection(newSocket, this);
					 addConnection(connection);
					 executor.submit(connection);//fa partire Connection
			}catch (IOException e){
				System.out.println("Errore nella connessione");
			}
			 }
			 	
		}
		
	 
	

	 private synchronized void addPlayersName(String name){
		 playersName.add(name);
				System.out.println("aggiunto " + name + " alla partita");//solo come prova
		}

	/*aggiunge all'arrayList le connessioni attive*/
	 private synchronized void addConnection(Connection c){
			connections.add(c);
				System.out.println("aggiunto il client alle connessioni");//solo come prova
		}
	 
	 public synchronized void removeConnection(Connection c){
		 connections.remove(c);			
	 }
	 
	 
	 public synchronized void match(Connection c, String name) throws IOException{
			
		 	number = 1;		 	
		 	waitingConnection.put(name, c);	
			System.out.println("aggiunto il giocatore alla coda di attesa");			
			addPlayersName(name);						
			if(waitingConnection.size() ==  2){				
				System.out.println("2 giocatori raggiunti, il gioco sta per iniziare");				
				setGame();				
							}				
				number++;				
				}
	  
	 
	 
	 public void setGame() throws IOException{
		 
		 		List<RemoteView> players = new ArrayList<RemoteView>(2);
		 		Connection c = new Connection(newSocket, server);				
		 		Billboard model = new Billboard();	 			
				DevelopmentCards developmentCards = new DevelopmentCards();
			 	developmentCards.build();
			 	
		 
			 	for(int i = 0; i < waitingConnection.size(); i++){//aggiunge i giocatori alla partita
			 				
			 					PlayerColor [] colors = PlayerColor.values();		 		
		 				 		List<String> keys = new ArrayList<>(waitingConnection.keySet());
		 				 		//Returns a Set view of the keys contained in this map.
		 				 		c = waitingConnection.get(keys.get(i));
		 				 		RemoteView player = new RemoteView(new Player(keys.get(i) ,colors[i] , 5+i ) , c);
		 				 		players.add(player);
		 				 		model.addPlayerRemote(i, keys.get(i), colors[i], 5+i);
		 				 		System.out.println("giocatore: " + playersName.get(i).toString() + " Colore: " + colors[i].toString());
		 				 		Controller controller = new Controller(model, player);
		 				 		model.addObserver(controller);
		 				 		player.addObserver(controller);
		 				 		controller.addObserver(player);
		 		 			 	}
	 	
			 	System.out.println("Partita numero " + number + " creata");
			 	System.out.println("I giocatori sono: ");
		 	
			 	for(int j = 0; j < waitingConnection.size(); j++){
		 		
			 					System.out.println(playersName.get(j).toString() + " ");
		 	
		 	
			 					}
			 	model.getTable().buildTable(2);
			 	model.getTurnOfPlay().setNumberOfPlayers(model.getPlayers().size());
		 	
			 	for(int y = 0; y < players.size(); y++){//invia a tutti il model
		 		
			 					players.get(y).showModel(model);
		 		
			 					}
		 		
			 	waitingConnection.clear();			
			 	System.out.println("Lista di attesa pulita");
			
			
		 
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



package it.polimi.ingsw.ps03.networking.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

	
	 private static final int port = 1500;
	 private ServerSocket serverSocket;
	 ExecutorService executor = Executors.newCachedThreadPool();//con newFixedThreadPool(i) posso scegliere quanti thread creare
		 														//classe executor per gestire più tread senza dover creare
	 															//continuamente ogni singolo thread
	 private List<Connection> connections = new ArrayList<Connection>();
	 private Map<String, Connection> waitingConnection = new HashMap<>();
		
	 private Map<Connection, Connection> playingConnection = new HashMap<>();
	 
	 public Server() throws IOException {
		 this.serverSocket = new ServerSocket(port);
	 }
		
	
	 public void startServer(){/*crea la server socket e si mette in ascolto pronto a ricevere
								le connessioni, man mano che un client si connette viene messo 
								nell'arraylist connections*/ 
		 System.out.println("Server Ready on Port: "+port);
		 while(true){
			try{
				Socket newSocket = serverSocket.accept(); //crea una connessione tra server e client
				Connection connection = new Connection(newSocket, this);
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
		}
	 
	 public synchronized void removeConnection(Connection c){
		 connections.remove(c);
//		 Connection player = playingConnection.get(c);
//			if(enemy != null)
//			player.closeConnection();
//			playingConnection.remove(c);
//			playingConnection.remove(player);
//			Iterator<String> iterator = waitingConnection.keySet().iterator();
//			while(iterator.hasNext()){
//				if(waitingConnection.get(iterator.next())==c){
//					iterator.remove();
//				}
//			}
			
	 }
	 
//	 public synchronized void rendezvous(Connection c, String name){
//			waitingConnection.put(name, c);
//			if(waitingConnection.size() == 2){
//				List<String> keys = new ArrayList<>(waitingConnection.keySet());
//				Connection c1 = waitingConnection.get(keys.get(0));
//				Connection c2 = waitingConnection.get(keys.get(1));
//				RemoteView player1 = new RemoteView(new Player(keys.get(0)), keys.get(1), c1);
//				RemoteView player2 = new RemoteView(new Player(keys.get(1)), keys.get(0), c2);
//				Model model = new Model();
//				Controller controller = new Controller(model);
//				model.addObserver(player1);
//				model.addObserver(player2);
//				player1.addObserver(controller);
//				player2.addObserver(controller);			
//				playingConnection.put(c1, c2);
//				playingConnection.put(c2, c1);
//				waitingConnection.clear();
//			}
//		}
	 
//	 public boolean isValid(int number){
//		 final boolean okay = false;
//		 while(number < 4){
//			 number++;
//		 }
//		 return okay;
//	 }
	 
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

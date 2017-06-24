package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.ps03.networking.controller.Controller;
import it.polimi.ingsw.ps03.networking.model.Model;
import it.polimi.ingsw.ps03.networking.model.Player;


public class Server {

	
	 private static final int port = 1500;
	 private ServerSocket serverSocket;
	 ExecutorService executor = Executors.newCachedThreadPool();//con newFixedThreadPool(i) posso scegliere quanti thread creare
		 														//classe executor per gestire più tread senza dover creare
	 															//continuamente ogni singolo thread
	 private List<Connection> connections = new ArrayList<Connection>();
	 private Map<String, Connection> waitingConnection = new HashMap<>();//inserisco i giocatori in coda, che attendono di giocare
		
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
	 
	 public synchronized void match(Connection c, String name){
			waitingConnection.put(name, c);
			if(waitingConnection.size() == 2){
				List<String> keys = new ArrayList<>(waitingConnection.keySet());
				Connection c1 = waitingConnection.get(keys.get(0));
				Connection c2 = waitingConnection.get(keys.get(1));
				RemoteView player1 = new RemoteView(new Player(keys.get(0)),/* keys.get(1),*/ c1);
				RemoteView player2 = new RemoteView(new Player(keys.get(1)),/* keys.get(0),*/ c2);
				Model model = new Model();
				Controller controller = new Controller(model);
				model.addObserver(player1);
				model.addObserver(player2);
				player1.addObserver(controller);
				player2.addObserver(controller);			
				playingConnection.put(c1, c2);
				playingConnection.put(c2, c1);
				try{
					serverSocket.setSoTimeout(20000);
					if(waitingConnection.size() == 3){
						Connection c3 = waitingConnection.get(keys.get(2));
						RemoteView player3 = new RemoteView(new Player(keys.get(2))/*, keys.get(1)*/,c3);
						model.addObserver(player3);
						player3.addObserver(controller);
						playingConnection.put(c2, c3);
						playingConnection.put(c3, c1);
						
							try{
								serverSocket.setSoTimeout(20000);
									if(waitingConnection.size() == 4){
										Connection c4 = waitingConnection.get(keys.get(3));
										RemoteView player4 = new RemoteView(new Player(keys.get(3)),/* keys.get(0),*/c4);
										model.addObserver(player4);
										player4.addObserver(controller);										
										playingConnection.put(c3, c4);
										playingConnection.put(c4, c1);
									}
							}catch(IOException e){
								System.out.println("no other player found");
								waitingConnection.clear();
							}
						
					}
				}catch(IOException e){
					System.out.println("no other player found");
					waitingConnection.clear();
				}
			
			
				waitingConnection.clear();
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

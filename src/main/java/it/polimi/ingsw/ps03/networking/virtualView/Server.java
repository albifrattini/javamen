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
		

	 public void startServer() throws SocketException{	 
		 System.out.println("[SERVER]  Ready on Port: "+PORT);		 
		 	while(isActive()){ 		 			
					try{
						newSocket = serverSocket.accept(); 		
						InetAddress infoclient = newSocket.getInetAddress();  
						String client = infoclient.getHostAddress(); 		
						System.out.println("[SERVER]  Connessione ricevuta dal client: " + client);		
						connection = new Connection(newSocket, this);
						addConnection(connection);
						executor.submit(connection);							
					}catch (IOException e){
						System.out.println("[SERVER] Errore nella connessione");
					}
		 	}	
					close();
	}
	 

	 private synchronized void addConnection(Connection c){
		connections.add(c);
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
		 Timer timer = new Timer(20);
		 timer.startTimer();
		 while(timer.stillRunning()){
			 try {
				Thread.sleep(1000);
				if(waitingConnection.size() == 4){
					timer.stopTimer();
				}
			} catch (InterruptedException e) {
				System.out.println("[WARNING]  Errore in waitTimer!");
				Thread.currentThread().interrupt();
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



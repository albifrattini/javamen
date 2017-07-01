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
import it.polimi.ingsw.ps03.networking.model.Player;
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
		//1)
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
	//6)
			System.out.println("aggiunto " + name + " alla partita");//solo come prova
		}

	/*aggiunge all'arrayList le connessioni attive*/
	 private synchronized void addConnection(Connection c){
			connections.add(c);
	//2)
			System.out.println("aggiunto il client alle connessioni");//solo come prova
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
	 
	 
	 public synchronized void match(Connection c, String name) throws IOException{
			
		 	number = 1;
		 	
		 	waitingConnection.put(name, c);
		//5)	
			System.out.println("aggiunto il giocatore alla coda di attesa");
			
			addPlayersName(name);			
			
			if(waitingConnection.size() ==  2){
				
				System.out.println("2 giocatori raggiunti, il gioco sta per iniziare");
				
				setGame();
				
							}
				
				number++;
				
				}
	  
	 
	 
	 public synchronized void setGame() throws IOException{
		 
		List<RemoteView> players = new ArrayList<RemoteView>(2);
		 
		Connection c = new Connection(newSocket, server);
		
		DevelopmentCards developmentCards;
		
		List<String> keys = null;
		 
	 	Billboard model = new Billboard();
	 	
		Controller controller = new Controller(model);
		 
		 	for(int i = 0; i < waitingConnection.size(); i++){//aggiunge i giocatori alla partita
		 		
		 		PlayerColor [] colors = PlayerColor.values();		 		
		 		
		 		keys = new ArrayList<>(waitingConnection.keySet());
		 		//Returns a Set view of the keys contained in this map.
		 		
		 		 c = waitingConnection.get(keys.get(i));
		 		
		 		RemoteView player = new RemoteView(new Player(keys.get(i) ,colors[i] , 5+i ) , c);
		 		
		 		players.add(player);
//7)		 		
		 		System.out.println("giocatore: " + playersName.get(i).toString() + " Colore: " + colors[i].toString());
		 				 		
		 		model.addObserver(controller);
		 		
		 		player.addObserver(controller);
		 		
		 		controller.addObserver(player);
		 
		 		
		 	}
//10)	 	
		 	System.out.println("Partita numero " + number + " creata");
		 	
		 	System.out.println("I giocatori sono: ");
		 	
		 	for(int j = 0; j < waitingConnection.size(); j++){
		 		
		 	System.out.println(playersName.get(j).toString() + " ");
		 	
		 	
		 	}
		 	
		 	developmentCards = new DevelopmentCards();
			
		 	developmentCards.build();
			
		 	model.getTable().buildTable(2);
		 	
		 	for(int y = 0; y < players.size(); y++){
		 		
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

//InetAddress infoclient = socket.getInetAddress();  //ritorna l'indirizzo dal quale il client si connette al socket
//String client = infoclient.getHostAddress();    //ritorna l'indirizzo IP del client   

//if(waitingConnection.size() == 2){
//
//List<String> keys = new ArrayList<>(waitingConnection.keySet());
//Connection c1 = waitingConnection.get(keys.get(0));
//Connection c2 = waitingConnection.get(keys.get(1));
//RemoteView player1 = new RemoteView(new Player(keys.get(0),PlayerColor.BLUE, 5),/* keys.get(1),*/ c1);
//RemoteView player2 = new RemoteView(new Player(keys.get(1),PlayerColor.RED, 6),/* keys.get(0),*/ c2);
//Billboard model = new Billboard();
//Controller controller = new Controller(model);//da cambiare
//model.addObserver(player1);//il player osserva il model, la billboard nella v.v 
//model.addObserver(player2);//e viene osservato dal controller che riceve i messaggi attraverso la remote view
//player1.addObserver(controller);
//player2.addObserver(controller);			
////playingConnection.put(c1, c2);
////playingConnection.put(c2, c1);
////model.addPlayer(0, PlayerColor.BLUE, 5);
////model.addPlayer(1, PlayerColor.RED, 6);
//try{
//	serverSocket.setSoTimeout(20000);
//	if(waitingConnection.size() == 3){
//		Connection c3 = waitingConnection.get(keys.get(2));
//		RemoteView player3 = new RemoteView(new Player(keys.get(2),PlayerColor.GREEN, 7)/*, keys.get(1)*/,c3);
//		model.addObserver(player3);
//		player3.addObserver(controller);
////		playingConnection.put(c2, c3);
////		playingConnection.put(c3, c1);
////		model.addPlayer(2, PlayerColor.GREEN, 7);
//		
//		
//			try{
//				serverSocket.setSoTimeout(20000);
//					if(waitingConnection.size() == 4){
//						Connection c4 = waitingConnection.get(keys.get(3));
//						RemoteView player4 = new RemoteView(new Player(keys.get(3), PlayerColor.YELLOW, 8),/* keys.get(0),*/c4);
//						model.addObserver(player4);
//						player4.addObserver(controller);										
////						playingConnection.put(c3, c4);
////						playingConnection.put(c4, c1);
////						model.addPlayer(3, PlayerColor.YELLOW, 8);
//					}
//			}catch(IOException e){
//				System.out.println("no other player found");
//				waitingConnection.clear();
//			}
//		
//	}
//}catch(IOException e){
//	System.out.println("no other player found");
//	waitingConnection.clear();
//}
//
//
//waitingConnection.clear();
//}
//}
//
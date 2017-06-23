package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.mvc.MainMVC;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.players.PlayerColor;

	//Main alla fine
	public class Server extends Observable implements Observer{
	 
	 	 private int port;
	 	 private int number = 0;
	 	 private List<PlayerColor> players;
//	 	 private PlayerColor color;
	 	 
	 	 public Server (int port)
	 	 {
	 	  this.port = port;
	 	  players = new ArrayList<PlayerColor>(4);
		 }
	 	
@Override
		public void update(Observable BillboardView, Object arg) {
			
			
		}

	
	 	 
	 	 public void startServer() {
	 		 ExecutorService executor = Executors.newCachedThreadPool();//con newFixedThreadPool(i) posso scegliere quanti thread creare
	 		 //classe executor per gestire più tread senza dover creare continuamente ogni singolo thread
	 		 ServerSocket serverSocket;
	 		// List<PlayerColor> players = new ArrayList<PlayerColor>(4);//lista dove inserire i giocatori che si connettono
	 		 
	 		
	 		
	 		 try{
	 			 serverSocket = new ServerSocket(port);//crea il server socket che starà in ascolto sulla porta
	 		 }catch(IOException e){
	 			 System.err.println(e.getMessage());//porta non disponibile
	 		 return;
			 }
	 		 
	 	  
	 		 System.out.println("Server Ready on Port: "+port);
	 		 
	 		 while(true && number <4){
	 			
	 			 try{
	  				 
	 			//	 MainMVC main = new MainMVC();
	 				 Socket socket = serverSocket.accept(); //crea una connessione tra server e client
	 				 executor.submit( new ServerClientHandler(socket)); //attiva l'handler per la comunicazione tra client e server
	 				
	 				setColor(number);
	 				InetAddress infoclient = socket.getInetAddress();  //ritorna l'indirizzo dal quale il client si connette al socket
	 			    String client = infoclient.getHostAddress();    //ritorna l'indirizzo IP del client   
	 			    
	 			    System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    		+ "e sarà il player " + players.get(number).toString());
	 				
	 			    if(number == 1){
	 			    		serverSocket.setSoTimeout(20000);//fa partire un timer di 20s, se il server non riceve altre connessioni fa partire il gioco
	 			    		
	 			    	    try{
	 			    	    		socket = serverSocket.accept();
	 			    	   		executor.submit( new ServerClientHandler(socket));
	 			    	    	
	 			    	    		number++;
	 			    	    		setColor(number);
	 			    	    		
	 			    	    		
	 			    	    		if(number == 2){
	 			    	    			 System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    	 			    		+ "e sarà il player " + players.get(number).toString());
	 			    	    			serverSocket.setSoTimeout(20000);
	 			    	    				
	 			    	    				try{
	 			    	    					socket = serverSocket.accept();
	 			    	    					executor.submit( new ServerClientHandler(socket));
	 			    	    					number++;
	 			    	    					setColor(number);
	 			    	    					
	 			    	    					
	 			    	    						if(number == 3){
	 			    	    									System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    	    											+ "e sarà il player " + players.get(number).toString());
	 			    	    									
	 			    	    									System.out.println("4 players connected\n"+
	 			    	    											"Game ready to start");
	 			    	    							//		main.startGame();
//	 			    	    								for(int i = 1; i<5; i++){
//			    	    									players.get(1).notify();
//	 			    	    									serverSocket.setSoTimeout(20000);
//	 			    	    								}
	 			    	    							}
	 			    	    						
	 			    	    				}catch(IOException e){
	 			    	    						System.out.println("no other player found");
	 			    	    								break;//da cambiare con fai partire il gioco
	 			    	    						}
	 			    	    		}
	 			    	    		
	 			    	    }catch(IOException e){
	 			    	    	System.out.println("no other player found");

	    	    						break;//da cambiare con fai partire il gioco
	 			    	     
	 			    }
	 			   
	 								
	 			    	  
	 					 
	 			    }
	 			   number++;
	 			 		}catch(IOException e){
	 			 			break;//se il serverSocket venisse chiuso, entrerei qui
	 			 				}	

	 		             }
	 		   executor.shutdown();//waits for currently running tasks to finish
	 		   //shutdownNow() interrupts all running tasks and shut the executor down immediately.
	 		  
	 		   }
	 	
	public List<PlayerColor> setColor(int number){//creare un arrayList in base ai giocatori che si connettono
		if(number == 0){
				players.add(number, PlayerColor.BLUE);
		}
			
			if(number == 1){
					players.add(number, PlayerColor.RED);
																}	
				
					
					
				if(number == 2){
						players.add(number, PlayerColor.YELLOW); 
																}
								
				
						
					if(number == 3){
							players.add(number, PlayerColor.GREEN);
																}
				
		return players;
	}
	
	public List<PlayerColor> getPlayers(){
		return players;
	}
	
	public int getPort(){
		return port;
	}
	
	
	//MAIN
	public static void main(String[] args){
		Server server = new Server(1450);
		server.startServer();
		
	}
	}

	

	
	 // private int backlog;//in teoria dovrebbe stabilire un massimo di connessioni alla volta

	 
	 
	
	 // this.backlog = backlog;
	//		List<String> players = new ArrayList(4); 
	 
	 
	 
			// setSoTimeout(60);//permette di settare un tempo di attesa all'accept
//				 for(int i=0; i<4; i++){
//				
//				 if(i==0){
//					 players.add(i, "Blue");}
//				 if(i==1){
//					 players.add(i, "Red");
//				 }
//				 if(i==2){
//					 players.add(i, "Yellow");
//				 }
//				 if(i==3){
//					 players.add(i, "Green");
//				 }
//				 }
				 
				
//private void setSoTimeout(int i) {

//	public void run(){
//		 		try{
//		 			//leggo e scrivo nella connessione finchè non ricevo quit
//		 			Scanner in = new Scanner(socket.getInputStream());
//		 			PrintWriter out = new PrintWriter(socket.getOutputStream());
//		 			
//		 			while(true){
//		 				String line = in.nextLine();
//		 				if(line.equals("quit")){
//		 					break;
//		 				}
//		 				else{
//		 					out.println("Received:" +line);
//		 					out.flush();
//		 				}
//		 			}
//		 			//chiudo tutto
//		 			in.close();
//		 			out.close();
//		 			socket.close();
//		 		}catch(IOException e){
//		 			System.err.println(e.getMessage());
//		 		}
//		 	}
//		 }
	
	
	//Create Gson Builder
//    mGsonBuilder = new GsonBuilder();fallo e basta sia nel client che server
//mGson = mGsonBuilder.create();       fallo e basta sia nel client che server
//mGson.toJson(mPlayers.get(id), Player.class)
//mGson.fromJson(waitForMessage(), Player.class))+


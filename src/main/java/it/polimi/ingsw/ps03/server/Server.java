package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.ArrayList;
//import java.util.List;
import java.io.*;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.mvc.MainMVC;
	//Main alla fine
	public class Server {
	 
	 	 private int port;
	 	 private int number = 1;
	 	 private List<String> players;
	 	 private String color;
	 	 
	 	 public Server (int port)
	 	 {
	 	  this.port = port;
	 	
		 }
	 	 
	 	 
	 	 public void startServer() {
	 		 ExecutorService executor = Executors.newCachedThreadPool();
	 		 ServerSocket serverSocket;
	 		 List<String> players = new ArrayList(4);
	 		 
	 		 try{
	 			 serverSocket = new ServerSocket(port);
	 		 }catch(IOException e){
	 			 System.err.println(e.getMessage());//porta non disponibile
	 		 return;
			 }
	 		 
	 	  
	 		 System.out.println("Server Ready on Port: "+port);
	 		 
	 		 while(true && number <5){
	 			
	 			 try{
	 				 MainMVC main = new MainMVC();
	 				 Socket socket = serverSocket.accept(); 
	 				 executor.submit( new ServerClientHandler(socket));
	 				
	 				setColor(number);
	 				players.add(number-1,color);
	 				InetAddress infoclient = socket.getInetAddress();  
	 			    String client = infoclient.getHostAddress();       
	 			    
	 			    System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    		+ "e sarà il player " + color);
	 				
	 			    if(number == 2){
	 			    		serverSocket.setSoTimeout(20000);
	 			    		
	 			    	    try{
	 			    	    		socket = serverSocket.accept();
	 			    	    		executor.submit( new ServerClientHandler(socket));
	 			    	    		number++;
	 			    	    		setColor(number);
	 			    	    		players.add(number-1,color);
	 			    	    		
	 			    	    		if(number == 3){
	 			    	    			 System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    	 			    		+ "e sarà il player " + color);
	 			    	    			serverSocket.setSoTimeout(20000);
	 			    	    				
	 			    	    				try{
	 			    	    					socket = serverSocket.accept();
	 			    	    					executor.submit( new ServerClientHandler(socket));
	 			    	    					number++;
	 			    	    					setColor(number);
	 			    	    					players.add(number-1,color);
	 			    	    					
	 			    	    						if(number == 4){
	 			    	    									System.out.println("Il client " + client + " numero "+ number +  " si è connesso al server\n"
	 			    	    											+ "e sarà il player " + color);
	 			    	    									
	 			    	    									System.out.println("4 players connected\n"+
	 			    	    											"Game ready to start");
	 			    	    									main.startGame();
	 			    	    									
	 			    	    							}
	 			    	    						
	 			    	    				}catch(IOException e){
	 			    	    						System.out.println("no other player found");
	 			    	    						break;
	 			    	    					}
	 			    	    		}
	 			    	    		
	 			    	    }catch(IOException e){
	 			    	    	System.out.println("no other player found");
	 			    	    	break;
	 			    	    }
	 			    	     
	 			    }
	 			   
	 								
	 				number++;
	 					 
	 					
	 					
	 			 		}catch(IOException e){
	 			 			break;//se il serverSocket venisse chiuso, entrerei qui
	 			 				}	

	 		             }
	 		   executor.shutdown();
	 		  
	 		   }
	 	
	public String setColor(int number){
		if(number == 1){
				color = "Blue"; 
		}
		if(number == 2){
				color = "Red"; 
		}
		if(number == 3){
			 	color = "Yellow"; 
		}
		if(number == 4){
				color = "Green"; 
		}
		return color;
	}
	
	//MAIN
	public static void main(String[] args){
		Server server = new Server(1450);
		server.startServer();
		
	}
}

	
	 // private int backlog;//in teoria dovrebbe stabilire un massimo di connessioni alla volta
	// private List<String> players;

	 
	 
	
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

		
	


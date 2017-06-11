package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Server {

	 private int port;

	 public Server (int port)
	 {
	  this.port = port;
	 }
	 
	 public void startServer() {
		 ExecutorService executor = Executors.newCachedThreadPool();
		 ServerSocket serverSocket;
		 
		 try{
			 serverSocket = new ServerSocket(port);
		 }catch(IOException e){
			 System.err.println(e.getMessage());//porta non disponibile
			 return;
		 }
		  
		 System.out.println("Server Ready");
		 
		 while(true){
			 try{
				 Socket socket = serverSocket.accept();
				 executor.submit( new ServerClientHandler(socket));
			 		}catch(IOException e){
			 			break;//se il serverSocket venisse chiuso, entrerei qui
			 				}	
		             }
		   executor.shutdown();
		   }
	
}

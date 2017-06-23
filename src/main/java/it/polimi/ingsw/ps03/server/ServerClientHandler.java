package it.polimi.ingsw.ps03.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class ServerClientHandler implements Runnable{

	private Socket socket;
    protected String color;
    private Server server;
    private int number;
   
	
	public ServerClientHandler(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{

			//leggo e scrivo nella connessione finchè non ricevo quit
			Scanner in = new Scanner(socket.getInputStream());//per leggere i messaggi ricevuti
			PrintWriter out = new PrintWriter(socket.getOutputStream());//per mandare risposte
			
		//  String line = in.nextLine();//riceve il nome del client
		  out.println("Players found, game will start in a while");
		 
		
//			
//			line = in.nextLine();//riceve il colore
//			while(!line.equalsIgnoreCase("red") && !line.equalsIgnoreCase("green") 
//					&& !line.equalsIgnoreCase("yellow")	&& !line.equalsIgnoreCase("blue")){
//			out.println("try again, your choice is not available" );
//			}
//			out.println("Your color will be " + line);
//			out.flush();
			while(true){
				String line = in.nextLine();//legge il messaggio ricevuto
				 
				  
				if(line.equals("quit")){
					break;
				}
				else{
					out.println("Well met " +line +
							  ", looking for other players,please wait..." );
//					 
//					
					  out.flush();
//					 
						}
				
			} 
			
			//chiudo tutto
			in.close();
			out.close();
			socket.close();
		}catch(IOException/*| InterruptedException */e){
			System.err.println(e.getMessage());
		}
	}
		
	}


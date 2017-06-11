package it.polimi.ingsw.ps03.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerClientHandler implements Runnable{

	private Socket socket;
	
	public ServerClientHandler(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			//leggo e scrivo nella connessione finchè non ricevo quit
			Scanner in = new Scanner(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			while(true){
				String line = in.nextLine();
				if(line.equals("quit")){
					break;
				}
				else{
					out.println("Received:" +line);
					out.flush();
				}
			}
			//chiudo tutto
			in.close();
			out.close();
			socket.close();
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
	}
}

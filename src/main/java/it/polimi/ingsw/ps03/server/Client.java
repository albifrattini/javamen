package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

//MAIN ALLA FINE
public class Client {

	private String ip;
	private int port;

	public Client(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	
	public void startClient() throws IOException {//crea la socket del client 
		Socket socket = new Socket(ip , port);
		System.out.println("Connection established\n"
				           +"WELCOME IN LORENZO IL MAGNIFICO\n\n"
				           +"What's your name?"	);
		
		Scanner socketIn = new Scanner(socket.getInputStream());
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
		Scanner stin = new Scanner(System.in);
		
		String inputLine = stin.nextLine();
		socketOut.println(inputLine);
		socketOut.flush();
		String socketLine = socketIn.nextLine();
		System.out.println(socketLine);
		
		//System.out.println("Choose a Color beetween Blue, Red, Yellow and Green");
		
		try{
			while(true){
				inputLine = stin.nextLine();//se da problemi cambia da nextLine a next()
				socketOut.println(inputLine);
				socketOut.flush();
			    socketLine = socketIn.nextLine();
				System.out.println(socketLine);
			}
		}catch(NoSuchElementException e){
			System.out.println("Connection closed");
		}finally{
			stin.close();
			socketIn.close();
			socketOut.close();
			socket.close();
		}
	}

	//MAIN
public static void main(String[] args){
	Client client = new Client("127.0.0.1", 1450);
	
	
	
	try{
		client.startClient();
		
	}catch (IOException e){
		System.err.println(e.getMessage());
					}
													}
}



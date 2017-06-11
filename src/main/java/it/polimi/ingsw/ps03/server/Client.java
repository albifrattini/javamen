package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class Client {

	private String ip;
	private int port;

	public Client(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	
	public void startClient() throws IOException {
		Socket socket = new Socket(ip , port);
		System.out.println("Connection established\n");
		System.out.println("digit quit if you want to exit");
		
		Scanner socketIn = new Scanner(socket.getInputStream());
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
		Scanner stin = new Scanner(System.in);
		
		try{
			while(true){
				String inputLine = stin.nextLine();
				socketOut.println(inputLine);
				socketOut.flush();
				String socketLine = socketIn.nextLine();
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
}
	


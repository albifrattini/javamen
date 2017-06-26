package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

public class Client {//si connette alla porta e attende gli eventi dalla virtual view e li notifica alla local view->BillboardView?
	private String ip;
	private int port;
	
	public Client(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	
	public void startClient() throws IOException {//mettere insieme view e netHan
		
		Socket socket = new Socket(ip , port);//crea la socket del client 		
		Scanner socketIn = new Scanner(socket.getInputStream());//legge gli imput messi dal client
		PrintStream socketOut = new PrintStream(socket.getOutputStream());//manda il messaggio attraverso il canale
		Scanner stin = new Scanner(System.in);	
	//	update(null, "Connection Established");
		System.out.println("Connection Established\n"+
		                    "What's your name?");//da mettere nella view
		
		try{
			while(true){
				String inputLine = stin.nextLine();//se da problemi cambia da nextLine a next()
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

	//MAIN
public static void main(String[] args){
	Client client = new Client("127.0.0.1", 1500);
	
	
	
	try{
		client.startClient();
		
	}catch (IOException e){
		System.err.println(e.getMessage());
					}
													}



public void update(Observable o, Object message) {
	
	
}
}

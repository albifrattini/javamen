package it.polimi.ingsw.ps03.server;

import java.net.*;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.io.*;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.mvc.BillboardView;
import it.polimi.ingsw.ps03.mvc.MainMVC;

//MAIN ALLA FINE
public class Client extends Observable implements Observer{

	private String ip;
	private int port;
	
	public Client(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	
	public void startClient() throws IOException {
		
		Socket socket = new Socket(ip , port);//crea la socket del client 
		
		
		Scanner socketIn = new Scanner(socket.getInputStream());//legge gli imput messi dal client
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());//manda il messaggio attraverso il canale
		Scanner stin = new Scanner(System.in);
		
		System.out.println("Connection established\n"
		           +"WELCOME IN LORENZO IL MAGNIFICO\n\n"
		           +"What's your name?"	);
		
		//System.out.println("Choose a Color beetween Blue, Red, Yellow and Green");
		
		try{
			while(true){
				MainMVC main = new MainMVC();
				String inputLine = stin.nextLine();//se da problemi cambia da nextLine a next()
				socketOut.println(inputLine);
				socketOut.flush();
			    String socketLine = socketIn.nextLine();
				System.out.println(socketLine);
			
//				wait();
				main.startGame();
//				System.out.println("Game ready");
			}
		}catch(NoSuchElementException e){
			System.out.println("Connection closed");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
		}finally{
			stin.close();
			socketIn.close();
			socketOut.close();
			socket.close();
		}
	}

	//MAIN
public static void main(String[] args){
	Client client = new Client("127.0.0.1", 1449);
	
	
	
	try{
		client.startClient();
		
	}catch (IOException e){
		System.err.println(e.getMessage());
					}
													}


@Override
public void update(Observable arg0, Object arg1) {
	
	
}
}



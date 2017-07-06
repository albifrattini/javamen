package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;


import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Observable;


public class Client extends Observable {
	private static String ip;
	private static int port;
	private static ObjectInputStream in;
		
	public Client(String ip,int port){
		Client.ip = ip;
		Client.port = port;		
	}

	public void receiveMessage(Socket socket) throws ClassNotFoundException, IOException{
		try{					
			while (true){		
				Object line = in.readObject();
				setChanged();
				notifyObservers(line);
				}
			}catch(NoSuchElementException | ClassNotFoundException e){
				System.out.println("Connection closed");
			}finally{				
				in.close();
				socket.close();
			}			
	}
		
	//MAIN
public static void main(String[] args) throws UnknownHostException, IOException{
	Client client = new Client("127.0.0.1", 1500);
	Socket socket = new Socket(ip , port);//crea la socket del client 
	NetworkHandler networkHandler = new NetworkHandler(socket);
	LocalView ui = new LocalView(System.in, System.out);
	in = new ObjectInputStream(socket.getInputStream());	
	client.addObserver(ui);
	ui.addObserver(networkHandler);
	ui.initGame();	
		try{
			client.receiveMessage(socket);		
		}catch (IOException | ClassNotFoundException e){
			System.err.println(e.getMessage());
		}
	}

public void update(Observable o, Object message) {		
}

}

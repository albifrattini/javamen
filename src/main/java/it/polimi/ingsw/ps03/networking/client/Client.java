package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Observable;

/**
 * this class represents Client, that receives objects and connects to the server port
 */
public class Client extends Observable {
	private static String ip;
	private static int port;
	private static ObjectInputStream in;
	private boolean active = true;
		
	public Client(String ip, int port){
		Client.ip = ip;
		Client.port = port;		
	}
/**
 * this class receives the object and notifies the observers about the change
 * @param socket Socket
 */
	public void receiveMessage(Socket socket) throws ClassNotFoundException, IOException{
		try{					
			while (isActive()){		
				Object line = in.readObject();
				setChanged();
				notifyObservers(line);
				}
			}catch(NoSuchElementException | ClassNotFoundException e){
				System.out.println("Connection closed");
			}finally{				
				in.close();
				socket.close();
				active = false;
			}			
	}
/**
 * this methos starts the client, establishes the socket's connection between client and server, generates a network handler and a local view
 * @param args
 * @throws UnknownHostException
 * @throws IOException
 */
	//MAIN
public static void main(String[] args) throws UnknownHostException, IOException{
	String localhost = String.valueOf(InetAddress.getLoopbackAddress());
	localhost = localhost.substring(10, localhost.length());
	Client client = new Client(localhost, 1500);
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

public boolean isActive(){
	return active;
}


public void update(Observable o, Object message) {		
}

}

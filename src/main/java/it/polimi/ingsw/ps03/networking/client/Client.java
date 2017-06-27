package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

import it.polimi.ingsw.ps03.actions.CheckPlayer;
import it.polimi.ingsw.ps03.actions.Place;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;

public class Client extends Observable {//si connette alla porta e attende gli eventi dalla virtual view e li notifica alla local view->BillboardView?
	private static String ip;
	private static int port;
	private static ObjectInputStream in;
	private ObjectOutputStream out;

	
	
	public Client(String ip,int port){
		Client.ip = ip;
		Client.port = port;		
	}

//	
//	public void startClient() throws IOException{//mettere insieme view e netHan
//		
//		Socket socket = new Socket(ip , port);//crea la socket del client 
//		NetworkHandler networkHandler = new NetworkHandler(socket);

		
	//	out = new ObjectOutputStream(socket.getOutputStream());
		
	//	in = new ObjectInputStream(socket.getInputStream());
		
		
//		Scanner socketIn = new Scanner(socket.getInputStream());//legge gli imput messi dal client
////		PrintStream socketOut = new PrintStream(socket.getOutputStream());//manda il messaggio attraverso il canale
//		Scanner stin = new Scanner(System.in);	
//	//	update(null, "Connection Established");
//		String inputLine = stin.nextLine();
//		out.writeObject(System.in);
//		out.flush();
//		
//		try{
//			while(true){
//				Object socketLine = in.readObject();
//				
//				if(socketLine instanceof String){
//					String line = (String)socketLine;
//					System.out.println(line);
//				}
//				if(socketLine instanceof Billboard){
//					Billboard line = (Billboard)socketLine;
//					System.out.println(line.toString());
//					
//				}
//				if(socketLine instanceof CheckPlayer){
//					CheckPlayer line = (CheckPlayer)socketLine;
//					System.out.println(line.toString());
//				}	
				
		//		inputLine = stin.nextLine();//se da problemi cambia da nextLine a next()
				
//					out.writeObject("Hi");
//					out.flush();
//				
//				
		//		System.out.println("Inviato il messaggio :" +inputLine);
				
			//	Object socketLine = in.readObject();
//				
//				
				
				
//				socketOut.println(inputLine);
//				socketOut.flush();
//			    String socketLine = socketIn.nextLine();
	//			System.out.println(socketLine);
			
//			}
//		}catch(NoSuchElementException | ClassNotFoundException e){
//			System.out.println("Connection closed");
//
//		}finally{
//	//		stin.close();
//			in.close();
//			out.close();
//			socket.close();
//		}
//	}

	public void receiveMessage(Socket socket) throws ClassNotFoundException, IOException{
		try{
		while (true){
			
		Object line = in.readObject();
		if(line instanceof String){
			String read = (String)in.readObject();
				setChanged();
					notifyObservers(read);
					System.out.println("Ricevuto messaggio: " + read);								}
		if(line instanceof Billboard){
			Billboard read = (Billboard)in.readObject();
				setChanged();
					notifyObservers(read);
		}
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

	
	client.addObserver(ui);
	ui.addObserver(networkHandler);
	ui.initGame();
	in = new ObjectInputStream(socket.getInputStream());
	
	try{
		client.receiveMessage(socket);
//		client.startClient();
		
	}catch (IOException | ClassNotFoundException e){
		System.err.println(e.getMessage());
					}
													}





public void update(Observable o, Object message) {
	
	
}
}

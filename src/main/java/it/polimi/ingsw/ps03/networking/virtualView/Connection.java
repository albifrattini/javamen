package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.PrintStream;
import java.net.Socket;
//import java.util.Scanner;

//import it.polimi.ingsw.ps03.networking.controller.Controller;

public class Connection extends Observable<String> implements Runnable{

	private Socket socket;
	private Server server;
//	private Scanner in;
//	private PrintStream out;
	private String name;
	private boolean active = true;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Connection(Socket socket, Server server) throws IOException{
		this.socket = socket;
		this.server = server;
	} 
	
	
	@Override
	public void run() {//gestisce gli input degli utenti
		try{
			
			
	//		in = new Scanner(socket.getInputStream());//ObjectInputStream
			in = new ObjectInputStream(socket.getInputStream());	//crea una variabile di tipo ObjectInputStream, che legge gli oggetti inviati
																	//attraverso il canale
			out = new ObjectOutputStream(socket.getOutputStream());
			Object read = in.readObject();
		//	String read = in.next();
	//		name = read.toString();
			//manda il messaggio alla networkHandler, problema(viene ricevuto solo dopo input invio)
			send("well met " + read + " and welcome in Lorenzo il Magnifico "
					+"looking for other players... please wait");//dovrebbe farlo il server
			server.match(this, read.toString());
			while(isActive()){
		//		read = in.nextLine();
				read = in.readObject();
				notifyObservers(read);
			}
		}catch(IOException | ClassNotFoundException e){
			System.err.println("Error!");
		}finally{
			try {
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/*controlla che il client sia connesso*/
	private synchronized boolean isActive(){
		return active;
	}
	/*invia i messaggi*/
	public void send(String message) throws IOException {
		out.writeObject(message);
		out.flush();
	}
	
	public synchronized void closeConnection() throws IOException {		
		send("Connection terminated!");
		try {
			socket.close();
		} catch (IOException e) {
		}
		active = false;
	}
	/*chiude la connessione invocando la closeConnection e rimuove 
	 * dall'arrayList di utenti connessi il giocatore disconnesso*/
	private void close() throws IOException{
		closeConnection();
		System.out.println("Client Disconnected");
		server.removeConnection(this);
	}

	/*crea un tread separato che fa andare avanti 
	 * l'esecuzione mentre attende i messaggi*/
//	public void asyncSend(final String message){
//		new Thread(new Runnable() {			
//			@Override
//			public void run() {
//				try {
//					send(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}				
//			}
//		}).start();
//	}
}

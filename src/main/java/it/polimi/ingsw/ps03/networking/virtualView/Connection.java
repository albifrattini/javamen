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
			
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			Object line = in.readObject();
			String read = (String)line;	
		//3)da qui in poi solo dopo aver inserito il nome	
			System.out.println("Ricevuto: "+ read);
		//4)	
			send("well met " + read + " and welcome in Lorenzo il Magnifico "
					+"looking for other players... please wait");

			server.match(this, read);
			
			while(isActive()){
				line = in.readObject();
				notifyObservers(line);
			}
		}catch(IOException  e){
			System.out.println("Errore nella ricezione!");
		} catch (ClassNotFoundException e) {
			System.out.println("Errore nella ricezione dell'oggetto");
		}finally{
			try {
				close();
			} catch (IOException e) {
				System.out.println("Errore nella chiusura");
			}
		}
		
	}
	/*controlla che il client sia connesso*/
	private synchronized boolean isActive(){
		return active;
	}
	/*invia i messaggi*/
	public void send(Object message) throws IOException {		
			out.writeObject(message);
				out.flush();
					System.out.println("messaggio inviato: " +message);
	}
	
	public synchronized void closeConnection() throws IOException {		
		send("Connection terminated!");
		try {
			socket.close();
			in.close();
			out.close();
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

}

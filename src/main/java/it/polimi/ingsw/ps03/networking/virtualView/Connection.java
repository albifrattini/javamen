package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Connection extends Observable<Object> implements Runnable{

	private Socket socket;
	private Server server;
	private boolean active = true;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private Object line;
	
	public Connection(Socket socket, Server server) throws IOException{
		this.socket = socket;
		this.server = server;
	} 
	
		@Override
	public void run() {
		try{			
		
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			line = in.readObject();
			String read = (String)line;	
			System.out.println("[CONNECTION]  Ricevuto: "+ read);
			send("Benvenuto in Lorenzo Il Magnifico " + read + "!\nSono in attesa di altri giocatori... Attendi");
			server.waitingRoom(this, read);
			receiveMessage();					    
		}catch(IOException  e){
			System.out.println("Errore nella ricezione!");
		 }catch (ClassNotFoundException e) {
			System.out.println("Errore nella ricezione dell'oggetto");
		  }
	}
				
	public void receiveMessage(){
		while(isActive()){
			System.out.println("[CONNECTION]  Sono in continuo ascolto dei messaggi");			
			try{
				System.out.println("[CONNECTION] ARRIVATO IL MESSAGGIO");
				line = in.readObject();
				System.out.println("[CONNECTION] INOLTRATO ALLA REMOTE VIEW");
				System.out.println(line.toString());
				notifyObservers(line); //metodo che attiva la notify di tutte le remoteview che hanno come attributo 'Connection'
			}catch (ClassNotFoundException | IOException e){
			System.out.println("Non son riuscito a ricevere");
			}
		}					
		try{
			close();
		}catch (IOException e) {
			System.out.println("Errore nella chiusura");
		}
	}
	
	/*controlla che il client sia connesso*/
	private synchronized boolean isActive(){
		return active;
	}	
	
	/*invia i messaggi*/
	public void send(Object message) throws IOException{	
		out.writeObject(message);
		out.flush();
		out.reset();
		System.out.println("[CONNECTION]  Messaggio inviato: " + message);
	}		
	
	public synchronized void closeConnection() throws IOException {		
		send("Connection terminated!");
		try {
			socket.close();
			in.close();
			out.close();
		}catch (IOException e){
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

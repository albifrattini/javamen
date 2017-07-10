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
				line = in.readObject();
				System.out.println(line.toString());
				notifyObservers(line);
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
	
	private synchronized boolean isActive(){
		return active;
	}	
	
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
		
	private void close() throws IOException{
		closeConnection();
		System.out.println("Client Disconnected");
		server.removeConnection(this);
	}

}

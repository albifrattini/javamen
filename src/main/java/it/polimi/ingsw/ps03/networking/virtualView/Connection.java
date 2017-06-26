package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.ingsw.ps03.networking.controller.Controller;

public class Connection extends Observable<String> implements Runnable{

	private Socket socket;
	private Server server;
	private Scanner in;
	private PrintStream out;
	private String name;
	private boolean active = true;
	
	
	public Connection(Socket socket, Server server){
		this.socket = socket;
		this.server = server;
	} 
	
	
	@Override
	public void run() {//gestisce gli input degli utenti
		try{
			
			in = new Scanner(socket.getInputStream());
			out = new PrintStream(socket.getOutputStream());
			String read = in.next();
			name = read;
			//manda il messaggio alla networkHandler, problema(viene ricevuto solo dopo input invio)
			send("well met " + name + " and welcome in Lorenzo il Magnifico "
					+"looking for other players... please wait");//dovrebbe farlo il server
			server.match(this, name);
			while(isActive()){
				read = in.nextLine();
				notifyObservers(read);
			}
		}catch(IOException e){
			System.err.println("Error!");
		}finally{
			close();
		}
		
	}
	/*controlla che il client sia connesso*/
	private synchronized boolean isActive(){
		return active;
	}
	/*invia i messaggi*/
	public void send(String message) {
		out.println(message);
		out.flush();
	}
	
	public synchronized void closeConnection() {		
		send("Connection terminated!");
		try {
			socket.close();
		} catch (IOException e) {
		}
		active = false;
	}
	/*chiude la connessione invocando la closeConnection e rimuove 
	 * dall'arrayList di utenti connessi il giocatore disconnesso*/
	private void close(){
		closeConnection();
		System.out.println("Client Disconnected");
		server.removeConnection(this);
	}

	/*crea un tread separato che fa andare avanti 
	 * l'esecuzione mentre attende i messaggi*/
	public void asyncSend(final String message){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				send(message);				
			}
		}).start();
	}
}

package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * this class sends events to the virtual view of server
 * @author Amministratore
 */
public class NetworkHandler implements Observer {
	
	private ObjectOutputStream out;
	
	public NetworkHandler(Socket socket) throws IOException {
		out = new ObjectOutputStream(socket.getOutputStream());		
	}

	
	@Override
	public void update(Observable o, Object obj) {		
		if(!(o instanceof LocalView)){
			throw new IllegalArgumentException();
		}
		try {
			out.writeObject(obj);
			out.flush();
			out.reset();
		}catch (IOException e) {
			System.out.println("[NETWORKHANDLER]  Errore nell'invio del messaggio");
		}
	}
		
}







package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;


//con il metodo update invia eventi alla virtual view del server
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
				System.out.println("messaggino inviatino");
			} catch (IOException e) {
				System.out.println("Errore nell'invio del messaggio");
			}
		}
		
	}







package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.*;
import java.io.IOException;


/**
 * this class links controller with client
 *
 */
public class RemoteView extends View implements Observer<Object> {

	private Connection connection;
		
	public RemoteView(Player player, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
	}

	public Connection getConnection(){
		return connection;
	}

	public void startTurn(Billboard billboard) throws IOException{
		connection.send("E' il tuo turno");
		sendModel(billboard);
	}
	
	
	
	@Override
	protected void sendModel(Billboard billboard){
		try {
			System.out.println("INVIO MODEL A GIOCATORE " + player.getName());
			connection.send(billboard);
		} catch (IOException e) {
			System.out.println("[SERVER->CLIENT; WARNING]  Errore nell'invio del messaggio al client");
		}
		setChanged();
		notifyObservers("[SERVER->CLIENT]  Elemento inviato con successo al client");
	}	
	
		
	@Override//sovrascrive la notify di Observer quando viene invocata
	public void notify(Object message) throws IOException {				
		System.out.println("[REMOTEVIEW]  Ricevuto " + message.toString() + " dal giocatore " + player.toString());//riceve man mano e stampa sul server ciò che ha ricevuto
			try{	
				System.out.println("[REMOTEVIEW]  Inoltro messaggio del client al controller");						
				processChoice(message);		
			}catch(IllegalArgumentException e){
				connection.send("[REMOTEVIEW; WARNING]  Messaggio del client non inoltrato al controller");						
			}		
	}

	public void update(Observable<Object> o, Object obj) {
		
	}

}

		







		
	


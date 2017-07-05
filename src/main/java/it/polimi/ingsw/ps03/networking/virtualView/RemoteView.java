package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.billboard_pack.TurnOfPlay;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.CouncilRoom;
import it.polimi.ingsw.ps03.room_pack.Room;
import it.polimi.ingsw.ps03.actions.ActionChoices;
import it.polimi.ingsw.ps03.actions.FakePlace;

import java.io.IOException;
import java.util.List;


public class RemoteView extends View implements Observer<Object> {

	private Connection connection;
	
	
	public RemoteView(Player player, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
	    System.out.println("aggiunto alla lista di observer");	
	}

	public Connection getConnection(){
		return connection;
	}

	public void startTurn(Billboard billboard) throws IOException{
		connection.send("E' il tuo turno");
		showModel(billboard);
	}
	@Override
	protected void showModel(Billboard billboard){
		try {
			System.out.println("Invia copia del model");
				
//			Billboard model = (Billboard) billboard.clone();				
			connection.send(billboard);
		
		} catch (IOException/* | CloneNotSupportedException*/ e) {
			 System.out.println("Non son riuscito a inviare il model");
		}
	}	
	
	@Override//sovrascrive la notify di Observer quando viene invocata
	public void notify(Object message) throws IOException {				
		System.out.println("Ricevuto " + message.toString() + " dal giocatore " +player.toString());//riceve man mano e stampa sul server ciò che ha ricevuto
			try{	
				System.out.println("entrato nella notify dellla remoteView");						
				processChoice(message);		
			}catch(IllegalArgumentException e){
			connection.send("Error, Action not valid!Please try again");						
			}		
	}

		public void update(Observable o, Object obj) {
		
	}

}

		







		
	


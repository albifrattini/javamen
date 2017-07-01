package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.networking.model.Player;
import it.polimi.ingsw.ps03.actions.ActionChoices;

import java.io.IOException;


public class RemoteView extends View implements Observer<Object> {

	private Connection connection;
	
	
	public RemoteView(Player player, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
	    System.out.println("aggiunto aglla lista di observer");	
	}

	@Override
	protected void showModel(Billboard billboard){
		try {
			connection.send(billboard.clone());
		} catch (IOException | CloneNotSupportedException e) {
			 System.out.println("Non son riuscito a inviare il model");
		}
	}
	
	
	@Override//sovrascrive la notify di Observer quando viene invocata
	public void notify(Object message) throws IOException {		
		
		System.out.println("Ricevuto " + message.toString() + " dal giocatore " +player.toString());//riceve man mano e stampa sul server ciò che ha ricevuto
		
		try{	
			System.out.println("entrato nella notify dellla remoteView");
			
			ActionChoices choice = ActionChoices.parseInput(message);
			processChoice(choice);
			
			
		}catch(IllegalArgumentException e){
			connection.send("Error, Action not valid!Please try again");
						
		}		
	}
}
		







		
	


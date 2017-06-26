package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.networking.model.Choice;
import it.polimi.ingsw.ps03.networking.model.Model;
import it.polimi.ingsw.ps03.networking.model.Player;
import it.polimi.ingsw.ps03.actions.ActionChoices;

import java.util.List;
//import java.util.Observer;


public class RemoteView extends View implements Observer<String> {

	private Connection connection;
	
	public RemoteView(Player player/*, List<String> enemyes*/, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
		//c.asyncSend("I tuoi avversari sono: " +  + "\nScegli la tua mossa:");
	}

	
	protected void showModel(Billboard billboard){
		connection.send(billboard.getOutcome(getPlayer().toString() + "\nScegli la tua mossa"));
	}
	
	
	
	public void notify(String message) {		
		System.out.println("Ricevuto " + message);//riceve man mano e stampa sul server ciò che ha ricevuto
		try{
			ActionChoices choice = ActionChoices.parseInput(message);
			processChoice(choice);
		}catch(IllegalArgumentException e){
			connection.send("Error!");			
		}		
	}




		
	}


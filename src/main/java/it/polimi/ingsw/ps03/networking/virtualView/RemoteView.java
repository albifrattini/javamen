package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.networking.model.Player;
import it.polimi.ingsw.ps03.actions.ActionChoices;

import java.io.IOException;
//import java.util.Observer;


public class RemoteView extends View implements Observer<Object> {

	private Connection connection;
	
	public RemoteView(Player player/*, List<String> enemyes*/, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
		//c.asyncSend("I tuoi avversari sono: " +  + "\nScegli la tua mossa:");
	}

	
	protected void showModel(Billboard billboard){
		try {
			connection.send(billboard.getOutcome(getPlayer().toString() + "\nScegli la tua mossa"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void notify(Object message) throws IOException {		
		System.out.println("Ricevuto " + message.toString() + " dal giocatore " +player.toString());//riceve man mano e stampa sul server ciò che ha ricevuto
		
		try{						
			ActionChoices choice = ActionChoices.parseInput(message);
			processChoice(choice);
			
		}catch(IllegalArgumentException e){
			connection.send("Error, Action not valid!Please try again");
						
		}		
	
		}
	}




		
	


package it.polimi.ingsw.ps03.networking.virtualView;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps03.actions.ActionChoices;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.networking.model.Choice;
import it.polimi.ingsw.ps03.networking.model.Model;
import it.polimi.ingsw.ps03.networking.model.Player;

public abstract class View extends Observable/*<VCEvents>*/ implements Observer/*<MVevents>*/{

	private Player player;
	
	
	protected View(Player player){
		this.player = player;
	}
	
	
	public Player getPlayer(){
		return player;
	}
	
	
	protected void choiceSelected(ActionChoices choice){
		setChanged();
		notifyObservers(choice);
	}
	
	protected void processChoice(ActionChoices choice) {
		setChanged();
		notifyObservers(choice);
	}
	
	protected abstract void showModel(Billboard billboard);
	
	@Override
	public synchronized void update(Observable o, Object arg1) {
		if(!(o instanceof Billboard)){
			throw new IllegalArgumentException();
		}
		showModel((Billboard)o);
	}
	

}

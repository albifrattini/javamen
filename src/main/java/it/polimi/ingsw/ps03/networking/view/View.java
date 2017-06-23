package it.polimi.ingsw.ps03.networking.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps03.networking.model.Choice;
import it.polimi.ingsw.ps03.networking.model.Player;

public abstract class View extends Observable implements Observer{

	private Player player;
	
	
	protected View(Player player){
		this.player = player;
	}
	
	
	public Player getPlayer(){
		return player;
	}
	
	
	protected void choiceSelected(Choice choice){
		setChanged();
		notifyObservers(choice);
	}
//	
//	
//	protected abstract void showModel(Model model);
//	
	@Override
	public synchronized void update(Observable o, Object arg1) {
//		if(!(o instanceof Model)){
//			throw new IllegalArgumentException();
//		}
//		showModel((Model)o);
	}
	

}

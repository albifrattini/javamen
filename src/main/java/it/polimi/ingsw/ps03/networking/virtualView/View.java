package it.polimi.ingsw.ps03.networking.virtualView;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.networking.controller.Controller;
import it.polimi.ingsw.ps03.players.*;

public abstract class View extends Observable implements Observer{

	protected Player player;
		
	protected View(Player player){
		this.player = player;
	}
		
	public Player getPlayer(){
		return player;
	}
		
	protected void choiceSelected(Object obj){
		setChanged();
		notifyObservers(obj);
	}
	
	protected void processChoice(Object obj) {
		System.out.println("[VIEW<-REMOTEVIEW]  Funzione process choice avviata");
		setChanged();
		notifyObservers(obj);
	}
		
	protected abstract void sendModel(Billboard billboard);
	
	
	@Override
	public synchronized void update(Observable o, Object arg1) {
		if(!(o instanceof Controller)){
			throw new IllegalArgumentException();
		}
		if(arg1 instanceof Billboard){
			sendModel((Billboard) arg1);
		}
	}
	

}

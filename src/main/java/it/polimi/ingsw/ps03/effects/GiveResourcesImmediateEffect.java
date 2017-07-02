package it.polimi.ingsw.ps03.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.resources.Resources;

public class GiveResourcesImmediateEffect extends Effect implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Resources givenResources;
	
	public GiveResourcesImmediateEffect(Resources givenResources, String name){
		super(name);
		this.givenResources = givenResources;
	}
	
	public Resources getGivenResources(){
		return givenResources;
	}
	
	@Override
	public void applyEffect(Player player){
		player.getResources().add(givenResources);
	}
	
	@Override 
	public String toString(){
		return "Nome effetto: " + getName() + "\nRisorse date: " + givenResources;
	}
	
}

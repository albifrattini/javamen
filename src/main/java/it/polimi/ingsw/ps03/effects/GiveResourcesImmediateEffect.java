package it.polimi.ingsw.ps03.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.resources.Resources;
/**
 * this class represents the immediate Effect that gives some resources to the player
 *
 */
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
	/**
	 * this method adds the resources to the player
	 */
	@Override
	public void applyEffect(Player player){
		player.getResources().add(givenResources);
	}
	
	@Override 
	public String toString(){
		return "Nome effetto: " + getName() + "\nRisorse date: " + givenResources;
	}
	
}

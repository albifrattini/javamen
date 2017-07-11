package it.polimi.ingsw.ps03.effects;



import java.io.Serializable;

import it.polimi.ingsw.ps03.players.Player;
/**
 * This class is the abstract class extended by all the other Effect class
 *
 */
public abstract class Effect implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Effect(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void applyEffect(Player player){
		
	}
}

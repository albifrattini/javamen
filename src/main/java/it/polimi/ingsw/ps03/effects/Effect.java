package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.players.Player;

public abstract class Effect {

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

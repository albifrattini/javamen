package it.polimi.ingsw.ps03.effects;

public abstract class Effect {

	private String name;
	
	public Effect(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}

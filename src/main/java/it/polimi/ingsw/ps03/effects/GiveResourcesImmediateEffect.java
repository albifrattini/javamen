package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;

public class GiveResourcesImmediateEffect extends Effect{

	private Resources givenResources;
	
	public GiveResourcesImmediateEffect(Resources givenResources, String name){
		super(name);
		this.givenResources = givenResources;
	}
	
	public Resources getGivenResources(){
		return givenResources;
	}
	
	@Override 
	public String toString(){
		return "Nome effetto: " + getName() + "\nRisorse date: " + givenResources;
	}
	
}

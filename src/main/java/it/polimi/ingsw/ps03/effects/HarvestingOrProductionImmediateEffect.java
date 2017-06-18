package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class HarvestingOrProductionImmediateEffect extends GiveResourcesImmediateEffect {
	
	private int diceValue;
    private TowerColor  color;
	
	public HarvestingOrProductionImmediateEffect(int diceValue, TowerColor color, Resources givenResources, String name){
		super(givenResources, name);
		this.diceValue = diceValue;
		this.color = color;
	}
	
	public int getDiceValue(){
		return diceValue;
	}
	public TowerColor getColor(){
		return color;
	}
	
	public String effectType(){
		if(color == TowerColor.GREEN){
			return "Raccolto";
		}
		else{
			return "Produzione";
		}
	}

	
	
	
	@Override
	public String toString(){
		return "Nome effetto: " + effectType() + "\tValore azione: " + getDiceValue()
			+ "\nRisorse date: " + getGivenResources();
	}
	
}

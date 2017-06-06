package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;

public class HarvestingOrProductionImmediateEffect extends GiveResourcesImmediateEffect{
	
	private int diceValue;
	private String harvestingOrProduction;
	
	public HarvestingOrProductionImmediateEffect(int dValue, String harvOrProd, Resources rToGive){
		super(rToGive);
		diceValue = dValue;
		harvestingOrProduction = harvOrProd;
	}
	
	public int getValue(){
		return diceValue;
	}
	public String getHarvOrProd(){
		return harvestingOrProduction;
	}
	
	public void applyEffect(){
//		HarvestingOrProduction.startEffect(diceValue, harvestingOrProduction);
	}
}

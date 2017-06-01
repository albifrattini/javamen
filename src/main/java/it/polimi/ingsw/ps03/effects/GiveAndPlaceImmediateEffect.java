package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class GiveAndPlaceImmediateEffect extends GiveResourcesImmediateEffect {
	
	private TowerColor actionColor;
	private int diceValue;
	
	public GiveAndPlaceImmediateEffect(TowerColor color, int dice, Resources rToGive){
		super(rToGive);
		actionColor = color;
		diceValue = dice;
	}
	
	public TowerColor getActionColor(){
		return actionColor;
	}
	public int getDiceValue(){
		return diceValue;
	}
}

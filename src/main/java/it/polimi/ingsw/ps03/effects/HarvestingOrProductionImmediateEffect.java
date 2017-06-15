package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class HarvestingOrProductionImmediateEffect extends Effect {
	
	private int hpDiceValue;
    private TowerColor  hpCardColor;
	
	public HarvestingOrProductionImmediateEffect(int dValue, TowerColor cardColor){
		hpDiceValue = dValue;
		hpCardColor = cardColor;
	}
	
	public int getValue(){
		return hpDiceValue;
	}
	public TowerColor getHpCardColor(){
		return hpCardColor;
	}
	
}

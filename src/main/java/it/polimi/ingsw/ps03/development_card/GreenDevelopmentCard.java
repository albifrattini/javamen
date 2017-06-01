package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.effects.*;

public class GreenDevelopmentCard extends DevelopmentCard{

	private int diceValue;
	
	public GreenDevelopmentCard(int id, String name, TowerColor color, int period, int dice, Effect immediateEffect){
		super(id, name, color, period, immediateEffect);
		diceValue = dice;
	}
	
	public int getDiceValue(){
		return diceValue;
	}
}

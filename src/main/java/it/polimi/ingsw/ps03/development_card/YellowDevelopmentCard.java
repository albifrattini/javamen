package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class YellowDevelopmentCard extends GreenDevelopmentCard {
	
	private Resources cardCost;
	
	public YellowDevelopmentCard(int id, String name, TowerColor color, int period, int dice, Resources cost, Effect immediateEffect){
		super(id, name, color, period, dice, immediateEffect);
		cardCost = cost;
	}
	
	public Resources getCost(){
		return cardCost;
	}
}

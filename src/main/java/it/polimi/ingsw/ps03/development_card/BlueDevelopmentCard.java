package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.resources.Resources;

public class BlueDevelopmentCard extends DevelopmentCard{
	
	private Resources cardCost;
	
	public BlueDevelopmentCard(int id, String name, TowerColor color, int period, Resources cost, Effect immediateEffect){
		super(id, name, color, period, immediateEffect);
		cardCost = cost;
	}
	
	public Resources getCost(){
		return cardCost;
	}

}

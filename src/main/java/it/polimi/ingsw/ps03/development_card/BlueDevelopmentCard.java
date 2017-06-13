package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;

import java.util.List;

import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.resources.Resources;

public class BlueDevelopmentCard extends DevelopmentCard{
	
	private List<Resources> costChoices;
	
	public BlueDevelopmentCard(int id, String name, TowerColor color, int period, List<Resources> costs, Effect immediateEffect){
		super(id, name, color, period, immediateEffect);
		costChoices = costs;
	}
	
	public List<Resources> getCost(){
		return costChoices;
	}

}

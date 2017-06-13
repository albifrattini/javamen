package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
import java.util.List;

public class YellowDevelopmentCard extends GreenDevelopmentCard {
	
	private List<Resources> costChoices;
	
	public YellowDevelopmentCard(int id, String name, TowerColor color, int period, int dice, List<Resources> costs, Effect immediateEffect){
		super(id, name, color, period, dice, immediateEffect);
		costChoices = costs;
	}
	
	public List<Resources> getCost(){
		return costChoices;
	}
}

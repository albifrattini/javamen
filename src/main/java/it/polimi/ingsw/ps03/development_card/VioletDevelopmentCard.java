package it.polimi.ingsw.ps03.development_card;

import java.util.List;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class VioletDevelopmentCard extends DevelopmentCard{

	private List<Resources> costChoices;
	
	public VioletDevelopmentCard(int id, String name, TowerColor color, int period, List<Resources> costs, Effect immediateEffect){
		super(id, name, color, period, immediateEffect);
		costChoices = costs;
	}
	
	public List<Resources> getCosts(){
		return costChoices;
	}
	
}

package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class GiveAndPlaceImmediateEffect extends PlaceImmediateEffect {
	
	private Resources resourcesToGive;
	
	public GiveAndPlaceImmediateEffect(TowerColor color, Resources rToGive){
		super(color);
		resourcesToGive = rToGive;
	}
	
	public Resources getResources(){
		return resourcesToGive;
	}
}

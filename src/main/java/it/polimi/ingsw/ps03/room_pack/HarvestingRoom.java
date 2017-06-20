package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.resources.Resources;

public class HarvestingRoom extends ProductionRoom{
	
	public HarvestingRoom(Resources resources){
		super(TowerColor.GREEN, resources);
	}

	@Override
	public String toString(){
		return "Spazio Raccolto:\t\t\tRequisito: " + getRequirement();
	}
	
}

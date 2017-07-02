package it.polimi.ingsw.ps03.room_pack;

import java.io.Serializable;

import it.polimi.ingsw.ps03.resources.Resources;

public class HarvestingRoom extends ProductionRoom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HarvestingRoom(Resources resources){
		super(TowerColor.GREEN, resources);
	}

	@Override
	public String toString(){
		return "Spazio Raccolto:\t\t\tRequisito: " + getRequirement();
	}
	
}

package it.polimi.ingsw.ps03.room_pack;

public class HarvestingRoom extends ProductionRoom{
	
	public HarvestingRoom(){
		super("HARVESTING");
	}

	@Override
	public String toString(){
		return "Spazio Raccolto:\t\t\tRequisito: " + getRequirement();
	}
	
}

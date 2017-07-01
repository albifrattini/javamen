package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.resources.*;

public class CouncilRoom extends MarketRoom{


	public CouncilRoom(){
		super(new Resources());
		createCouncilResources();
	}
	
	private void createCouncilResources(){
		getResources().getResource("COINS").add(1);
		getResources().getResource("COUNCILPRIVILEGES").add(1);
	}
	
	@Override
	public String toString(){
		return "Palazzo del Consiglio:     \tRequisito: " + getRequirement() + "\t" + 
					getResources().toString();
	}
	

}

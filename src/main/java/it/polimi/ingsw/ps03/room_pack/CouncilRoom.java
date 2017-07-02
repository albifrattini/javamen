package it.polimi.ingsw.ps03.room_pack;

import java.io.Serializable;

import it.polimi.ingsw.ps03.resources.*;

public class CouncilRoom extends MarketRoom implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

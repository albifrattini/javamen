package it.polimi.ingsw.ps03.room_pack;

import java.io.Serializable;

import it.polimi.ingsw.ps03.resources.*;

public class MarketRoom extends Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Resources resources;
	
	public MarketRoom (Resources resources) {
		this(1, resources);
	}
	public MarketRoom (int requirement, Resources resources){
		super(requirement);
		this.resources = resources;
	}
	
	public Resources getResources(){
		return resources;
	}
	

	
	@Override 
	public String toString(){
		return "Spazio azione 'Mercato':     \tRequisito: " + getRequirement() + "\t" + 
					getResources().toString();
	}
	

}

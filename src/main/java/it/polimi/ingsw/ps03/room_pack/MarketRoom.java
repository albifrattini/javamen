package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.resources.*;

public class MarketRoom extends Room{
	
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
	

}

package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;

public class GiveResourcesImmediateEffect extends Effect{

	private Resources resourcesToGive;
	
	public GiveResourcesImmediateEffect(Resources rToGive){
		resourcesToGive = rToGive;
	}
	
	public Resources getResources(){
		return resourcesToGive;
	}
	
}

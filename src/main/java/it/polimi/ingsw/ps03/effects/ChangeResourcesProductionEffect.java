package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import java.util.List;
import java.util.ArrayList;

public class ChangeResourcesProductionEffect extends Effect{

	private List<Resources> choicesToGive;
	private List<Resources> choicesToEarn;
	
	public ChangeResourcesProductionEffect(){
		choicesToGive = new ArrayList<Resources>(2);
		choicesToEarn = new ArrayList<Resources>(2);
	}
	
	public Resources changeResources(Resources rToChange){
		return choicesToEarn.get(getIndexOfChange(rToChange));
	}
	
	private int getIndexOfChange(Resources rToChange){
		for(Resources r : choicesToGive){
			if(r.equals(rToChange)){
				return choicesToGive.indexOf(r);
			}
		}
		return -1;
	}
	
	public List<Resources> getResourcesToGive(){
		return choicesToGive;
	}
	
	public List<Resources> getResourcesToEarn(){
		return choicesToEarn;
	}
	
}

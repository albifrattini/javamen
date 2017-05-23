package it.polimi.ingsw.ps03.resource_pack;

import java.util.HashMap;

public class Resources {

	private HashMap<String,Resource> resources; 
	
	public Resources(){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins(0));
		resources.put("STONES", new Stones(0));
		resources.put("WOODS", new Woods(0));
		resources.put("SERVANTS", new Servants(0));
		resources.put("FPOINTS", new FaithPoints(0));
		resources.put("MPOINTS", new MilitaryPoints(0));
		resources.put("VPOINTS", new VictoryPoints(0));
	}
	public Resources(int initialCoins){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins(initialCoins));
		resources.put("STONES", new Stones(2));
		resources.put("WOODS", new Woods(2));
		resources.put("SERVANTS", new Servants(3));
		resources.put("FPOINTS", new FaithPoints(0));
		resources.put("MPOINTS", new MilitaryPoints(0));
		resources.put("VPOINTS", new VictoryPoints(0));
	}
	//public Resources(List<Resource> initialResources){}		altro possibile metodo
	
	public Resource getResource(String rName){
		return resources.get(rName);
	}
	
	
	
	
	
}

package it.polimi.ingsw.ps03.resources;

import java.util.HashMap;
import java.util.Map;

public class Resources {

	private HashMap<String,Resource> resources; 
	
	public Resources(){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins("Coins", 0));
		resources.put("STONES", new Stones("Stones", 0));
		resources.put("WOODS", new Woods("Woods", 0));
		resources.put("SERVANTS", new Servants("Servants", 0));
		resources.put("FPOINTS", new FaithPoints("FaithPoints", 0));
		resources.put("MPOINTS", new MilitaryPoints("MilitaryPoints", 0));
		resources.put("VPOINTS", new VictoryPoints("VictoryPoints", 0));
	}
	public Resources(int initialCoins){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins("Coins", initialCoins));
		resources.put("STONES", new Stones("Stones", 2));
		resources.put("WOODS", new Woods("Woods", 2));
		resources.put("SERVANTS", new Servants("Servants", 3));
		resources.put("FPOINTS", new FaithPoints("FaithPoints", 0));
		resources.put("MPOINTS", new MilitaryPoints("MilitaryPoints", 0));
		resources.put("VPOINTS", new VictoryPoints("VictoryPoints", 0));
	}
	//public Resources(ArrayList<Resource> initialResources){}
	
	public Map<String,Resource> getResources(){
		return this.resources;
	}
	public Resource getResource(String rName){
		return resources.get(rName);
	}
	
	public boolean add(Resource rToAdd){
		resources.get(rToAdd.getName()).add(rToAdd.getValue());
		return true;
	}
	public boolean sub(Resource rToSub){
		Resource temp = resources.get(rToSub.getName());
		if(temp.getValue() < rToSub.getValue()){
			return false;
		}
		temp.sub(rToSub.getValue());
		return true;
	}
/*	public boolean add(Resources rName){
		for(Resource r : rName){
			resources.get(r.getName()).add(r.getValue());
		}
		return true;
	}
	public boolean sub(Resources rName){
		for(Resource r : rName){
			Resource temp = resources.get(r.getName());
			if(temp.getValue() < r.getValue()){
				return false;
			}
			temp.sub(r.getValue());
		}
		return true;
	}
*/	
	
	
	
	
}

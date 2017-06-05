package it.polimi.ingsw.ps03.resources;

import java.util.HashMap;
import java.util.Map;

public class Resources {

	private Map<String,Resource> resources; 
	
	

	
	
	
	public Resources(){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins(0));
		resources.put("STONES", new Stones(0));
		resources.put("WOODS", new Woods(0));
		resources.put("SERVANTS", new Servants(0));
		resources.put("FAITHPOINTS", new FaithPoints(0));
		resources.put("MILITARYPOINTS", new MilitaryPoints(0));
		resources.put("VICTORYPOINTS", new VictoryPoints(0));
		resources.put("COUNCILPRIVILEGES", new CouncilPrivileges(0));
	}
	public Resources(int initialCoins){
		resources = new HashMap<String,Resource>(7);
		resources.put("COINS", new Coins(initialCoins));
		resources.put("STONES", new Stones(2));
		resources.put("WOODS", new Woods(2));
		resources.put("SERVANTS", new Servants(3));
		resources.put("FAITHPOINTS", new FaithPoints(0));
		resources.put("MILITARYPOINTS", new MilitaryPoints(0));
		resources.put("VICTORYPOINTS", new VictoryPoints(0));
		resources.put("COUNCILPRIVILEGES", new CouncilPrivileges(0));
	}

	
	
	
	
	
	public Map<String,Resource> getResources(){
		return this.resources;
	}
	public Resource getResource(String rName){
		return resources.get(rName);
	}
	
	public void add(Resources rsToAdd){
		for(Map.Entry<String, Resource> entry : rsToAdd.getResources().entrySet()){
			resources.get(entry.getKey()).add(entry.getValue().getValue()); 
			//il primo getValue() ritorna l'elemento corrispondente di quella entry
			//mentre il secondo ritorna il valore int della risorsa
		}
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
	
	@Override
	public String toString(){
		String allResources = "";
		for(Map.Entry<String, Resource> entry : resources.entrySet()){
			if(entry.getValue().getValue() != 0){
				allResources = allResources.concat(entry.getValue().toString() + "\t");
			}
		}
		return allResources;
	}
	
	
	
	
//	public boolean add(Resources rName){
//		for(Resource r : rName){
//			resources.get(r.getName()).add(r.getValue());
//		}
//		return true;
//	}
//	public boolean sub(Resources rName){
//		for(Resource r : rName){
//			Resource temp = resources.get(r.getName());
//			if(temp.getValue() < r.getValue()){
//				return false;
//			}
//			temp.sub(r.getValue());
//		}
//		return true;
//	}
	
	
	
	
	
}

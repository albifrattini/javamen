package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Resources implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	
	
	
	
	
	public Map<String,Resource> getResourcesMap(){
		return this.resources;
	}
	public Resource getResource(String rName){
		return resources.get(rName);
	}
	
	public void add(Resources rsToAdd){
		for(Map.Entry<String, Resource> entry : rsToAdd.getResourcesMap().entrySet()){
			resources.get(entry.getKey()).add(entry.getValue().getValue()); 
			//il primo getValue() ritorna l'elemento corrispondente di quella entry
			//mentre il secondo ritorna il valore int della risorsa
		}
	}
	public void sub(Resources rsToSub){
		try{
			for(Map.Entry<String, Resource> entry : rsToSub.getResourcesMap().entrySet()){
				resources.get(entry.getKey()).sub(entry.getValue().getValue()); 
			}
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Risorsa non sufficiente!");
		}
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
	
	
}

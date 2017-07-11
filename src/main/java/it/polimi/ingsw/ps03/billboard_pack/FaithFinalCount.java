package it.polimi.ingsw.ps03.billboard_pack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps03.resources.Resources;

public class FaithFinalCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Resources> givenResources;
	
	public FaithFinalCount(){
		givenResources = new HashMap<Integer, Resources>();
	}
	
	public void addResources(int i, Resources resources){
		givenResources.put(Integer.valueOf(i), resources);
	}
	
	public Resources getResources(int i){
		return givenResources.get(Integer.valueOf(i));
	}
	
	public Map<Integer, Resources> getMap(){
		return givenResources;
	}
}

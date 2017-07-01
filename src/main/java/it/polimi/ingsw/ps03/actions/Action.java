package it.polimi.ingsw.ps03.actions;

import java.io.Serializable;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;

public abstract class Action implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String actionName;
	private Billboard billboard;
	
	public Action(String name){
		actionName = name;
		this.billboard = null;
	}
	
	public Action(String name, Billboard billboard){
		actionName = name;
		this.billboard = billboard;
	}
	
	public static String getAction(){
		return actionName;
	}
	public Billboard getBillboard(){
		return billboard;
	}
	public void setBillboard(Billboard billboard){
		this.billboard = billboard;
	}

	
}

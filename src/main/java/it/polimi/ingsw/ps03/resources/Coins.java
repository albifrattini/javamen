package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;

public class Coins extends Resource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Coins(int value){
		super("Coins", value);
	}
}

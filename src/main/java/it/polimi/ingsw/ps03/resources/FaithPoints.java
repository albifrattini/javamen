package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;

public class FaithPoints extends Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FaithPoints(int value){
		super("Faith Points", value);
	}
}

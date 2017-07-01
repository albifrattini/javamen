package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;

public class MilitaryPoints extends Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MilitaryPoints(int value){
		super("Military Points", value);
	}
}

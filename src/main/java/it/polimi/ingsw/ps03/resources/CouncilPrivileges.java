package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;

public class CouncilPrivileges extends Resource implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouncilPrivileges(int value){
		super("Council Privileges", value);
	}
}

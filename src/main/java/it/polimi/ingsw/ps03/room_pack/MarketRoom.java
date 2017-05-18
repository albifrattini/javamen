package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.pawns_pack.*;
import it.polimi.ingsw.ps03.resource_pack.*;

public class MarketRoom {
	
	private int requirement;
	private boolean occupied;
	private Resource givenResource; //***creare classe risorsa***
	private Pawn pawn;
	
	public MarketRoom (int requirement, Resource givenResource) {
		this.requirement = requirement;
		this.occupied = false;
		this.pawn = null;
	}
	
	public int getRequirement(){
		return this.requirement;
	}
	
	public boolean isFull(){
		return this.occupied;
	}
	
	public Pawn getPawn(){
		return this.pawn;
	}
	
	public void placePawn(Pawn pawn){
		this.occupied = true;
		this.pawn = pawn;
	}
	
	public void removePawn(Pawn pawn){
		this.occupied = false;
		this.pawn = pawn;
	}
	
	/*	!!!METODI DA IMPLEMENTARE!!!
	 * private Resource getResource();
	 * 
	 */

}

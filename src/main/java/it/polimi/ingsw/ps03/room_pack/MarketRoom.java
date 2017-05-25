package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.*;

public class MarketRoom {
	
	private int requirement;
	private boolean occupied;
	private Resources resources; 
	private Pawn pawn;
	
	public MarketRoom (int requirement, Resources resources) {
		this.requirement = requirement;
		this.occupied = false;
		this.resources = resources;
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
		this.pawn = pawn;
	}
	
	public void removePawn(){
		this.occupied = false;
	}
	
	/*	!!!METODI DA IMPLEMENTARE!!!
	 * private Bonus getBonus();
	 * 
	 */

}

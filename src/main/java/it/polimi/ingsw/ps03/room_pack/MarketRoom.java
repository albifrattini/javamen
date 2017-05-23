package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.pawns_pack.*;
import it.polimi.ingsw.ps03.resource_pack.*;

public class MarketRoom {
	
	private int requirement;
	private boolean occupied;
	private Bonus givenBonus; 
	private Pawn pawn;
	
	public MarketRoom (int requirement, Bonus givenBonus) {
		this.requirement = requirement;
		this.occupied = false;
		this.givenBonus = givenBonus;
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

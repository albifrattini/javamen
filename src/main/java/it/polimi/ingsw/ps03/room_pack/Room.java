package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.pawns_pack.*;

public abstract class Room {
	
	private int requirement;
	private boolean occupied;
	private Pawn pawn;
	
	public Room(int requirement){
		this.requirement = requirement;
		this.occupied = false;
		this.pawn = null;
	}
	
	public int getRequirement(){
		return this.requirement;
	}
	
	public boolean getOccupied(){
		return this.occupied;
	}
	
	public Pawn getPawn(){
		return this.pawn;
	}
	
	public void setOccupied(boolean occupied){
		this.occupied = occupied;
	}
	
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	
	
}

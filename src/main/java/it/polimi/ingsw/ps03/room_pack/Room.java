package it.polimi.ingsw.ps03.room_pack;


import it.polimi.ingsw.ps03.players.*;

public abstract class Room {
	
	private boolean occupied;
	private final int requirement;
	private Pawn pawn;
	
	public Room(int requirement){
		this.requirement = requirement;
		occupied = false;
		pawn = null;
	}
	
	//METODI GET
	public int getRequirement(){
		return requirement;
	}
	public Pawn getPawn(){
		return pawn;
	}
	public boolean isFull(){
		return occupied;
	}
	
	//METODI SET
	public void occupyPlacement(){
		this.occupied = true;
	}
	public void freePlacement(){
		this.occupied = false;
	}
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
		occupyPlacement();
	}
	public void removePawn(){
		this.pawn = null;
		freePlacement();
	}
	
}

package it.polimi.ingsw.ps03.room_pack;


import it.polimi.ingsw.ps03.players.*;

public abstract class Room {
	
	private final int requirement;
	private Pawn pawn;
	
	public Room(int requirement){
		this.requirement = requirement;
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
		return (pawn != null);
	}
	
	//METODI SET
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void removePawn(){
		this.pawn = null;
	}
	
}

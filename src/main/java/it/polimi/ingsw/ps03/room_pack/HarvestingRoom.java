package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.pawns_pack.Pawn;

public class HarvestingRoom {
	private boolean occupied;
	private int requirement ;
	private Pawn pawn; 
	
	public HarvestingRoom (int requirement) {
		this.occupied = false;
		this.requirement = requirement;
		this.pawn = pawn;
		}
	public boolean getOccupied() {
		return this.occupied;
	}
	public void setId (boolean occupied) {
		this.occupied = occupied;
	}
	public int getRequirment() {
		return this.requirement;
	}

	public Pawn getPawn() {
		return this.pawn;
	}
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
}

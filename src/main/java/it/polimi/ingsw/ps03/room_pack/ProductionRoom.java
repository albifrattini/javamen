package it.polimi.ingsw.ps03.room_pack;
import it.polimi.ingsw.ps03.players.Pawn;

public class ProductionRoom {
	private boolean occupied;
	private int requirement ;
	private Pawn pawn; 
	
	public ProductionRoom () {
		this.occupied = occupied;
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
	public void setRequirement (int requirement) {
		this.requirement = requirement;
	}
	public Pawn getPawn() {
		return this.pawn;
	}
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
	

}

package it.polimi.ingsw.ps03.room_pack;
import it.polimi.ingsw.ps03.players.Pawn;

public class ProductionRoom {
	
	private boolean occupied;
	private int requirement ;
	private Pawn pawn; 
	
	public ProductionRoom(){
		this.occupied = false;
		this.requirement = 1;
		this.pawn = null;
		}
	
	//METODI GET
	public int getRequirement(){
		return this.requirement;
	}
	public Pawn getPawn(){
		return this.pawn;
	}
	public boolean isFull(){
		return this.occupied;
	}
	
	//METODI SET
	private void occupyPlacement(){
		this.occupied = true;
	}
	private void freePlacement(){
		this.occupied = false;
	}
	public void setPawn(Pawn pawn){
		occupyPlacement();
		this.pawn = pawn;
	}
	public void removePawn(){
		freePlacement();
		this.pawn = null;
	}
	

}

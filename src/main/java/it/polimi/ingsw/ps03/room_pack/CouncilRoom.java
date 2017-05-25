package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.players.*;

public class CouncilRoom {
	private final int requirement = 1;
	/*private Bonus givenBonus; */
	private Pawn pawn;


	public CouncilRoom (/*Bonus givenBonus*/) {
		/*this.givenBonus = givenBonus;*/
		this.pawn = null;
	}
	
	public int getRequirement(){ 
		return this.requirement;
	}
	public Pawn getPawn(){
		return this.pawn;
	}
	public void placePawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void removePawn(){
		this.pawn = null;
	}

}

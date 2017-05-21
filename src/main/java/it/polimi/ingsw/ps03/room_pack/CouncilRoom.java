package it.polimi.ingsw.ps03.room_pack;

public class CouncilRoom {
	private int requirement;
	/*private Bonus givenBonus; */
	private Pawn pawn;


	public CouncilRoom (int requirement/*, Bonus givenBonus*/) {
		this.requirement = requirement;
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
	
	public void removePawn(Pawn pawn){
		this.pawn = pawn;
	}

}

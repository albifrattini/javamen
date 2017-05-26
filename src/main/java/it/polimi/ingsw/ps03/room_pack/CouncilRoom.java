package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.*;

public class CouncilRoom {
	private final int requirement;
	private Resources bonusResources;
	private Pawn pawn;


	public CouncilRoom (){
		requirement = 1;
		bonusResources = createCouncilResources();
		pawn = null;
	}
	
	public int getRequirement(){ 
		return requirement;
	}
	public Pawn getPawn(){
		return this.pawn;
	}
	public Resources placePawn(Pawn pawn){
		this.pawn = pawn;
		return bonusResources;
	}
	public void removePawn(){
		this.pawn = null;
	}
	
	private Resources createCouncilResources(){
		Resources r = new Resources();
		r.getResource("COINS").add(1);
		r.getResource("COUNCILPRIVILEGES").add(1);
		return r;
	}

}

package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.*;

public class CouncilRoom extends Room{
	
	private Resources bonusResources;


	public CouncilRoom(){
		super(1);
		bonusResources = createCouncilResources();
	}
	
	public CouncilRoom(Resources resources){
		super(1);
		bonusResources = resources;
	}
	
	public Resources getResources(){
		return bonusResources;
	}
	public Resources placePawn(Pawn pawn){
		setPawn(pawn);
		return bonusResources;
	}
	
	private Resources createCouncilResources(){
		Resources r = new Resources();
		r.getResource("COINS").add(1);
		r.getResource("COUNCILPRIVILEGES").add(1);
		return r;
	}
	
	
	

}

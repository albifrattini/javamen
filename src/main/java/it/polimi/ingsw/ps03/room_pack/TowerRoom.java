package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.development_card.*;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.resources.*;

public class TowerRoom {
	
	private TowerColor color;
	private int requirement;
	private Resources resources;
	private DevelopmentCard placedCard;
	private boolean occupied;
	private Pawn pawn;
	
	//COSTRUTTORE
	public TowerRoom(TowerColor color, int requirement, Resources resources){
		this.color = color;
		this.requirement = requirement;
		this.resources = resources;
		this.placedCard = null;
		this.occupied = false;
		this.pawn = null;
	}
	
	//METODI GET PRINCIPALI
	public TowerColor getTowerRoomColor(){
		return this.color;
	}
	public int getTowerRoomRequirement(){
		return this.requirement;
	}
	public Resources getResources(){
		return this.resources;
	}
	public DevelopmentCard getPlacedCard(){
		return this.placedCard;
	}
	public boolean getOccupation(){
		return this.occupied;
	}
	public Pawn getPlacedPawn(){
		return this.pawn;
	}
	
	
	
	//METODI SET
	public void setOccupation(){
		occupied = true;
	}
	public void freeCardSpace(){
		placedCard = null;
	}
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void setDevelopmentCard(DevelopmentCard developmentCard){
		this.placedCard = developmentCard;
	}
	
	public DevelopmentCard drawCard(Pawn pToPlace){
		DevelopmentCard temp = placedCard;
		setOccupation();
		setPawn(pToPlace);
		freeCardSpace();
		return temp;
	}
	
	
	
	
}

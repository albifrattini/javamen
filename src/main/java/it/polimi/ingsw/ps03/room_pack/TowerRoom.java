package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.pawns_pack.Pawn;
import it.polimi.ingsw.ps03.resource_pack.*;
import it.polimi.ingsw.ps03.development_card.*;

public class TowerRoom {
	
	private TowerColor color;
	private int requirement;
	private Bonus givenBonus;
	private DevelopmentCard placedCard;
	private boolean occupied;
	private Pawn pawn;
	
	//COSTRUTTORE
	public TowerRoom(TowerColor color, int requirement, Bonus givenBonus){
		this.color = color;
		this.requirement = requirement;
		this.givenBonus = givenBonus;
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
	public Bonus getTowerRoomBonus(){
		return this.givenBonus;
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
	
	//METODI GET SECONDARI
	public int getBonusCoins(){
		return givenBonus.getResource().getCoinsValue();
	}
	public int getBonusWoods(){
		return givenBonus.getResource().getWoodsValue();
	}
	public int getBonusStones(){
		return givenBonus.getResource().getStonesValue();
	}
	public int getBonusServants(){
		return givenBonus.getResource().getServantsValue();
	}
	public int getBonusMilitaryPoints(){
		return givenBonus.getMilitaryPoints();
	}
	
	//METODI SET
	public void setOccupation(boolean occupied){
		this.occupied = occupied;
	}
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void setDevelopmentCard(DevelopmentCard developmentCard){
		this.placedCard = developmentCard;
	}
	
	
	
	
}

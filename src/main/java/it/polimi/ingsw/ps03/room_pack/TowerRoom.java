package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.development_card.*;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.resources.*;

public class TowerRoom extends MarketRoom{
	
	private final TowerColor color;
	private DevelopmentCard placedCard;
	
	//COSTRUTTORE
	public TowerRoom(TowerColor color, int requirement, Resources resources){
		super(requirement, resources);
		this.color = color;
		this.placedCard = null;
	}
	
	//METODI GET PRINCIPALI
	public TowerColor getTowerRoomColor(){
		return color;
	}
	public DevelopmentCard getPlacedCard(){
		return placedCard;
	}
	
	
	
	
	//METODI SET
	public void freeCardSpace(){
		placedCard = null;
	}
	public void setDevelopmentCard(DevelopmentCard developmentCard){
		placedCard = developmentCard;
	}
	
	public DevelopmentCard drawCard(Pawn pToPlace){
		DevelopmentCard temp = placedCard;
		occupyPlacement();
		setPawn(pToPlace);
		freeCardSpace();
		return temp;
	}
	
	@Override
	public String toString(){
		return "Spazio azione 'Torre " + getTowerRoomColor() + "':   \tRequisito: " + getRequirement() + "\t";
	}
	
	
}

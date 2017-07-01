package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.development_card.*;
import it.polimi.ingsw.ps03.resources.Resources;

public class TowerRoom extends MarketRoom{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	

	
	@Override
	public String toString(){
		if(placedCard != null){
			return "Spazio azione 'Torre " + getTowerRoomColor().toString().substring(0, 1) + 
					getTowerRoomColor().toString().substring(1, getTowerRoomColor().toString().length()).toLowerCase()
					+ "':  \tRequisito: " + getRequirement() + "\t" + getResources().toString() 
					+ "\n\tCarta sviluppo: " + placedCard.getCardName();
		}
		return "Spazio azione 'Torre " + getTowerRoomColor().toString().substring(0, 1) + 
				getTowerRoomColor().toString().substring(1, getTowerRoomColor().toString().length()).toLowerCase()
				+ "':  \tRequisito: " + getRequirement() + "\t" + getResources().toString(); 
	}
	
	
}

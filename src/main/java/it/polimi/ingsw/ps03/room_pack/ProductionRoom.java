package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.effects.HarvestingOrProduction;

import java.util.List;

public class ProductionRoom extends Room{
	
	private String effectName;
	
	public ProductionRoom(String name){
		super(1);
		this.effectName = name;
	}
	
	public void applyEffect(List<DevelopmentCard> cards, int diceValue){
		HarvestingOrProduction.startEffect(cards, diceValue, this.effectName);
	}
	
	public String getName(){
		return effectName;
	}
	
	@Override
	public String toString(){
		return "Spazio Produzione:\t\t\tRequisito: " + getRequirement();
	}
}

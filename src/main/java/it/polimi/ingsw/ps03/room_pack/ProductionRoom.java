package it.polimi.ingsw.ps03.room_pack;

import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.effects.HarvestingOrProduction;
import it.polimi.ingsw.ps03.resources.Resources;

import java.util.List;

public class ProductionRoom extends MarketRoom{
	
	private TowerColor colorEffect;
	
	public ProductionRoom(TowerColor colorEffect, Resources resources){
		super(1, resources);
		this.colorEffect = colorEffect;
	}
	public ProductionRoom(Resources resources){
		super(1, resources);
		this.colorEffect = TowerColor.YELLOW;
	}
	
	public void applyEffect(List<DevelopmentCard> cards, int diceValue){
		HarvestingOrProduction.startEffect(cards, diceValue, this.colorEffect);
	}
	
	public TowerColor getColorEffect(){
		return colorEffect;
	}
	
	@Override
	public String toString(){
		return "Spazio Produzione:\t\t\tRequisito: " + getRequirement();
	}
}

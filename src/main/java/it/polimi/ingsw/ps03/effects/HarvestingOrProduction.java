package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

import java.util.List;

public class HarvestingOrProduction {

	public static void startEffect(List<DevelopmentCard> cards, int diceValue, String harvOrProd){
		TowerColor colorOfEffect = TowerColor.GREEN;
		if(harvOrProd == "PRODUCTION"){
			colorOfEffect = TowerColor.YELLOW;
		}
		for(DevelopmentCard d : cards){
			if(d.getCardColor() == colorOfEffect){
//				d.applyPermanentEffect();
			}
		}
	}
	
}

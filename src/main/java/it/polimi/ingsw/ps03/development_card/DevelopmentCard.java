package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.resources.Resources;

import java.util.List;

public class DevelopmentCard {
	
	private int cardId;
	private int cardPeriod;
	private int diceValue;
	private String cardName;
	private TowerColor cardColor;
	private Effect immediateEffect;
		
	//COSTRUTTORE
	public DevelopmentCard(int mId,int mCardPeriod, int diceValue, String mCardName, 
			TowerColor mCardColor){ 
		
		cardId = mId;
		cardName = mCardName;
		cardColor = mCardColor;
		cardPeriod = mCardPeriod;
		immediateEffect = null; 
		
	}
	
	@Override
	public String toString(){
		String price = "";
		for(Resources r : costs){
			price = r.toString();
		}
		return "\tNome: " + cardName + "\tColore: " + cardColor + "\nCosto: " + price;
	}
	
	//METODI GET
	public DevelopmentCard getCard(){
		return this;
	}
	public int getId(){
		return cardId;
	}
	public String getCardName(){
		return cardName;
	}
	public TowerColor getCardColor(){
		return cardColor;
	}
	public int getCardPeriod(){
		return cardPeriod;
	}
	public int getDiceValue(){
		return diceValue;
	}
	public Effect getImmediateEffect(){
		return immediateEffect;
	}

	
	
}

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
	private List<Resources> costs;
	private List<Resources> requirements;
	private Effect immediateEffect;
		
	//COSTRUTTORE
	public DevelopmentCard(int mId, String mCardName, TowerColor mCardColor, 
			int mCardPeriod, Effect mImmediateEffect){ 
		cardId = mId;
		cardName = mCardName;
		cardColor = mCardColor;
		cardPeriod = mCardPeriod;
	}
	
	@Override
	public String toString(){
		return "id: " + cardId + "\nCard Name: " + cardName + "\nCard Color: " + cardColor +
				"\nCard Period: " + cardPeriod;
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
	public Effect getImmediateEffect(){
		return immediateEffect;
	}
	
	
	
}

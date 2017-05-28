package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.effects.*;

public class DevelopmentCard {
	
	private static int id;
	private static String cardName;
	private static TowerColor cardColor;
	private static int cardPeriod;
	private static int diceValue;
	private static Effect immediateEffect;
	
	//COSTRUTTORE
	public DevelopmentCard(int mId, String mCardName, TowerColor mCardColor, 
			int mCardPeriod, int mDiceValue, Effect mImmediateEffect){ 
		id = mId;
		cardName = mCardName;
		cardColor = mCardColor;
		cardPeriod = mCardPeriod;
		diceValue = mDiceValue;
	}
	
	@Override
	public String toString(){
		return "id: " + id + "\nCard Name: " + cardName + "\nCard Color: " + cardColor +
				"\nCard Period: " + cardPeriod + "\nDice Value: " + diceValue;
	}
	
	//METODI GET
	public DevelopmentCard getCard(){
		return this;
	}
	public static int getId(){
		return id;
	}
	public static String getCardName(){
		return cardName;
	}
	public static TowerColor getCardColor(){
		return cardColor;
	}
	public static int getCardPeriod(){
		return cardPeriod;
	}
	public static int getDiceValue(){
		return diceValue;
	}
	public static Effect getImmediateEffect(){
		return immediateEffect;
	}
	
	
	
}

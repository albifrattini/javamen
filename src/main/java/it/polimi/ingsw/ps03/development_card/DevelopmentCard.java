package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevelopmentCard {
	
	private int id;
	private String cardName;
	private TowerColor cardColor;
	private int cardPeriod;
	private int diceValue;
	//private ImmediateEffect immediateEffect;
	//private PermanentEffect permanentEffect;
	
	//COSTRUTTORE
	public DevelopmentCard(){
		
	}
	public DevelopmentCard(int id, String cardName, TowerColor cardColor, 
			int cardPeriod, int diceValue){ //Effects ancora da aggiungere
		this.id = id;
		this.cardName = cardName;
		this.cardColor = cardColor;
		this.cardPeriod = cardPeriod;
		this.diceValue = diceValue;
	}
	
	@Override
	public String toString(){
		return "id: " + this.id + "Card Name: " + this.cardName + "Card Color: " + this.cardColor +
				"Card Period: " + this.cardPeriod + "Dice Value: " + this.diceValue;
	}
	
	//METODI SET: assenti perchè una carta non può essere modificata
	
	//METODI GET
	public DevelopmentCard getCard(){
		return this;
	}
	public int getId(){
		return this.id;
	}
	public String getCardName(){
		return this.cardName;
	}
	public TowerColor getCardColor(){
		return this.cardColor;
	}
	public int getCardPeriod(){
		return this.cardPeriod;
	}
	public int getDiceValue(){
		return this.diceValue;
	}
	//public ImmediateEffect getImmediateEffect();
	//public PermanentEffect getPermanentEffect();
	
	
}

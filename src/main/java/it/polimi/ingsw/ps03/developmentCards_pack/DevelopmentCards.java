package it.polimi.ingsw.ps03.developmentCards_pack;

import  it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevelopmentCards {
	private int id;
	private String cardName;
	private TowerColor cardColor;
	private int cardPeriod; 
	/*private ImmediateEffect immediateEffect;
	private PermanentEffect permanentEffect; */
	private int diceValue;
	
	public DevelopmentCards(int id, String cardName, TowerColor cardColor, int diceValue) {
		this.id = id;
		this.cardName = cardName;
		this.cardColor = cardColor;
		this.diceValue = diceValue;
	}
	    public int getId() {
			return this.id;
		}
		public void setId (int id) {
			this.id = id;
		}
		public String getCardName () {
			return this.cardName;
		}
		public void setCardName (String cardName) {
			this.cardName = cardName;
		}
		public TowerColor getCardColor () {
			return this.cardColor;
		}
		public void setCardColor (TowerColor cardColor) {
			this.cardColor = cardColor;
		}
		
		public int getDiceValue () {
			return this.diceValue;
		}
		public void setDiceValue (int diceValue) {
			this.diceValue = diceValue;
		}
		
	}
    
	



package it.polimi.ingsw.ps03.dices_pack;

public class Dices {
	
	private DiceOrange diceOrange;
	private DiceBlack diceBlack;
	private DiceWhite diceWhite;
	
	public Dices() {
		diceOrange = new DiceOrange();
		diceBlack = new DiceBlack();
		diceWhite = new DiceWhite();
	}
	
	public void roll(){
		diceOrange.setValue((int)(Math.random()*6+1));
		diceBlack.setValue((int)(Math.random()*6+1));
		diceWhite.setValue((int)(Math.random()*6+1));
	}
	
	public int getOrangeValue () {
		return diceOrange.getValue();
	}
	
	public int getBlackValue () {
		return diceBlack.getValue();
	}
	
	public int getWhiteValue () {
		return diceWhite.getValue();
	}
}

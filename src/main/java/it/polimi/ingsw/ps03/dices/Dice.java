package it.polimi.ingsw.ps03.dices;

public abstract class Dice {

	private DiceColor color;
	private int value;
	
	public Dice(DiceColor color, int value){
		this.color = color;
		this.value = value;
	}
	
	public DiceColor getColor(){
		return color;
	}
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
}

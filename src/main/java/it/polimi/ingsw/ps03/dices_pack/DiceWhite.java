package it.polimi.ingsw.ps03.dices_pack;

public class DiceWhite {
	
	private String color;
	private int value;
	
	public DiceWhite() {
		this.color = "WHITE";
		this.value = 0;
	}
	
	public void setValue (int value) {
		this.value = value;
	}
	
	public int getValue () {
		return this.value;
	}
	
	public String getColor () {
		return this.color;
	}
	
}

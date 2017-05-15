package it.polimi.ingsw.ps03.dices_pack;

public class DiceOrange {
	
	private String color;
	private int value;
	
	public DiceOrange (){
		this.color = "ORANGE";
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

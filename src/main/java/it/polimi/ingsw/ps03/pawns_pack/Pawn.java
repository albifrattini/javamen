package it.polimi.ingsw.ps03.pawns_pack;

public class Pawn {
	
	private int value;
	private String colorOfDice;
	
	public Pawn (String colorOfDice, int value) {
		this.value = value;
		this.colorOfDice = colorOfDice;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getColorOfDice(){
		return this.colorOfDice.toUpperCase();
	}
}

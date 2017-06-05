package it.polimi.ingsw.ps03.players;


public class Pawn {
	
	private PlayerColor pColor;
	private PawnDiceColor dColor;
	private int value;
	
	public Pawn(PlayerColor pColor, PawnDiceColor dColor, int value){
		this.pColor = pColor;
		this.dColor = dColor;
		this.value = value;
	}
	
	public PlayerColor getPlayerColor(){
		return this.pColor;
	}
	public PawnDiceColor getDiceColor(){
		return this.dColor;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return dColor.toString().substring(0, 1).toUpperCase() + 
				dColor.toString().substring(1, dColor.toString().length()).toLowerCase() 
				+ " di valore " + value;
	}
}

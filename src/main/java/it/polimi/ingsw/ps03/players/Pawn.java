package it.polimi.ingsw.ps03.players;


public abstract class Pawn {
	
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
	
	
}

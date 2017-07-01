package it.polimi.ingsw.ps03.players;

import java.io.Serializable;

public class Pawn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerColor pColor;
	private PawnDiceColor dColor;
	private int value;
	
	public Pawn(PlayerColor pColor, PawnDiceColor dColor, int value){
		this.pColor = pColor;
		this.dColor = dColor;
		this.value = value;
	}
	public Pawn(int value){
		this.pColor = null;
		this.dColor = null;
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
	public void addServants(int servants){
		value = value + servants;
	}
	
	@Override
	public String toString(){
		return dColor.toString().substring(0, 1).toUpperCase() + 
				dColor.toString().substring(1, dColor.toString().length()).toLowerCase() 
				+ " di valore " + value;
	}
}

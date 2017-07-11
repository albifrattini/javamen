package it.polimi.ingsw.ps03.dices;

import java.io.Serializable;
/**
 * this class represents a Dice and sets its color and value
 *
 */
public class Dice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

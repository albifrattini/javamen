package it.polimi.ingsw.ps03.dices;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
/**
 * this class creates dices and allows to roll its
 *
 */
public class Dices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Dice> dices;
	/**
	 * this method creates 3 dices of 3 different colors
	 */
	public Dices(){
		dices = new HashMap<String, Dice>(3);
		dices.put("BLACK", new Dice(DiceColor.BLACK, 0));
		dices.put("ORANGE", new Dice(DiceColor.ORANGE, 0));
		dices.put("WHITE", new Dice(DiceColor.WHITE, 0));
	}
	
	public Dice getDice(String diceColor){
		return dices.get(diceColor);
	}
	/**
	 * this method rolls dices and change the value of all of them
	 */
	public void rollDices(){
		for(Map.Entry<String,Dice> d : dices.entrySet()){
			d.getValue().setValue((int)(Math.random()*6+1));
		}
	}
	
}

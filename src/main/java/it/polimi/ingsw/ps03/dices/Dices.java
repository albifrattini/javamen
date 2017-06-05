package it.polimi.ingsw.ps03.dices;

import java.util.Map;
import java.util.HashMap;

public class Dices {

	private Map<String, Dice> dices;
	
	public Dices(){
		dices = new HashMap<String, Dice>(3);
		dices.put("BLACK", new Dice(DiceColor.BLACK, 0));
		dices.put("ORANGE", new Dice(DiceColor.ORANGE, 0));
		dices.put("WHITE", new Dice(DiceColor.WHITE, 0));
	}
	
	public Dice getDice(String diceColor){
		return dices.get(diceColor);
	}
	public void rollDices(){
		for(Map.Entry<String,Dice> d : dices.entrySet()){
			d.getValue().setValue((int)(Math.random()*6+1));
		}
	}
	
}

package it.polimi.ingsw.ps03.dices;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DicesTest {
	
	private Map<String, Dice> dices;
	
	public void Dices(){
		dices = new HashMap<String, Dice>(3);
		dices.put("BLACK", new Dice(DiceColor.BLACK, 0));
		dices.put("ORANGE", new Dice(DiceColor.ORANGE, 0));
		dices.put("WHITE", new Dice(DiceColor.WHITE, 0));
	}
	
	public Dice getDice(String diceColor){
		return dices.get(diceColor);
	}

	@Test
	public void testGetDice() {
		getDice("BLACK");
		
	}

	@Test
	public void testRollDices() {
		fail("Not yet implemented");
	}

}

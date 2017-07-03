package it.polimi.ingsw.ps03.dices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.billboard_pack.TurnOfPlay;

public class DiceTest {
	Dice mDice;
	
	@Before
	public void setUp() throws Exception {
		 mDice = new Dice(DiceColor.ORANGE, 3);
	}

	

	@Test
	public void testGetColor() {
		assertEquals(DiceColor.ORANGE,mDice.getColor());
	}
	
	@Test
	public void testGetValue() {
		assertEquals(3,mDice.getValue());
	}

	@Test
	public void testSetValue(){
		mDice.setValue(4);
		assertEquals(4,mDice.getValue());
	}


}


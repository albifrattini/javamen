package it.polimi.ingsw.ps03.dices;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {
	private int value = -1;

	
	public void setValue(int value){
		this.value = value;
	}

	@Test
	public void testSetValue() {
		setValue(4);
		assertEquals(4,value);
	}

}


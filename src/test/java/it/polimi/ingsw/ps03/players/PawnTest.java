package it.polimi.ingsw.ps03.players;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {
	
	private PlayerColor pColor;
	private PawnDiceColor dColor;
	private int value=4;

	public void setValue(int value){
		this.value = value; }
		
    public void addServants(int servants){
			value = value + servants;
		
	}
	@Test
	public void testSetValue() {
		setValue(4);
		assertEquals(4,value);
	}

	@Test
	public void testAddServants() {
	     addServants(6);
		assertEquals(10,value);
	}

}

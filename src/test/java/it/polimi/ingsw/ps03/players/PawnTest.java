package it.polimi.ingsw.ps03.players;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.dices.DiceColor;

public class PawnTest {
	
	Pawn mPawn;
	
	@Before
	public void setUp() throws Exception {
		 mPawn = new Pawn(PlayerColor.BLUE, PawnDiceColor.BLACK, 3);
	}
		
	
	@Test
	public void testSetValue() {
		mPawn.setValue(4);
		assertEquals(4,mPawn.getValue());
	}

	@Test
	public void testAddServants() {
	     mPawn.addServants(6);
		assertEquals(9,mPawn.getValue());
	}
	
	@Test
	public void testGetPlayerColor() {
		assertEquals(PlayerColor.BLUE,mPawn.getPlayerColor());
	}
	
	@Test
	public void testGetDiceColor() {
		assertEquals(PawnDiceColor.BLACK,mPawn.getDiceColor());
	}
	
	@Test
	public void testGetValue() {
		assertEquals(3,mPawn.getValue());
	}

}

package it.polimi.ingsw.ps03.billboard_pack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TurnOfPlayTest {

	TurnOfPlay mturn;
	
	@Before
	public void setUp() throws Exception {
		 mturn = new TurnOfPlay();
	}


	@Test
	public void testGetMiniTurn() {
		assertEquals(0,mturn.getMiniTurn());
	}

	@Test
	public void testGetTurn() {
		assertEquals(0,mturn.getTurn());
	}

	@Test
	public void testGetPeriod() {
		assertEquals(1,mturn.getPeriod());
	}

	@Test
	public void testGetPlayerToPlay() {
		assertEquals(0,mturn.getPlayerToPlay());
	}

	@Test
	public void testGetNumberOfPlayers() {
        assertEquals(2,mturn.getNumberOfPlayers());
	}

	@Test
	public void testSetNumberOfPlayers() {
		mturn.setNumberOfPlayers(4);
		assertEquals(4, mturn.getNumberOfPlayers());
	}

	@Test
	public void testNextTurnOfPlay() {
		mturn.nextTurnOfPlay();
		assertEquals(1,mturn.getPeriod());
		assertEquals(1,mturn.getMiniTurn());
		assertEquals(1,mturn.getTurn());
	}

	@Test
	public void testNextTurn() {
		mturn.nextTurn();
		assertEquals(1,mturn.getTurn());
		assertEquals(1,mturn.getMiniTurn());
	}

	@Test
	public void testNextPeriod() {
		mturn.nextPeriod();
		assertEquals(2,mturn.getPeriod());
		assertEquals(1,mturn.getMiniTurn());
		assertEquals(1,mturn.getTurn());
	}

	@Test
	public void testNextPlayer() {
	 mturn.nextPlayer();
	 assertEquals(1,mturn.getPlayerToPlay());
	 assertEquals(1, mturn.getMiniTurn());
	}

	@Test
	public void testHasNextMiniTurn() {
		assertTrue(mturn.hasNextMiniTurn());
	}

}

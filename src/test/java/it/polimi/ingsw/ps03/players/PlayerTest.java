package it.polimi.ingsw.ps03.players;

import  it.polimi.ingsw.ps03.resources.Resources;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	 Player mPlayer;
	 Resources mRes;
	@Before
	public void setUp() throws Exception {
		mPlayer = new Player("nome prova",PlayerColor.BLUE, 5);
		mRes = new Resources(5);
	}




	@Test
	public void testGetName() {
		assertEquals("nome prova", mPlayer.getName());
	}

	@Test
	public void testGetColor() {
	    assertEquals(PlayerColor.BLUE,mPlayer.getColor());
	}



	@Test
	public void testGetLeftPawns() {
		assertEquals(4, mPlayer.getLeftPawns());
	}



}

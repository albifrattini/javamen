package it.polimi.ingsw.ps03.billboard_pack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.players.PlayerColor;

public class BillboardTest {

	Billboard mBillboard;
	
	@Before
	public void setUp() throws Exception {
		mBillboard= new Billboard();
	}

	@Test
	public void testAddPlayerRemote() {
		mBillboard.addPlayerRemote(1, "nome prova",PlayerColor.GREEN, 5);
		assertEquals(PlayerColor.GREEN, mBillboard.getPlayers().get(0).getColor());
		assertEquals(5, mBillboard.getPlayers().get(0).getResources().getResource("COINS").getValue());
	}

	@Test
	public void testAddPlayer() {
		mBillboard.addPlayerRemote(1, "nome prova",PlayerColor.GREEN, 5);
		assertEquals(PlayerColor.GREEN, mBillboard.getPlayers().get(0).getColor());
		assertEquals(5, mBillboard.getPlayers().get(0).getResources().getResource("COINS").getValue());
	}

	@Test
	public void testGetDices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTurnOfPlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerOfColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCouncilChoices() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateList() {
		fail("Not yet implemented");
	}

}

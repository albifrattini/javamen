package it.polimi.ingsw.ps03.billboard_pack;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.players.PlayerColor;

public class BillboardTest {

	Billboard mBillboard;
	List<Player> players;
	
	@Before
	public void setUp() throws Exception {
		mBillboard= new Billboard();
		
	}

	@Test
	public void testAddPlayerRemote() {
		mBillboard.addPlayerRemote(0, "nome prova",PlayerColor.GREEN, 5);
		assertEquals(PlayerColor.GREEN, mBillboard.getPlayers().get(0).getColor());
		assertEquals(5, mBillboard.getPlayers().get(0).getResources().getResource("COINS").getValue());
		assertEquals("nome prova", mBillboard.getPlayers().get(0).getName());
	}

	@Test
	public void testAddPlayer() {
		mBillboard.addPlayer(0,PlayerColor.GREEN, 5);
		assertEquals(PlayerColor.GREEN, mBillboard.getPlayers().get(0).getColor());
		assertEquals(5, mBillboard.getPlayers().get(0).getResources().getResource("COINS").getValue());
	}

    @Test
	public void testGetPlayerOfColor() {
		mBillboard.addPlayer(0,PlayerColor.GREEN, 5);
		assertEquals(mBillboard.getPlayers().get(0),mBillboard.getPlayerOfColor(PlayerColor.GREEN));

	}




}

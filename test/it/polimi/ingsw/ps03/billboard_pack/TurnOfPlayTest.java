package it.polimi.ingsw.ps03.billboard_pack;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurnOfPlayTest {
	private int miniTurn=3;
	private int turn=2;
	private int period=1;
	private int playerToPlay=1;
	private int numberOfPlayers=2;
	
	public void TurnOfPlay(){
		this.miniTurn = 3;
		this.turn = 2;
		this.period = 1;
		this.playerToPlay = 1;
		this.numberOfPlayers = 2;
	}
	public int getMiniTurn(){
		return miniTurn;
	}
	public int getTurn(){
		return turn;
	}
	public int getPeriod(){
		return period;
	}
	public int getPlayerToPlay(){
		return playerToPlay;
	}
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers){
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public void nextTurnOfPlay(){
		if(turn != 2){
			nextTurn();
		}
		else{
			nextPeriod();
		}
	}
	public void nextTurn(){
		miniTurn = 1;
		turn++;
	}
	public void nextPeriod(){
		miniTurn = 1;
		turn = 1;
		period++;
	}
	public void nextPlayer(){
		if(playerToPlay >= numberOfPlayers-1){
			playerToPlay = 0;
			miniTurn++;
		}
		else{
			miniTurn++;
			playerToPlay++;
		}
	}
	public boolean hasNextMiniTurn(){
		if(miniTurn > numberOfPlayers*4){
			return false;
		}
		return true;
	}

	@Test
	public void testGetMiniTurn() {
		TurnOfPlay();
		assertEquals(3,getMiniTurn());
	}

	@Test
	public void testGetTurn() {
		TurnOfPlay();
		assertEquals(2,getTurn());
	}

	@Test
	public void testGetPeriod() {
		TurnOfPlay();
		assertEquals(1,getPeriod());
	}

	@Test
	public void testGetPlayerToPlay() {
		TurnOfPlay();
		assertEquals(1,getPlayerToPlay());
	}

	@Test
	public void testGetNumberOfPlayers() {
		TurnOfPlay();
		assertEquals(2,getNumberOfPlayers());
	}

	@Test
	public void testSetNumberOfPlayers() {
		setNumberOfPlayers(4);
		assertEquals(4, numberOfPlayers);
	}

	@Test
	public void testNextTurnOfPlay() {
		nextTurnOfPlay();
		assertEquals(2,period);
		assertEquals(1,miniTurn);
		assertEquals(1, turn);
	}

	@Test
	public void testNextTurn() {
		nextTurn();
		assertEquals(3,turn);
		assertEquals(1,miniTurn);
	}

	@Test
	public void testNextPeriod() {
		nextPeriod();
		assertEquals(2,period);
		assertEquals(1,miniTurn);
		assertEquals(1,turn);
	}

	@Test
	public void testNextPlayer() {
	 nextPlayer();
	 assertEquals(0,playerToPlay);
	}

	@Test
	public void testHasNextMiniTurn() {
		assertTrue(hasNextMiniTurn());
	}

}

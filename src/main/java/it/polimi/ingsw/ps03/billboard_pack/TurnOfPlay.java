package it.polimi.ingsw.ps03.billboard_pack;

import java.io.Serializable;

public class TurnOfPlay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int miniTurn;
	private int turn;
	private int period;
	private int playerToPlay;
	private int numberOfPlayers;
	
	public TurnOfPlay(){
		this.miniTurn = 0;
		this.turn = 0;
		this.period = 1;
		this.playerToPlay = 0;
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
	
	
	
}

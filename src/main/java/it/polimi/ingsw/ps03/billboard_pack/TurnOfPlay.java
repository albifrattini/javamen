package it.polimi.ingsw.ps03.billboard_pack;

import java.io.Serializable;
/**
 * This class defines the instant of the game and  who has to move  
 * @author Amministratore
 *
 */
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
	/**
	 * this method changes the turn of the game with the next one, if it's the last turn changes the period
	 */
	public void nextTurnOfPlay(){
		if(turn != 2){
			nextTurn();
		}
		else{
			nextPeriod();
		}
	}
	/**
	 * this method changes the turn with the next one
	 */
	public void nextTurn(){
		miniTurn = 1;
		turn++;
	}
	
/**
 * this method changes the period with the next one
 */
	public void nextPeriod(){
		miniTurn = 1;
		turn = 1;
		period++;
	}
	/**
	 * this method changes the player who has to move with the next one
	 */
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
	/**
	 * this class defines if there are a following mini-turn
	 * @return  Boolean 
	 */
	public boolean hasNextMiniTurn(){
		if(miniTurn > numberOfPlayers*4){
			return false;
		}
		return true;
	}
	
	
	
}

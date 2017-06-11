package it.polimi.ingsw.ps03.billboard_pack;

import java.util.Observable;

public class TurnOfPlay extends Observable{

	private int turn;
	private int period;
	private int playerToPlay;
	private int numberOfPlayers;
	
	public TurnOfPlay(){
		this.turn = 0;
		this.period = 1;
		this.playerToPlay = 0;
		this.numberOfPlayers = 2;
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
		turn++;
	}
	public void nextPeriod(){
		turn = 1;
		period++;
	}
	public void nextPlayer(){
		if(playerToPlay >= numberOfPlayers-1){
			playerToPlay = 0;
			nextTurnOfPlay();
		}
		else{
			playerToPlay++;
		}
	}
	
}

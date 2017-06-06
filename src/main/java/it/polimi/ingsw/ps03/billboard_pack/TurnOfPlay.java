package it.polimi.ingsw.ps03.billboard_pack;

import java.util.Observable;

public class TurnOfPlay extends Observable {

	private int turn;
	private int period;
	private int playerToPlay;
	
	public TurnOfPlay(){
		turn = 0;
		period = 1;
		playerToPlay = 0;
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
		if(playerToPlay >= 3){
			playerToPlay = 0;
			nextTurnOfPlay();
		}
		else{
			playerToPlay++;
		}
	}
	
}

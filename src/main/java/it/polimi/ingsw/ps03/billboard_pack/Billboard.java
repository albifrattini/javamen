package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices_pack.Dices;
import it.polimi.ingsw.ps03.players_pack.Player;

import java.util.ArrayList;


public class Billboard {

	private static Dices dices;
	private ArrayList<Player> players = new ArrayList<Player>(4);
	private static int period;
	private static int turn;
	
	
	public Billboard(){
		dices = new Dices();
		period = 1;
		turn = 1;
	}
	
	public void addPlayer(int index, String color){
		Player player = new Player(color);
		players.add(index, player);
	}
	
	public void changeDicesValues(){
		dices.roll();
	}
	
	public static int getBlackDiceValue(){
		return dices.getBlackValue();
	}
	
	public static int getOrangeDiceValue(){
		return dices.getOrangeValue();
	}
	
	public static int getWhiteDiceValue(){
		return dices.getWhiteValue();
	}
	
	public static int getPeriod(){
		return period;
	}
	
	public static int getTurn(){
		return turn;
	}
	
}

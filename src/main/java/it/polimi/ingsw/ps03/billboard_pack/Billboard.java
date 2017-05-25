package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.players.*;

import java.util.ArrayList;


public class Billboard {

	private static Dices dices;
	private ArrayList<Player> players = new ArrayList<Player>(4);
	private static Instant instant;
	
	
	public Billboard(){
		dices = new Dices();
		instant = new Instant();
	}
	
	public void addPlayer(PlayerColor color, int initialCoins){
		Player player = new Player(color, initialCoins);
		players.add(player);
	}
	
	public static Dices getDices(){
		return dices;
	}
	public static Instant getInstant(){
		return instant;
	}
	
	
}

package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;
import java.util.List;
import java.util.ArrayList;


public class Billboard {

	private static Dices dices;
	private static List<Player> players;
	private static Table table;
	private static Instant instant;
	
	
	public Billboard(){
		players = new ArrayList<Player>(4);
		dices = new Dices();
		table = new Table();
		instant = new Instant();
	}
	
	public static void addPlayer(PlayerColor color, int initialCoins){
		Player player = new Player(color, initialCoins);
		players.add(player);
	}
	
	public static Dices getDices(){
		return dices;
	}
	public static Instant getInstant(){
		return instant;
	}
	public static Table getTable(){
		return table;
	}
	public static List<Player> getPlayers(){
		return players;
	}
	
	
}

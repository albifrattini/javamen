package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.networking.model.Outcome;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


public class Billboard extends Observable implements Cloneable{

	private Dices dices;
	private List<Player> players;
	private Table table;
	private TurnOfPlay turnOfPlay;
	private Map<Player, Outcome> outcomes = new HashMap<>();//aggiunto ma da rivedere
	
	public Billboard(){
		players = new ArrayList<Player>(4);
		dices = new Dices();
		table = new Table();
		turnOfPlay = new TurnOfPlay();
	}
	
	public void addPlayer(int order, PlayerColor color, int initialCoins){
		Player player = new Player(color, initialCoins);
		players.add(order, player);
	}
	
	public Dices getDices(){
		return dices;
	}
	public TurnOfPlay getTurnOfPlay(){
		return turnOfPlay;
	}
	public Table getTable(){
		return table;
	}
	public List<Player> getPlayers(){
		return players;
	}
	public Player getPlayerOfColor(PlayerColor color){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getColor() == color){
				return players.get(i);
			}
		}
		throw new NullPointerException("Errore nella conversione giocatori per turno successivo!");
	}
	public void setPlayers(List<Player> players){
		this.players = players;
	}
	public /*Outcome*/ String getOutcome(String player) {//aggiunto ma da rivedere
		return outcomes.get(player).toString();
	}

	
}

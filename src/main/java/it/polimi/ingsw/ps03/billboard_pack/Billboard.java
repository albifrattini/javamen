package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Billboard extends Observable implements Observer{

	private Dices dices;
	private List<Player> players;
	private Table table;
	private TurnOfPlay turnOfPlay;
	
	
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
	
	public void toNotify(){
		setChanged();
		notifyObservers(this);
	}
	
	
	
	
	
	@Override
	public void update(Observable o, Object arg){
		if(o != turnOfPlay){
			throw new IllegalArgumentException();
		}
		setChanged();
		notifyObservers(this);
	}
}

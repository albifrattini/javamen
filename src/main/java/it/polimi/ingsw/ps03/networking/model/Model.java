package it.polimi.ingsw.ps03.networking.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

public class Model extends Observable {

	private Map<Player, Choice> choices = new HashMap<>();
//	private Map<Player, Outcome> outcomes = new HashMap<>();
	
	public Choice getPlayerChoice(Player player){
		return choices.get(player);
	}
	
}

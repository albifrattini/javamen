package it.polimi.ingsw.ps03.networking.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

public class Model extends Observable {//per ora tengo questa classe ma va sostituita con la Billboard

	private Map<Player, Choice> choices = new HashMap<>();
//	private Map<Player, Outcome> outcomes = new HashMap<>();
	
	public Choice getPlayerChoice(Player player){
		return choices.get(player);
	}
	
	public void setPlayerChoice(Player player, Choice playerChoice) {
		if(choices.size() == 2){
			choices.clear();
	//		outcomes.clear();
		}				
		if(!choices.containsKey(player)){
			if(choices.size() == 1){
				Player other = new LinkedList<Player>(choices.keySet()).get(0);
		//		outcomes.put(player, 
	//					playerChoice.resultAgainst(choices.get(other)));		
	//			outcomes.put(other, 
		//				choices.get(other).resultAgainst(playerChoice));	
				setChanged();
			}	
			choices.put(player, playerChoice);
		}	
		notifyObservers();
	}
	
//	public Outcome getOutcome(Player player) {
//		return outcomes.get(player);
//	}
}

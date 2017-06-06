package it.polimi.ingsw.ps03.players;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import it.polimi.ingsw.ps03.development_card.*;
import it.polimi.ingsw.ps03.resources.*;


public class Player {

	private PlayerColor color;
	private Map<String, Pawn> pawns;
	private Resources resources;
	private List<DevelopmentCard> ownedCards;

	
	public Player (PlayerColor pColor, int initialCoins) {
		color = pColor;
		pawns = new HashMap<String, Pawn>(4);
		pawns.put("WHITE", new Pawn(color, PawnDiceColor.WHITE, 0));
		pawns.put("BLACK", new Pawn(color, PawnDiceColor.BLACK, 0));
		pawns.put("ORANGE", new Pawn(color, PawnDiceColor.ORANGE, 0));
		pawns.put("NEUTRAL", new Pawn(color, PawnDiceColor.NEUTRAL, 0));
		resources = new Resources(initialCoins);
		ownedCards = new ArrayList<DevelopmentCard>(0);
	}
	
	public void refreshPawns(){
		pawns.put("WHITE", new Pawn(color, PawnDiceColor.WHITE, 0));
		pawns.put("BLACK", new Pawn(color, PawnDiceColor.BLACK, 0));
		pawns.put("ORANGE", new Pawn(color, PawnDiceColor.ORANGE, 0));
		pawns.put("NEUTRAL", new Pawn(color, PawnDiceColor.NEUTRAL, 0));
	}
	
	public Pawn getPawn(String pawn){
		return pawns.get(pawn);
	}
	public Map<String, Pawn> getPawns(){
		return pawns;
	}
	public PlayerColor getColor(){
		return this.color;
	}
	public Resources getResources(){
		return this.resources;
	}
	public List<DevelopmentCard> getCards(){
		return this.ownedCards;
	}
	public int getLeftPawns(){
		int counter = 0;
		for(Map.Entry<String, Pawn> p : pawns.entrySet()){
			counter++;
			System.out.println("Dice Color:  " + p.getValue().getDiceColor() + 
					"\nValue:  " + p.getValue().getValue() + "\n");
		}
		return counter;
	}
	public Pawn getPawnToPlace(String pawn){
		Pawn temp = getPawn(pawn);
		removePawn(pawn);
		return temp;
	}
	public void removePawn(String pawn){
		pawns.remove(pawn);
	}
	
	
	
	
	
	
	
}

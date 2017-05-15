package it.polimi.ingsw.ps03.players_pack;

import java.util.ArrayList;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.pawns_pack.Pawn;

public class Player {

	private String color;
	private ArrayList<Pawn> pawns = new ArrayList<Pawn>(4);

	public Player(){
		
	}
	
	public Player (String color) {
		this.color = color.toUpperCase();
		pawns.add(0, new Pawn("NEUTRAL", 0));
		pawns.add(1, new Pawn("ORANGE", Billboard.getOrangeDiceValue()));
		pawns.add(2, new Pawn("BLACK", Billboard.getBlackDiceValue()));
		pawns.add(3, new Pawn("WHITE", Billboard.getWhiteDiceValue()));
	}
	
	public String getColor(){
		return this.color.toUpperCase();
	}
	
	
	
}

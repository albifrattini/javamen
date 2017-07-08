package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.players.PlayerColor;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class ClientFakePlace extends ClientPlace{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Billboard billboard;
	private TowerColor color;
	private Pawn pawn;
	
	public ClientFakePlace(Billboard billboard, PlayerColor player, TowerColor color, int actionValue){
		super(ActionChoices.PLACE, player);
		this.color = color;
		this.pawn = new Pawn(actionValue);
		this.billboard = billboard;
	}
	
	public Billboard getModel(){
		return billboard;
	}
	public TowerColor getTowerColor(){
		return color;
	}
	public Pawn getPawn(){
		return pawn;
	}
	

}

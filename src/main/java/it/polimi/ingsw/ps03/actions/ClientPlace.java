package it.polimi.ingsw.ps03.actions;

import java.io.Serializable;

import it.polimi.ingsw.ps03.players.PawnDiceColor;
import it.polimi.ingsw.ps03.players.PlayerColor;
import it.polimi.ingsw.ps03.resources.Resources;
/**
 * this class represents the action Place as seen from the client
 * @author Amministratore
 *
 */
public class ClientPlace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionChoices type;
	private PlayerColor playerColor;
	private PawnDiceColor pawnColor;
	private int room;
	private Resources spentResources;
	private Resources chosenCost;
	
	
	
	public ClientPlace(ActionChoices type, PlayerColor playerColor){
		this.type = type;
		this.playerColor = playerColor;
		this.pawnColor = null;
		this.room = -1;
		this.spentResources = null;
		this.chosenCost = null;
	}
	
	
	public void setPawnColor(PawnDiceColor pawnColor){
		this.pawnColor = pawnColor;
	}
	public void setRoom(int position){
		this.room = position;
	}
	public void setSpentResources(Resources spentResources){
		this.spentResources = spentResources;
	}
	public void setChosenCost(Resources chosenCost){
		this.chosenCost = chosenCost;
	}
	
	
	public ActionChoices getActionType(){
		return type;
	}
	public PlayerColor getPlayerColor(){
		return playerColor;
	}
	public PawnDiceColor getPawnColor(){
		return pawnColor;
	}
	public int getRoom(){
		return room;
	}
	public Resources getSpentResources(){
		return spentResources;
	}
	public Resources getChosenCost(){
		return chosenCost;
	}
		
}

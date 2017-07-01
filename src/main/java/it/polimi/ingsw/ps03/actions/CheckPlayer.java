package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.players.Player;

public class CheckPlayer extends Action{

	private int turnOfPlayer;
	
	
	public CheckPlayer(int turnOfPlayer){
		super("CHECKPLAYER");
		this.turnOfPlayer = turnOfPlayer;
	}

	
	
	public Player applyAction(){
		Player player = getBillboard().getPlayers().get(turnOfPlayer);
		return player;
	}
	

}

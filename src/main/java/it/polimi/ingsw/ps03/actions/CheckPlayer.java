package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.players.PlayerColor;

public class CheckPlayer extends Action{

	private PlayerColor color;
	
	public CheckPlayer(){
		super("CHECKPLAYER");
		this.color = null;
	}
	
	public CheckPlayer(Billboard billboard, PlayerColor color){
		super("CHECKPLAYER", billboard);
		this.color = color;
	}
	
	public void applyAction(PlayerColor color){
		Player player = getPlayer(color);
	}
	
	public Player getPlayer(PlayerColor color){
		Player player = null;
		for(int i = 0; i < getBillboard().getPlayers().size(); i++){
			if(getBillboard().getPlayers().get(i).getColor() == color){
				player = getBillboard().getPlayers().get(i);
			}
		}
		return player;
	}
}

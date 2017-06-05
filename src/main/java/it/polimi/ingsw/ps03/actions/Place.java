package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;

public class Place extends Action{
	
	private Pawn pawn;
	private Room room;
	
	public Place(){
		super("PLACE");
		this.pawn = null;
		this.room = null;
	}
	
	public Place(Billboard billboard, Pawn pawn, Room room){
		super("PLACE", billboard);
		this.pawn = pawn;
		this.room = room;
	}
	
	public void applyAction(){
		if(room instanceof TowerRoom){
			getPlayer(pawn.getPlayerColor()).getCards().add(((TowerRoom) room).getPlacedCard());
		}
		if(room instanceof MarketRoom){
			getPlayer(pawn.getPlayerColor()).getResources().add(((MarketRoom) room).getResources());
		}
		if(room instanceof CouncilRoom){
			getPlayer(pawn.getPlayerColor()).getResources().add(((CouncilRoom) room).getResources());
		}
		room.setPawn(pawn);
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
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void setRoom(Room room){
		this.room = room;
	}

}

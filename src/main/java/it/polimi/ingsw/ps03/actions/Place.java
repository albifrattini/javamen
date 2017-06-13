package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.resources.Resources;

import java.util.List;

public class Place extends Action{
	
	private Player player;
	private Pawn pawn;
	private Room room;
	private Resources requiredResources;
	
	public Place(Player player){
		super("PLACE");
		this.player = player;
		this.pawn = null;
		this.room = null;
		this.requiredResources = null;
	}
	
	public Place(Billboard billboard, Player player, Pawn pawn, Room room){
		super("PLACE", billboard);
		this.player = player;
		this.pawn = pawn;
		this.room = room;
		this.requiredResources = new Resources();
	}
	
	public void applyAction(){
		if(room instanceof MarketRoom){
			if(room instanceof TowerRoom){
				player.getCards().add(((TowerRoom) room).getPlacedCard());
			}
			player.getResources().add(((MarketRoom) room).getResources());
		}
		if(room instanceof CouncilRoom){
			player.getResources().add(((CouncilRoom) room).getResources());
			getBillboard().getTable().addCouncilRoom();
		}
//		if(room instanceof ProductionRoom){
//			List<DevelopmentCard> cards = player.getCards();
//			if(room instanceof HarvestingRoom){
//				((HarvestingRoom) room).applyEffect(cards, pawn.getValue());
//			}
//			else{
//				((ProductionRoom) room).applyEffect(cards, pawn.getValue());
//			}
//		}
		room.setPawn(pawn);
		player.removePawn(pawn.getDiceColor().toString());
		player.getResources().sub(requiredResources);
		getBillboard().getTurnOfPlay().nextPlayer();
	}
	
	
	public void setPlayer(Player player){
		this.player = player;
	}
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setRequiredResources(Resources requiredResources){
		this.requiredResources = requiredResources;
	}
	public Player getPlayer(){
		return player;
	}
	public Pawn getPawn(){
		return pawn;
	}
	public Resources getRequiredResources(){
		return requiredResources;
	}
	public Room getRoom(){
		return room;
	}

}

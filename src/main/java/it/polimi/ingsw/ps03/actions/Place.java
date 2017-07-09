package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.resources.Resources;

/**
 * This class represents the action Place, that allows to position the pawn on the Table
 * @author Amministratore
 *
 */
public class Place extends Action{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private Pawn pawn;
	private Room room;
	private Resources requiredResources;
	private Resources chosenCost;
	
	public Place(Player player){
		super("PLACE");
		this.player = player;
		this.pawn = null;
		this.room = null;
		this.requiredResources = null;
		this.chosenCost = null;
	}
	
	public Place(String name, Billboard billboard, Player player){
		super(name, billboard);
		this.player = player;
		this.pawn = null;
		this.room = null;
		this.requiredResources = new Resources();
		this.chosenCost = null;
	}
	
	public DevelopmentCard applyAction(){
		if(room instanceof CouncilRoom){
			getBillboard().getTable().addCouncilRoom();
		}
		room.setPawn(pawn);
		player.removePawn(pawn.getDiceColor().toString());
		player.getResources().add(((MarketRoom) room).getResources());
		player.getResources().sub(requiredResources);
		if(room instanceof TowerRoom){
			player.getCards().add(((TowerRoom) room).getPlacedCard());
			return ((TowerRoom) room).getPlacedCard();
		}
		return null;
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
	public void setChosenCost(Resources chosenCost){
		this.chosenCost = chosenCost;
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
	public Resources getChosenCost(){
		return chosenCost;
	}
	public Room getRoom(){
		return room;
	}

}

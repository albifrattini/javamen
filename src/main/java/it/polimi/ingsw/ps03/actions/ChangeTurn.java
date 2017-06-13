package it.polimi.ingsw.ps03.actions;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.room_pack.CouncilRoom;
import it.polimi.ingsw.ps03.room_pack.Room;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.players.PawnDiceColor;

public class ChangeTurn extends Action {
	
	public ChangeTurn(){
		super("CHANGETURN");
	}
	
	public ChangeTurn(Billboard billboard){
		super("CHANGETURN", billboard);
	}
	
	public void applyAction(){
		getBillboard().getTurnOfPlay().nextTurnOfPlay();
		getBillboard().getDices().rollDices();
		newPawns();	
		refreshTable();
	}
	public void refreshTable(){
		for(Room r : getBillboard().getTable().getRooms()){
			r.removePawn();
		}
	}
	public void newPawns(){
		for(Player pl : getBillboard().getPlayers()){
			pl.refreshPawns();
			for(Map.Entry<String, Pawn> pa : pl.getPawns().entrySet()){
				if(pa.getValue().getDiceColor() != PawnDiceColor.NEUTRAL){
					pa.getValue().setValue(getDiceColorValue(pa.getValue().getDiceColor()));
				}
			}
		}
	}
	public int getDiceColorValue(PawnDiceColor color){
		return getBillboard().getDices().getDice(color.toString()).getValue();
	}
	public void changeTurnOfPlay(){
		List<Player> players = new ArrayList<Player>(getBillboard().getPlayers().size());
		for(CouncilRoom cr : getBillboard().getTable().getCouncilPalaceList()){
			players.add(getBillboard().getPlayerOfColor(cr.getPawn().getPlayerColor()));
			getBillboard().getPlayers().remove(getBillboard().getPlayerOfColor(cr.getPawn().getPlayerColor()));
			//da gestire il caso in cui ci siano più pedine dello stesso colore
		}
		for(Player p : getBillboard().getPlayers()){
			players.add(p);
			getBillboard().getPlayers().remove(p);
		}
		getBillboard().setPlayers(players);
	}
	
}

package it.polimi.ingsw.ps03.actions;

import java.util.Map;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.Player;
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
		//libero tutte le posizioni sul tabellone
	}
	public void newPawns(){
		for(Player pl : getBillboard().getPlayers()){
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
	
}

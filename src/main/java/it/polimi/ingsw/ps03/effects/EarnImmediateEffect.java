package it.polimi.ingsw.ps03.effects;


import java.io.Serializable;


import java.util.Map;

import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

/**
 * this class represents the immediate effect  Earn, that gives resources relying on the number of card of a specific color or the number of military points
 * @author Amministratore
 *
 */

public class EarnImmediateEffect extends Effect implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Resources earnResources;
	private TowerColor earnCardColor; 
	
	public EarnImmediateEffect(TowerColor eCardColor, Resources eResources, String name){
		super(name);
		earnResources = eResources ;
		earnCardColor = eCardColor;
	}
	public Resources getEarnResources(){
		return earnResources;
	}
	public TowerColor getEarnTowerColor() {
		return earnCardColor;
	}
	/**
	 * this method prints the description of the effect Earn
	 * @return
	 */
	public String effectType(){
		if(earnCardColor != null){
			return "In base al numero di carte " + earnCardColor.toString().toLowerCase() + " possedute"; 
		}
		else{
			return "Ogni due punti militari posseduti";
		}
	}
	/**
	 * this method applies the effect Earn to the Player, takes the resource and gives them to the Player.
	 */
	@Override
	public void applyEffect(Player player){
		int counter = 0;
		if(earnCardColor != null){
			for(DevelopmentCard d : player.getCards()){
				if(d.getCardColor() == earnCardColor){
					counter++;
				}
			}
		}
		else{
			counter = (player.getResources().getResource("MILITARYPOINTS").getValue())/2;
		}
		for(Map.Entry<String, Resource> entry: player.getResources().getResourcesMap().entrySet()){
			entry.getValue().setValue(entry.getValue().getValue()*counter);
		}
	}
	
	@Override
	public String toString(){
		return "Nome effetto: " + getName() + "\nRisorse date: " + earnResources + effectType();
	}
}

package it.polimi.ingsw.ps03.finalcount;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
/**
 * this class counts the final victory points for each player
 *
 */
public class FinalCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TowerColor cardColor;
	private List<Resources> givenResources;
	
	public FinalCount(TowerColor color, List<Resources> resources){
		cardColor = color;
		givenResources = resources;
	}
	/**
	 * this method turns the development cards owned by a player into victory points
	 * @param cards
	 * @return
	 */
	public Resources giveVictoryPoints(List<DevelopmentCard> cards){
		int counter = -1;
		for(DevelopmentCard d: cards){
			if(d.getCardColor() == cardColor){
				counter++;
			}
		}
		if(counter == -1){
			return new Resources();
		}
		return givenResources.get(counter);
	}
	
	public List<Resources> getResources(){
		return givenResources;
	}
}

package it.polimi.ingsw.ps03.players_pack;

import it.polimi.ingsw.ps03.development_card.*;
import it.polimi.ingsw.ps03.resource_pack.*;
import java.util.ArrayList;


public class Bridge {
	
	private Resource resources;
	//private LeftSideEffect leftSideEffect;
	private ArrayList<DevelopmentCard> bridgeCards;
	
	//COSTRUTTORE
	public Bridge(Resource initialResources){
		this.resources = initialResources; //perchè le monete all'inizio dipendono dal turno del giocatore
		this.bridgeCards = new ArrayList<DevelopmentCard>();
	}
	
	//METODI GET
	public Resource getResources(){
		return this.resources;
	}
	
	//public DevelopmentCard getCards(); 
	
	public void printBridgeCards(){
		for(DevelopmentCard dC:bridgeCards){
			dC.toString();
		}
	}

	
	public void addResources(Resource resources){ //probabilmente non tanto estendibile
		this.resources.getCoins().addValue(resources.getCoins().getValue());
		this.resources.getWoods().addValue(resources.getWoods().getValue());
		this.resources.getStones().addValue(resources.getStones().getValue());
		this.resources.getServants().addValue(resources.getServants().getValue());
	}
	public void stealResources(Resource resources){ //controllare che risorse non vadano sotto 0
		this.resources.getCoins().stealValue(resources.getCoins().getValue());
		this.resources.getWoods().stealValue(resources.getWoods().getValue());
		this.resources.getStones().stealValue(resources.getStones().getValue());
		this.resources.getServants().stealValue(resources.getServants().getValue());
	}
	public void addCardToBridge(DevelopmentCard developmentCard){
		bridgeCards.add(developmentCard);
	}
	
}

package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.resources.*;

public class PlaceImmediateEffect extends GiveResourcesImmediateEffect{


	private int diceValue;
	TowerColor  cardColor;
	
	Resources discount;
	
	public PlaceImmediateEffect(TowerColor color, int diceValue, Resources givenResources, Resources discount, String name){
		super(givenResources, name);
		this.diceValue = diceValue;
		this.cardColor = color;
		this.discount = discount;
		
	}
	
	public TowerColor getPlaceTowerColor(){
		return cardColor;
	}
	public int getPlaceDiceValue(){
		return diceValue;
	}
	public Resources getDiscount(){
		return discount;
	}
	
	@Override
	public String toString(){
		return "Nome effetto: " + getName() + "\nValore azione: " + diceValue + "\tColore torre dell'azione: " + cardColor + 
				"\nRisorse date: " + getGivenResources() + "\nSconto applicato: " + discount;
	}
	
	
/*	private void buildChoosablePlaces(List<Room> placesToChoose, TowerColor color){
		Iterator<TowerRoom> it = Table.getTowerRoomList().iterator();
		while(it.hasNext()){
			if(it.next().getTowerRoomColor() == color && it.next().isFull() == false){
				placesToChoose.add(it.next());
			}
		}
	}
*/	
}

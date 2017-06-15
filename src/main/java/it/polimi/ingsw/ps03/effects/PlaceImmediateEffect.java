package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.resources.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PlaceImmediateEffect extends Effect{


	private int pDiceValue;
	TowerColor  pCardColor;
	Resources pResources;
	
	public PlaceImmediateEffect(TowerColor color, int dValue, Resources resources){

		pDiceValue = dValue;
		pCardColor = color;
		pResources = resources;
		
	}
	
	public TowerColor getPlaceTowerColor(){
		return pCardColor;
	}
	

	public int getPlaceDiceValue(){
		return pDiceValue;
	}
	public Resources getDiscount(){
		return pResources;
	}
	
	
	private void buildChoosablePlaces(List<Room> placesToChoose, TowerColor color){
		Iterator<TowerRoom> it = Table.getTowerRoomList().iterator();
		while(it.hasNext()){
			if(it.next().getTowerRoomColor() == color && it.next().isFull() == false){
				placesToChoose.add(it.next());
			}
		}
	}
	
}

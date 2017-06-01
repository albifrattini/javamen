package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.resources.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PlaceImmediateEffect extends Effect{

	private List<Room> placesToChoose;
	private int diceValue;
	private Resources discount;
	
	public PlaceImmediateEffect(TowerColor color, int dValue, Resources discRes){
		placesToChoose = new ArrayList<Room>();
		buildChoosablePlaces(placesToChoose, color);
		diceValue = dValue;
		discount = discRes;
	}
	
	public List<Room> getChoosablePlace(){
		return placesToChoose;
	}
	public Room getRoom(int choice){
		return placesToChoose.get(choice);
	}
	public int getDiceValue(){
		return diceValue;
	}
	public Resources getDiscount(){
		return discount;
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

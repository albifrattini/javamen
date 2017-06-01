package it.polimi.ingsw.ps03;

import it.polimi.ingsw.ps03.development_card.DevelopmentCards;
import it.polimi.ingsw.ps03.room_pack.Table;

public class Main {
	
	
	public static void main(String[] args){
		Table prova = new Table();
		Table.buildTowerRooms();
		
		try{
			for (int i = 0; i < Table.TOWER_ROOM_SPACES; i++) {
			if(Table.getTowerRoomList().get(i) == null) throw new IndexOutOfBoundsException("Valori terminati");
		
			System.out.println("\nColor: " + Table.getTowerRoomList().get(i).getTowerRoomColor());
			System.out.println("Requirement: " + Table.getTowerRoomList().get(i).getRequirement());
			System.out.println("GivenBonus: \n\tCoins: " + Table.getTowerRoomList().get(i).getResources().getResource("COINS").getValue()
					+ "\tWoods: " + Table.getTowerRoomList().get(i).getResources().getResource("WOODS").getValue() 
					+ "\tStones: " + Table.getTowerRoomList().get(i).getResources().getResource("STONES").getValue()
					+ "\tMilitary Points: " + Table.getTowerRoomList().get(i).getResources().getResource("MILITARYPOINTS").getValue());
			}
			
			
			
			
		}catch(IndexOutOfBoundsException e){
			e.getMessage();
		}
	}
}

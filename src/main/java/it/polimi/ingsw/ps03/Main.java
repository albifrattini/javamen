package it.polimi.ingsw.ps03;


import it.polimi.ingsw.ps03.room_pack.Table;

public class Main {
	
	
	public static void main(String[] args){
		Table prova = new Table();
		prova.buildTowerRooms();
		
		//Excommunications exc = new Excommunications();
		//exc.buildExcomm();
		
		try{
//			for (int i = 0; i < Table.TOWER_ROOM_SPACES; i++) {
//				if(Table.getTowerRoomList().get(i) == null) throw new IndexOutOfBoundsException("Valori terminati");
//		
//				System.out.println("\nColor: " + Table.getTowerRoomList().get(i).getTowerRoomColor());
//				System.out.println("Requirement: " + Table.getTowerRoomList().get(i).getRequirement());
//				System.out.println("GivenBonus: \n\tCoins: " + Table.getTowerRoomList().get(i).getResources().getResource("COINS").getValue()
//					+ "\tWoods: " + Table.getTowerRoomList().get(i).getResources().getResource("WOODS").getValue() 
//					+ "\tStones: " + Table.getTowerRoomList().get(i).getResources().getResource("STONES").getValue()
//					+ "\tMilitary Points: " + Table.getTowerRoomList().get(i).getResources().getResource("MILITARYPOINTS").getValue());
//			}
			

		/*	for(int i = 0; i < Excommunications.getExcommunicationsList().size(); i++){
				if (Excommunications.getExcommunicationsList().get(i) == null) throw new IndexOutOfBoundsException("Valori Terminati!");
				
				System.out.println("Excommunication:\nID: " + Excommunications.getExcommunicationsList().get(i).getId()
						+ "\nperiod: " + Excommunications.getExcommunicationsList().get(i).getExcommunicationPeriod() + 
						"\neffetto: " + Excommunications.getExcommunicationsList().get(i).getExcommunicationEffect());
	
			}*/
	
		}catch(IndexOutOfBoundsException e){
			e.getMessage();
		}
	}
}

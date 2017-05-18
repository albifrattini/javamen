package it.polimi.ingsw.ps03.room_pack;

import java.util.ArrayList;

public class Table {
	
	private static final int PH_SPACES = 4;
	private static final int MARKET_ROOM_SPACES = 4;
	private static final int TOWER_ROOM_SPACES = 16;
	private ArrayList<TowerRoom> towerRooms = 
			new ArrayList<TowerRoom>(TOWER_ROOM_SPACES);
	private ArrayList<MarketRoom> market = 
			new ArrayList<MarketRoom>(MARKET_ROOM_SPACES);
	private ArrayList<HarvestingRoom> harvestingRooms = 
			new ArrayList<HarvestingRoom>(PH_SPACES);
	private ArrayList<ProductionRoom> productionRooms = 
			new ArrayList<ProductionRoom>(PH_SPACES);
	private ArrayList<CouncilRoom> councilPalace =
			new ArrayList<CouncilRoom>();
	//voglio rendere il primo spazio di arraylist per production e harvesting disponibile
	//con requirement=1, mentre gli spazi 2-4 con requirement=4 e tolgo 3 punti al pedone
	//Ho inoltre messo 4 spazi in arraylist perchè non potendo posizionare più volte
	//un pedone dello stesso colore avremo al massimo 4 spazi occupati
	
	public Table(){
		
	}

	
	

}

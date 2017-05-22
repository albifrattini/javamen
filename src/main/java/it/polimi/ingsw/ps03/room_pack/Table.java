package it.polimi.ingsw.ps03.room_pack;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.points_pack.*;
import it.polimi.ingsw.ps03.resource_pack.*;

//import it.polimi.ingsw.ps03.resource_pack.Bonus;

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


	
	public void buildTowerRooms(){
		
		int coins = 0;
		int woods = 0;
		int stones = 0;
		int servants = 0;
		int militaryPoints = 0;
		TowerColor color = TowerColor.GREEN;
		int requirement = 0; 
		
		try{
			
			File towerRoomsXml = new File("/Users/alberto/prova-finale-template/src/main/java/it/polimi/ingsw/ps03/"
					+ "tower_rooms_arraylist_2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document docTowerRooms = dBuilder.parse(towerRoomsXml);
		
			docTowerRooms.getDocumentElement().normalize();
			docTowerRooms.getDocumentElement().getNodeName();

			NodeList towerRoomList = docTowerRooms.getElementsByTagName("towerRoom");
		
			for(int i = 0; i < towerRoomList.getLength(); i++){
				Node towerRoom = towerRoomList.item(i);
				
				if(towerRoom.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) towerRoom;
					String colore = (String) eElement.getElementsByTagName("color").item(0).getTextContent().toUpperCase();
					color = TowerColor.valueOf(colore);
					String require = (String) eElement.getElementsByTagName("requirement").item(0).getTextContent();
					requirement = Integer.parseInt(require);
					String coin = (String) eElement.getElementsByTagName("coins").item(0).getTextContent();
					coins = Integer.parseInt(coin);
					String wood = (String) eElement.getElementsByTagName("woods").item(0).getTextContent();
					woods = Integer.parseInt(wood);
					String stone = (String) eElement.getElementsByTagName("stones").item(0).getTextContent();
					stones = Integer.parseInt(stone);
					String servant = (String) eElement.getElementsByTagName("servants").item(0).getTextContent();
					servants = Integer.parseInt(servant);
					String militaryPoint = (String) eElement.getElementsByTagName("militaryPoints").item(0).getTextContent();
					militaryPoints = Integer.parseInt(militaryPoint);
				}
				
				Resource resource = new Resource(
						new Coins(coins), new Woods(woods),
						new Stones(stones), new Servants(servants));
				MilitaryPoints mPoints = new MilitaryPoints(militaryPoints);
				Bonus gBonus = new Bonus(resource, mPoints);
				TowerRoom nTRoom = new TowerRoom(color, requirement, gBonus);
				towerRooms.add(i, nTRoom);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	




}

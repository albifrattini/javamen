package it.polimi.ingsw.ps03.room_pack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.resources.*;


public class Table {
	
	public static final int PH_SPACES = 4;
	public static final int TOWER_ROOM_SPACES = 16;
	private static List<TowerRoom> towerRooms;
	private static List<MarketRoom> marketRooms;
	private static List<HarvestingRoom> harvestingRooms;
	private static List<ProductionRoom> productionRooms;
	private static List<CouncilRoom> councilPalace;
	
	//voglio rendere il primo spazio di arraylist per production e harvesting disponibile
	//con requirement=1, mentre gli spazi 2-4 con requirement=4 e tolgo 3 punti al pedone
	//Ho inoltre messo 4 spazi in arraylist perchè non potendo posizionare più volte
	//un pedone dello stesso colore avremo al massimo 4 spazi occupati
	
	
	
	
	
	public Table(){
		towerRooms = new ArrayList<TowerRoom>(TOWER_ROOM_SPACES);
		marketRooms = new ArrayList<MarketRoom>(PH_SPACES);
		harvestingRooms = new ArrayList<HarvestingRoom>(PH_SPACES);
		productionRooms = new ArrayList<ProductionRoom>(PH_SPACES);
		councilPalace = new ArrayList<CouncilRoom>();
	}

	public static List<TowerRoom> getTowerRoomList(){
		return towerRooms;
	}
	public static List<MarketRoom> getMarketRoomList(){
		return marketRooms;
	}
	public static List<HarvestingRoom> getHarvestingRoomList(){
		return harvestingRooms;
	}
	public static List<ProductionRoom> getProductionRoomList(){
		return productionRooms;
	}
	public static List<CouncilRoom> getCouncilPalaceList(){
		return councilPalace;
	}
	
	public static void buildTowerRooms(){
		try{
			File towerRoomsXml = new File("./src/tower_rooms_arraylist.xml");
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
					TowerColor color = TowerColor.valueOf(colore);
					String require = (String) eElement.getElementsByTagName("requirement").item(0).getTextContent();
					int requirement = Integer.parseInt(require);
					String coin = (String) eElement.getElementsByTagName("coins").item(0).getTextContent();
					int coins = Integer.parseInt(coin);
					String wood = (String) eElement.getElementsByTagName("woods").item(0).getTextContent();
					int woods = Integer.parseInt(wood);
					String stone = (String) eElement.getElementsByTagName("stones").item(0).getTextContent();
					int stones = Integer.parseInt(stone);
					String militaryPoint = (String) eElement.getElementsByTagName("militaryPoints").item(0).getTextContent();
					int militaryPoints = Integer.parseInt(militaryPoint);
				
					Resources xmlResource = new Resources();
					xmlResource.getResource("COINS").add(coins);
					xmlResource.getResource("WOODS").add(woods);
					xmlResource.getResource("STONES").add(stones);
					xmlResource.getResource("MILITARYPOINTS").add(militaryPoints);
					towerRooms.add(i, new TowerRoom(color, requirement, xmlResource));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void buildMarketRooms(int numberOfPlayers){
		try{
			File marketRoomsXml = new File("./src/marketRoom_arraylist.xml");
			Document docMarketRooms = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(marketRoomsXml);
		
			docMarketRooms.getDocumentElement().normalize();
			docMarketRooms.getDocumentElement().getNodeName();

			NodeList marketRoomList = docMarketRooms.getElementsByTagName("marketRoom");
			int roomCounter = 4;
			if(numberOfPlayers < 4){
				roomCounter = 2;
			}
			for(int i = 0; i < roomCounter; i++){
				Node towerRoom = marketRoomList.item(i);
				
				if(towerRoom.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) towerRoom;
					String coin = (String) eElement.getElementsByTagName("coins").item(0).getTextContent();
					int coins = Integer.parseInt(coin);
					String servant = (String) eElement.getElementsByTagName("servants").item(0).getTextContent();
					int servants = Integer.parseInt(servant);
					String militaryPoint = (String) eElement.getElementsByTagName("militaryPoints").item(0).getTextContent();
					int militaryPoints = Integer.parseInt(militaryPoint);
//					String cPrivilege = (String) eElement.getElementsByTagName("councilPrivilege").item(0).getTextContent();
//					int cPrivileges = Integer.parseInt(cPrivilege);
					
					Resources xmlResource = new Resources();
					xmlResource.getResource("COINS").add(coins);
					xmlResource.getResource("SERVANTS").add(servants);
					xmlResource.getResource("MILITARYPOINTS").add(militaryPoints);
					marketRooms.add(i, new MarketRoom(xmlResource));

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void buildProductionRooms(int numberOfPlayers){
		int roomCounter = 4;
		if(numberOfPlayers < 3){
			roomCounter = 1;
		}
		for(int i = 0; i < roomCounter; i++){
			productionRooms.add(i, new ProductionRoom());
		}
	}
	
	public static void buildHarvestingRooms(int numberOfPlayers){
		int roomCounter = 4;
		if(numberOfPlayers < 3){
			roomCounter = 1;
		}
		for(int i = 0; i < roomCounter; i++){
			harvestingRooms.add(i, new HarvestingRoom());
		}
	}
	
	public static void buildCouncilRoom(){
		
	}

	public static void buildTable(int numberOfPlayers){
		buildTowerRooms();
		buildMarketRooms(numberOfPlayers);
		buildHarvestingRooms(numberOfPlayers);
		buildProductionRooms(numberOfPlayers);
		buildCouncilRoom();
	}



}

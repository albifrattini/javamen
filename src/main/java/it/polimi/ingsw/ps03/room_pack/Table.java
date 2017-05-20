package it.polimi.ingsw.ps03.room_pack;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		//lettura da file .json per costruire gli spazi azione
	}


	
	public void buildTowerRooms(){
		
		try{
			
			//apro il file in un oggetto di tipo file per fare operazioni su di esso
			File towerRoomsXml = new File("/Users/alberto/prova-finale-template/src/main/java/it/polimi/ingsw/ps03"); 
			/*definisce una api factory che permette all'applicazione di ottenere un parser
			 *che produce alberi oggetti da un documento xml
			 *uso .newInstance() perchè il costruttore è protected
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			/*crea un nuovo oggetto di tipo DocumentBuilder
			 *una volta che è stato istanziato, xml può essere parsato
			 *in modi diversi di input
			 */
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			/*parsa il contenuto del file come un documento xml e ritorna 
			 *un nuovo oggetto DOM Document
			 */
			Document docTowerRooms = dBuilder.parse(towerRoomsXml);
			
			docTowerRooms.getDocumentElement().normalize();
			
			//printa a video il "titolo"
			//System.out.println("Root Element: " + docTowerRooms.getDocumentElement().getNodeName());
			//crea una lista di nodi che hanno come iniziale "towerRoom"
			NodeList towerRoomList = docTowerRooms.getElementsByTagName("towerRoom");
			
			
			for(int i = 0; i < towerRoomList.getLength(); i++){
				
				Node towerRoom = towerRoomList.item(i);
				
				if(towerRoom.getNodeType() == Node.ELEMENT_NODE){
				
				
				Element eElement = (Element) towerRoom;
				String colore = (String) eElement.getElementsByTagName("color").item(0).getTextContent().toUpperCase();
				TowerColor color = TowerColor.valueOf(colore);
				String require = (String) eElement.getElementsByTagName("requirement").item(0).getTextContent();
				int requirement = Integer.parseInt(require);
				String givenBonus = (String) eElement.getElementsByTagName("givenBonus").item(0).getTextContent().toUpperCase();
				
				
				TowerRoom nTRoom = new TowerRoom(color, requirement, givenBonus);
				towerRooms.add(i, nTRoom);

				}
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			try {
				
				
				for (int i = 0; i < 16; i++) {
					if(towerRooms.get(i) == null) throw new IndexOutOfBoundsException("Valori terminati");
				
					System.out.println("Color: " + towerRooms.get(i).getColor());
					System.out.println("Requirement: " + towerRooms.get(i).getRequirement());
				}
					
			} catch (IndexOutOfBoundsException e){
				e.getMessage();
			}
	}
	




}

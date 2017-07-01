package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.networking.model.Outcome;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.*;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Billboard extends Observable implements Cloneable{

	private Dices dices;
	private List<Player> players;
	private Table table;
	private TurnOfPlay turnOfPlay;
<<<<<<< HEAD
	private List<Resources> councilPrivilegesChange;
	private Map<Player, Outcome> outcomes = new HashMap<>();//aggiunto ma da rivedere
=======

>>>>>>> 9b5f74046ea4817a27d908585f4aaa13a4049d23
	
	public Billboard(){
		players = new ArrayList<Player>(4);
		dices = new Dices();
		table = new Table();
		turnOfPlay = new TurnOfPlay();
		councilPrivilegesChange = createList();
	}
	
	
	public void addPlayer(int order, PlayerColor color, int initialCoins){
		Player player = new Player(color, initialCoins);
		players.add(order, player);
	}
	
	public Dices getDices(){
		return dices;
	}
	public TurnOfPlay getTurnOfPlay(){
		return turnOfPlay;
	}
	public Table getTable(){
		return table;
	}
	public List<Player> getPlayers(){
		return players;
	}
	public Player getPlayerOfColor(PlayerColor color){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getColor() == color){
				return players.get(i);
			}
		}
		throw new NullPointerException("Errore nella conversione giocatori per turno successivo!");
	}
	public List<Resources> getCouncilChoices(){
		return councilPrivilegesChange;
	}
	public void setPlayers(List<Player> players){
		this.players = players;
	}
<<<<<<< HEAD
	
	public /*Outcome*/ String getOutcome(String player) {//aggiunto ma da rivedere
		return outcomes.get(player).toString();
	}
	
	public List<Resources> createList(){
		List<Resources> list = new ArrayList<Resources>();
		try{
			File councilXml = new File("./src/council_privileges_change.xml");
			Document docCouncil = DocumentBuilderFactory.newInstance().
										newDocumentBuilder().parse(councilXml);
		
			docCouncil.getDocumentElement().normalize();
			docCouncil.getDocumentElement().getNodeName();

			NodeList councilList = docCouncil.getElementsByTagName("Resources");
		
			for(int i = 0; i < councilList.getLength(); i++){
				Node councilResource = councilList.item(i);
				
				if(councilResource.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) councilResource;
					
					Resources resources = new Resources();
					readResources(element, resources);
					list.add(resources);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private int readIntFromFile(Element element, String intName){
		String read = readStringFromFile(element, intName);
		int readInt = Integer.parseInt(read);
		return readInt;
	}
	private String readStringFromFile(Element element, String stringName){
		return (String) element.getElementsByTagName(stringName).item(0).getTextContent();
	}
	private void readResources(Element element, Resources resources){
		for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){;
			int value = readIntFromFile(element, entry.getKey().toLowerCase());
			resources.getResource(entry.getKey()).add(value);
		}
	}
	
	
	
	
	
	
	
	
	
	
=======
>>>>>>> 9b5f74046ea4817a27d908585f4aaa13a4049d23

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}

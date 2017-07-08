package it.polimi.ingsw.ps03.billboard_pack;

import it.polimi.ingsw.ps03.dices.*;
import it.polimi.ingsw.ps03.finalcount.FinalCount;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.*;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * this class permits to create a the billboard that will be used during the game
 * @author Amministratore
 *
 */
public class Billboard extends Observable implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dices dices;
	private List<Player> players;
	private Table table;
	private TurnOfPlay turnOfPlay;
	private List<Resources> councilPrivilegesChange;
	private List<FinalCount> finalPoints;
	
	public Billboard(){
		players = new ArrayList<Player>(4);
		dices = new Dices();
		table = new Table();
		turnOfPlay = new TurnOfPlay();
		councilPrivilegesChange = createList();
		finalPoints = finalPoints();
	}
	
/**
 * this method creates a new player 
 * @param order int that specifies the order of the player
 * @param name  string that specifies the nickName of the player
 * @param color PlayerColoro that specify the color of the player
 * @param initialCoins  int that specifies the initial coins of the player
 */

	public void addPlayerRemote(int order,String name, PlayerColor color, int initialCoins){
		Player player = new Player(name, color, initialCoins);
		players.add(order, player);
	}
	/**
	 * this method creates a new player ( not using the Network, so the NickName)
	 * @param order int that specifies the order of the player
	 * @param color PlayerColoro that specify the color of the player
	 * @param initialCoins  int that specifies the initial coins of the player
	 */

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
	
	//perchè 5? cosa fa?
	public List<Resources> getCouncilChoices(){
		return councilPrivilegesChange;
	}
	public List<FinalCount> getFinalPoints(){
		return finalPoints;
	}
	
	// setta le risorse council di un giocatore
	public void setPlayers(List<Player> players){
		this.players = players;
	}
	/**
	 * create the list of resources owned by the player
	 * @return the list of resources
	 */
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
			System.out.println("[WARNING]  Problema con caricamento cambi del consiglio");
		}
		return list;
	}
	
	public List<FinalCount> finalPoints(){
		List<FinalCount> finals = new ArrayList<FinalCount>();
		finals.add(createFinalList(TowerColor.GREEN, "green"));
		finals.add(createFinalList(TowerColor.BLUE, "blue"));
		return finals;
	}
	
	public FinalCount createFinalList(TowerColor color, String type){
		List<Resources> list = new ArrayList<Resources>();
		try{
			File finalXml = new File("./src/final_points_" + type + ".xml");
			Document docFinal = DocumentBuilderFactory.newInstance().
										newDocumentBuilder().parse(finalXml);
		
			docFinal.getDocumentElement().normalize();
			docFinal.getDocumentElement().getNodeName();

			NodeList finalList = docFinal.getElementsByTagName("Resources");
		
			for(int i = 0; i < finalList.getLength(); i++){
				Node finalResources = finalList.item(i);
				
				if(finalResources.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) finalResources;
					
					Resources resources = new Resources();
					readResources(element, resources);
					list.add(resources);
				}
			}
		}catch(Exception e){
			System.out.println("[WARNING]  Problema con caricamento cambi del consiglio");
		}
		FinalCount finalCount = new FinalCount(color, list);
		return finalCount;
	}
	
/**
 * this method is used to read a Int from a file
 * @param element Element 
 * @param intName string of the int that we want to read
 * @return int read from the file
 */
	private int readIntFromFile(Element element, String intName){
		String read = readStringFromFile(element, intName);
		int readInt = Integer.parseInt(read);
		return readInt;
	}
	
	/**
 * this method is used to read a String from a file
	 * @param element
	 * @param stringName String that shows the String we want to read frome the file
	 * @return String read form the file
	 */
	private String readStringFromFile(Element element, String stringName){
		return (String) element.getElementsByTagName(stringName).item(0).getTextContent();
	}
	
	/**
	 * this method is used to read a Resources from a file
	 * @param element
	 * @param resources Resources that rapresents the Resources we want to read
	 */
	private void readResources(Element element, Resources resources){
		for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){;
			int value = readIntFromFile(element, entry.getKey().toLowerCase());
			resources.getResource(entry.getKey()).add(value);
		}
	}
	
	
	
	
	
	
	
	
	// crea una copia della classe billboard
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}

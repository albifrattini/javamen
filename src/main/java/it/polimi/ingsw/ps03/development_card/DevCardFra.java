package it.polimi.ingsw.ps03.development_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.resources.MilitaryPoints;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.resources.VictoryPoints;
import it.polimi.ingsw.ps03.effects.*;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DevCardFra {

	private static final int STANDARD_SIZE = 96;
	private static List<DevelopmentCard> developmentCards;
	
	//COSTRUTTORE
	public DevCardFra(){
		this(STANDARD_SIZE);
	}

	public DevCardFra(int deckLength){
		developmentCards = new ArrayList<DevelopmentCard>(deckLength);
	}
	
	//METODI GET
	public List<DevelopmentCard> getCardsList(){
		return developmentCards;
	}
	
	public List<DevelopmentCard> getCardsOfColor(TowerColor color, List<DevelopmentCard> developmentCards){
		List<DevelopmentCard> deck = new ArrayList<DevelopmentCard>();
		for(int i = 0; i < STANDARD_SIZE; i++){
			if(developmentCards.get(i).getCardColor() == color){
				deck.add(developmentCards.get(i));
			}
		}
		return deck;
	}
	
	public List<DevelopmentCard> getCardsOfPeriod(int cardPeriod, List<DevelopmentCard> developmentCards){
		List<DevelopmentCard> deck = new ArrayList<DevelopmentCard>();
		for(int i = 0; i < STANDARD_SIZE; i++){
			developmentCards.get(i);
			if(developmentCards.get(i).getCardPeriod() == cardPeriod){
				deck.add(developmentCards.get(i));
			}
		}
		return deck;
	}
	
	public DevelopmentCard getRandomCard(List<DevelopmentCard> developmentCards){
		Random random = new Random();
		int choice = random.nextInt(developmentCards.size()-1);
		return developmentCards.get(choice);
	}
	
	//se per esempio volessi una carta di un determinato valore e di un determinato periodo farei una richiesta del genere:
	//DevelopmentCard dC = getRandomCard(getCardsOfPeriod(periodo, getCardsOfColor(colore, getCardsList())));
	
	
	// albi io mi sono concentrato sul BUILDER, ho provato a tenere come base quello che avevi già fatto in modo da 
	// non dovere modificare troppo codice in giro per le classi
	// il focus era creare la lista di carte, poi come gestire bene gli effetti lo dobbiamo scegliere insieme 
	// ho modiicato anche le classe yellowDev e BlueDev in modo che accettino un array list per cost
	
	//BUILDER
	public void build(){
		try{
			DevelopmentCard dC = null;
			Effect immediateEffect = null;
			
			File devCardsXml = new File("./src/devCard.xml");
			Document docDevCards = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(devCardsXml);
			
			docDevCards.getDocumentElement().normalize();
			docDevCards.getDocumentElement().getNodeName();
			
			NodeList devCardsList = docDevCards.getElementsByTagName("developmentCard");
			
			for(int i = 0; i < devCardsList.getLength(); i++){
				Node devCard = devCardsList.item(i);
				if(devCard.getNodeType() == Node.ELEMENT_NODE){
					Element cardEl = (Element) devCard;
					TowerColor pColor;
					List<Resources> costChoices = new ArrayList<Resources>();
					
					
					
					
					
					
					String tId = (String) cardEl.getElementsByTagName("id").item(0).getTextContent();
					int id = Integer.parseInt(tId);
					String cardName = (String) cardEl.getElementsByTagName("cardName").item(0).getTextContent();
					String tCardColor = (String) cardEl.getElementsByTagName("cardColor").item(0).getTextContent();
					TowerColor cardColor = TowerColor.valueOf(tCardColor);
					String tCardPeriod = (String) cardEl.getElementsByTagName("cardPeriod").item(0).getTextContent();
					int cardPeriod = Integer.parseInt(tCardPeriod);
					String tImmediateEffect = (String) cardEl.getElementsByTagName("immediateEffect").item(0).getTextContent();
					
					String hptDiceValue = (String) cardEl.getElementsByTagName("hpDiceValue").item(0).getTextContent();
					int hpDiceValue = Integer.parseInt(hptDiceValue);
					
					String htVictoryPoints = (String) cardEl.getElementsByTagName("hVictoryPoints").item(0).getTextContent();
					int hVictoryPoints = Integer.parseInt(htVictoryPoints);
			        String htColor = (String) cardEl.getElementsByTagName("hColor").item(0).getTextContent();
				    TowerColor hColor = TowerColor.valueOf(htColor);
					String htMilitaryPoints = (String) cardEl.getElementsByTagName("hMilitaryPoints").item(0).getTextContent();
					int hMilitaryPoints = Integer.parseInt(htMilitaryPoints);
					
					String ptDiceValue = (String) cardEl.getElementsByTagName("pDiceValue").item(0).getTextContent();
					int pDiceValue = Integer.parseInt(ptDiceValue);
					String ptColor = (String) cardEl.getElementsByTagName("pColor").item(0).getTextContent();
					if(ptColor == "EVERY"){
						pColor = null;
					}
					else{
						pColor = TowerColor.valueOf(ptColor);
					}	

	
						String tCoins = (String) cardEl.getElementsByTagName("gCoins").item(0).getTextContent();
						int coins = Integer.parseInt(tCoins);
						String tWoods = (String) cardEl.getElementsByTagName("gWoods").item(0).getTextContent();
						int woods = Integer.parseInt(tWoods);
						String tStones = (String) cardEl.getElementsByTagName("gStones").item(0).getTextContent();
						int stones = Integer.parseInt(tStones);
						String tServants = (String) cardEl.getElementsByTagName("gServants").item(0).getTextContent();
						int servants = Integer.parseInt(tServants);
						String tFaithPoints = (String) cardEl.getElementsByTagName("gFaithPoints").item(0).getTextContent();
						int faithPoints = Integer.parseInt(tFaithPoints);
						String tMilitaryPoints = (String) cardEl.getElementsByTagName("gMilitaryPoints").item(0).getTextContent();
						int militaryPoints = Integer.parseInt(tMilitaryPoints);

						String tVictoryPoints = (String) cardEl.getElementsByTagName("gVictoryPoints").item(0).getTextContent();
						int victoryPoints = Integer.parseInt(tVictoryPoints);
						String tCouncilPrivileges = (String) cardEl.getElementsByTagName("gCouncilPrivileges").item(0).getTextContent();
						int councilPrivileges = Integer.parseInt(tCouncilPrivileges);
						String choice = (String) cardEl.getElementsByTagName("check").item(0).getTextContent();
						int choices = Integer.parseInt(choice);
						if ( choices == 1)
						{ Resources cost = new Resources();
						String coinCardCost = (String) cardEl.getElementsByTagName("coinCardCost").item(0).getTextContent();
						int coinsCardCost = Integer.parseInt(coinCardCost);
						cost.getResource("COINS").add(coinsCardCost);
						String woodCardCost = (String) cardEl.getElementsByTagName("woodCardCost").item(0).getTextContent();
						int woodsCardCost = Integer.parseInt(woodCardCost);
						cost.getResource("WOODS").add(woodsCardCost);
						String stoneCardCost = (String) cardEl.getElementsByTagName("stoneCardCost").item(0).getTextContent();
						int stonesCardCost = Integer.parseInt(stoneCardCost);
						cost.getResource("STONES").add(stonesCardCost);
						String servantCardCost = (String) cardEl.getElementsByTagName("servantCardCost").item(0).getTextContent();
						int servantsCardCost = Integer.parseInt(servantCardCost);
						cost.getResource("SERVANTS").add(servantsCardCost);
						String militaryCardCost = (String) cardEl.getElementsByTagName("militaryCardCost").item(0).getTextContent();
						int militarysCardCost = Integer.parseInt(militaryCardCost);
						cost.getResource("MILITARYPOINTS").add(militarysCardCost); }
						if ( choices > 1) {
							for(int c = 0; c < choices; c++){
								Resources cost = new Resources();
								String coinCardCost = (String) cardEl.getElementsByTagName("coinCardCost").item(0).getTextContent();
								int coinsCardCost = Integer.parseInt(coinCardCost);
								cost.getResource("COINS").add(coinsCardCost);
								String woodCardCost = (String) cardEl.getElementsByTagName("woodCardCost").item(0).getTextContent();
								int woodsCardCost = Integer.parseInt(woodCardCost);
								cost.getResource("WOODS").add(woodsCardCost);
								String stoneCardCost = (String) cardEl.getElementsByTagName("stoneCardCost").item(0).getTextContent();
								int stonesCardCost = Integer.parseInt(stoneCardCost);
								cost.getResource("STONES").add(stonesCardCost);
								String servantCardCost = (String) cardEl.getElementsByTagName("servantCardCost").item(0).getTextContent();
								int servantsCardCost = Integer.parseInt(servantCardCost);
								cost.getResource("SERVANTS").add(servantsCardCost);
								String militaryCardCost = (String) cardEl.getElementsByTagName("militaryCardCost").item(0).getTextContent();
								int militarysCardCost = Integer.parseInt(militaryCardCost);
								cost.getResource("MILITARYPOINTS").add(militarysCardCost);
								String minMilitaryCardCost = (String) cardEl.getElementsByTagName("minMilitaryCardCost").item(0).getTextContent();
								int minMilitarysCardCost = Integer.parseInt(minMilitaryCardCost);
								costChoices.add(cost);
							}
							
						}
						String gtDiceValue = (String) cardEl.getElementsByTagName("diceValue").item(0).getTextContent();
						int gDiceValue = Integer.parseInt(gtDiceValue);
						
						switch(cardColor){
						
						case GREEN: 
							dC = new GreenDevelopmentCard(id, cardName, cardColor, cardPeriod, gDiceValue, immediateEffect);
						         developmentCards.add(dC);
						case YELLOW:
							dC = new YellowDevelopmentCard(id, cardName, cardColor, cardPeriod, gDiceValue, costChoices, immediateEffect);
							     developmentCards.add(dC); 
						case BLUE:
							dC = new BlueDevelopmentCard(id, cardName, cardColor, cardPeriod, costChoices, immediateEffect);
							     developmentCards.add(dC);
						case VIOLET:
							dC = new VioletDevelopmentCard(id, cardName, cardColor, cardPeriod, costChoices, immediateEffect);
							     developmentCards.add(dC);
						
				                       }
			} 
			}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
					
					
				
	
	
	private int readIntFromFile(Element cardEl, String intName){
		String read = readStringFromFile(cardEl, intName);
		int readInt = Integer.parseInt(read);
		return readInt;
	}
	private TowerColor readColorFromFile(Element cardEl, String colorName){
		String read = readStringFromFile(cardEl, colorName);
		TowerColor readColor = TowerColor.valueOf(read);
		return readColor;
	}
	private String readStringFromFile(Element cardEl, String stringName){
		return (String) cardEl.getElementsByTagName(stringName).item(0).getTextContent();
	}

	
	
	

}
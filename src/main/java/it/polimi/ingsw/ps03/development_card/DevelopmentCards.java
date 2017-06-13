package it.polimi.ingsw.ps03.development_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.resources.MilitaryPoints;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.resources.VictoryPoints;
import it.polimi.ingsw.ps03.effects.*;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DevelopmentCards {

	private static final int STANDARD_SIZE = 96;
	private static List<DevelopmentCard> developmentCards;
	
	//COSTRUTTORE
	public DevelopmentCards(){
		this(STANDARD_SIZE);
	}
	public DevelopmentCards(int deckLength){
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
	
	
	
	
	//BUILDER
	public void build(){
		try{
		Resources r = new Resources();
			DevelopmentCard dC = null;
			Effect immediateEffect = null;
			
			File devCardsXml = new File("./src/development_cards.xml");
			Document docDevCards = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(devCardsXml);
			
			docDevCards.getDocumentElement().normalize();
			docDevCards.getDocumentElement().getNodeName();
			
			NodeList devCardsList = docDevCards.getElementsByTagName("developmentCard");
			
			for(int i = 0; i < devCardsList.getLength(); i++){
				Node devCard = devCardsList.item(i);
				if(devCard.getNodeType() == Node.ELEMENT_NODE){
					Element cardEl = (Element) devCard;
					
					
					
					
					
					
					
					String tId = (String) cardEl.getElementsByTagName("id").item(0).getTextContent();
					int id = Integer.parseInt(tId);
					String cardName = (String) cardEl.getElementsByTagName("cardName").item(0).getTextContent();
					String tCardColor = (String) cardEl.getElementsByTagName("cardColor").item(0).getTextContent();
					TowerColor cardColor = TowerColor.valueOf(tCardColor);
					String tCardPeriod = (String) cardEl.getElementsByTagName("cardPeriod").item(0).getTextContent();
					int cardPeriod = Integer.parseInt(tCardPeriod);
					
					String tImmediateEffect = (String) cardEl.getElementsByTagName("immediateEffect").item(0).getTextContent();
					
					switch(tImmediateEffect){
					case "GIVE":
						Resources giveEffectResources = new Resources();
						String tCoins = (String) cardEl.getElementsByTagName("gCoins").item(0).getTextContent();
						int coins = Integer.parseInt(tCoins);
						giveEffectResources.getResource("COINS").add(coins);
						String tWoods = (String) cardEl.getElementsByTagName("gWoods").item(0).getTextContent();
						int woods = Integer.parseInt(tWoods);
						giveEffectResources.getResource("WOODS").add(woods);
						String tStones = (String) cardEl.getElementsByTagName("gStones").item(0).getTextContent();
						int stones = Integer.parseInt(tStones);
						giveEffectResources.getResource("STONES").add(stones);
						String tServants = (String) cardEl.getElementsByTagName("gServants").item(0).getTextContent();
						int servants = Integer.parseInt(tServants);
						giveEffectResources.getResource("SERVANTS").add(servants);
						String tFaithPoints = (String) cardEl.getElementsByTagName("gFaithPoints").item(0).getTextContent();
						int faithPoints = Integer.parseInt(tFaithPoints);
						giveEffectResources.getResource("FAITHPOINTS").add(faithPoints);
						String tMilitaryPoints = (String) cardEl.getElementsByTagName("gMilitaryPoints").item(0).getTextContent();
						int militaryPoints = Integer.parseInt(tMilitaryPoints);
						giveEffectResources.getResource("MILITARYPOINTS").add(militaryPoints);
						String tVictoryPoints = (String) cardEl.getElementsByTagName("gVictoryPoints").item(0).getTextContent();
						int victoryPoints = Integer.parseInt(tVictoryPoints);
						giveEffectResources.getResource("VICTORYPOINTS").add(victoryPoints);
						String tCouncilPrivileges = (String) cardEl.getElementsByTagName("gCouncilPrivileges").item(0).getTextContent();
						int councilPrivileges = Integer.parseInt(tCouncilPrivileges);
						giveEffectResources.getResource("COUNCILPRIVILEGES").add(councilPrivileges);
						
						immediateEffect = new GiveResourcesImmediateEffect(giveEffectResources);
						
					case "PLACE":
						TowerColor pColor;
						String ptDiceValue = (String) cardEl.getElementsByTagName("pDiceValue").item(0).getTextContent();
						int pDiceValue = Integer.parseInt(ptDiceValue);
						String ptColor = (String) cardEl.getElementsByTagName("pColor").item(0).getTextContent();
						if(ptColor == "EVERY"){
							pColor = null;
						}
						else{
							pColor = TowerColor.valueOf(ptColor);
						}
						Resources placeEffectDiscount = new Resources();
						String pltCoins = (String) cardEl.getElementsByTagName("pCoins").item(0).getTextContent();
						int plCoins = Integer.parseInt(pltCoins);
						placeEffectDiscount.getResource("COINS").add(plCoins);
						String pltWoods = (String) cardEl.getElementsByTagName("pWoods").item(0).getTextContent();
						int plWoods = Integer.parseInt(pltWoods);
						placeEffectDiscount.getResource("WOODS").add(plWoods);
						String pltStones = (String) cardEl.getElementsByTagName("pStones").item(0).getTextContent();
						int plStones = Integer.parseInt(pltStones);
						placeEffectDiscount.getResource("STONES").add(plStones);
						String pltServants = (String) cardEl.getElementsByTagName("pServants").item(0).getTextContent();
						int plServants = Integer.parseInt(pltServants);
						placeEffectDiscount.getResource("SERVANTS").add(plServants);
						String pltFaithPoints = (String) cardEl.getElementsByTagName("pFaithPoints").item(0).getTextContent();
						int plFaithPoints = Integer.parseInt(pltFaithPoints);
						placeEffectDiscount.getResource("FAITHPOINTS").add(plFaithPoints);
						String pltMilitaryPoints = (String) cardEl.getElementsByTagName("pMilitaryPoints").item(0).getTextContent();
						int plMilitaryPoints = Integer.parseInt(pltMilitaryPoints);
						placeEffectDiscount.getResource("MILITARYPOINTS").add(plMilitaryPoints);
						String pltVictoryPoints = (String) cardEl.getElementsByTagName("pVictoryPoints").item(0).getTextContent();
						int plVictoryPoints = Integer.parseInt(pltVictoryPoints);
						placeEffectDiscount.getResource("VICTORYPOINTS").add(plVictoryPoints);
						String pltCouncilPrivileges = (String) cardEl.getElementsByTagName("pCouncilPrivileges").item(0).getTextContent();
						int plCouncilPrivileges = Integer.parseInt(pltCouncilPrivileges);
						placeEffectDiscount.getResource("COUNCILPRIVILEGES").add(plCouncilPrivileges);
						
						immediateEffect = new PlaceImmediateEffect(pColor, pDiceValue, placeEffectDiscount);
					
					case "PLACEANDGIVE":
						TowerColor pgColor;
						String pgtDiceValue = (String) cardEl.getElementsByTagName("pgDiceValue").item(0).getTextContent();
						int pgDiceValue = Integer.parseInt(pgtDiceValue);
						String pgtColor = (String) cardEl.getElementsByTagName("pgColor").item(0).getTextContent();
						if(pgtColor == "EVERY"){
							pgColor = null;
						}
						else{
							pgColor = TowerColor.valueOf(pgtColor);
						}
						Resources placegiveEffectDiscount = new Resources();
						String plgtCoins = (String) cardEl.getElementsByTagName("pgCoins").item(0).getTextContent();
						int plgCoins = Integer.parseInt(plgtCoins);
						placegiveEffectDiscount.getResource("COINS").add(plgCoins);
						String plgtWoods = (String) cardEl.getElementsByTagName("pgWoods").item(0).getTextContent();
						int plgWoods = Integer.parseInt(plgtWoods);
						placegiveEffectDiscount.getResource("WOODS").add(plgWoods);
						String plgtStones = (String) cardEl.getElementsByTagName("pgStones").item(0).getTextContent();
						int plgStones = Integer.parseInt(plgtStones);
						placegiveEffectDiscount.getResource("STONES").add(plgStones);
						String plgtServants = (String) cardEl.getElementsByTagName("pgServants").item(0).getTextContent();
						int plgServants = Integer.parseInt(plgtServants);
						placegiveEffectDiscount.getResource("SERVANTS").add(plgServants);
						String plgtFaithPoints = (String) cardEl.getElementsByTagName("pgFaithPoints").item(0).getTextContent();
						int plgFaithPoints = Integer.parseInt(plgtFaithPoints);
						placegiveEffectDiscount.getResource("FAITHPOINTS").add(plgFaithPoints);
						String plgtMilitaryPoints = (String) cardEl.getElementsByTagName("pgMilitaryPoints").item(0).getTextContent();
						int plgMilitaryPoints = Integer.parseInt(plgtMilitaryPoints);
						placegiveEffectDiscount.getResource("MILITARYPOINTS").add(plgMilitaryPoints);
						String plgtVictoryPoints = (String) cardEl.getElementsByTagName("pgVictoryPoints").item(0).getTextContent();
						int plgVictoryPoints = Integer.parseInt(plgtVictoryPoints);
						placegiveEffectDiscount.getResource("VICTORYPOINTS").add(plgVictoryPoints);
						String plgtCouncilPrivileges = (String) cardEl.getElementsByTagName("pgCouncilPrivileges").item(0).getTextContent();
						int plgCouncilPrivileges = Integer.parseInt(plgtCouncilPrivileges);
						placegiveEffectDiscount.getResource("COUNCILPRIVILEGES").add(plgCouncilPrivileges);
						
						immediateEffect = new PlaceImmediateEffect(pgColor, pgDiceValue, placegiveEffectDiscount);
					
					case "HARVORPROD":
						String hptDiceValue = (String) cardEl.getElementsByTagName("hpDiceValue").item(0).getTextContent();
						int hpDiceValue = Integer.parseInt(hptDiceValue);
						String hpDecision = (String) cardEl.getElementsByTagName("hpDecision").item(0).getTextContent();
						
						Resources harvOrProdGiveEffect = new Resources();
						String hptCoins = (String) cardEl.getElementsByTagName("hpCoins").item(0).getTextContent();
						int hpCoins = Integer.parseInt(hptCoins);
						harvOrProdGiveEffect.getResource("COINS").add(hpCoins);
						String hptWoods = (String) cardEl.getElementsByTagName("hpWoods").item(0).getTextContent();
						int hpWoods = Integer.parseInt(hptWoods);
						harvOrProdGiveEffect.getResource("WOODS").add(hpWoods);
						String hptStones = (String) cardEl.getElementsByTagName("hpStones").item(0).getTextContent();
						int hpStones = Integer.parseInt(hptStones);
						harvOrProdGiveEffect.getResource("STONES").add(hpStones);
						String hptServants = (String) cardEl.getElementsByTagName("hpServants").item(0).getTextContent();
						int hpServants = Integer.parseInt(hptServants);
						harvOrProdGiveEffect.getResource("SERVANTS").add(hpServants);
						String hptFaithPoints = (String) cardEl.getElementsByTagName("hpFaithPoints").item(0).getTextContent();
						int hpFaithPoints = Integer.parseInt(hptFaithPoints);
						harvOrProdGiveEffect.getResource("FAITHPOINTS").add(hpFaithPoints);
						String hptMilitaryPoints = (String) cardEl.getElementsByTagName("hpMilitaryPoints").item(0).getTextContent();
						int hpMilitaryPoints = Integer.parseInt(hptMilitaryPoints);
						harvOrProdGiveEffect.getResource("MILITARYPOINTS").add(hpMilitaryPoints);
						String hptVictoryPoints = (String) cardEl.getElementsByTagName("hpVictoryPoints").item(0).getTextContent();
						int hpVictoryPoints = Integer.parseInt(hptVictoryPoints);
						harvOrProdGiveEffect.getResource("VICTORYPOINTS").add(hpVictoryPoints);
						String hptCouncilPrivileges = (String) cardEl.getElementsByTagName("hpCouncilPrivileges").item(0).getTextContent();
						int hpCouncilPrivileges = Integer.parseInt(hptCouncilPrivileges);
						harvOrProdGiveEffect.getResource("COUNCILPRIVILEGES").add(hpCouncilPrivileges);
						
						immediateEffect = new HarvestingOrProductionImmediateEffect(hpDiceValue, hpDecision, harvOrProdGiveEffect);
					
//					case "HEARN":
//						String htVictoryPoints = (String) cardEl.getElementsByTagName("hVictoryPoints").item(0).getTextContent();
//						int hVictoryPoints = Integer.parseInt(htVictoryPoints);
//						VictoryPoints multiplyValue = new VictoryPoints("VICTORYPOINTS", hVictoryPoints);	
//						String htColor = (String) cardEl.getElementsByTagName("hColor").item(0).getTextContent();
//						TowerColor hColor = TowerColor.valueOf(htColor);
//						String htMilitaryPoints = (String) cardEl.getElementsByTagName("hMilitaryPoints").item(0).getTextContent();
//						int hMilitaryPoints = Integer.parseInt(htMilitaryPoints);
//						MilitaryPoints dividingValue = new MilitaryPoints("MILITARYPOINTS", hMilitaryPoints);
//						
//						immediateEffect = new EarnImmediateEffect(multiplyValue, hColor, dividingValue);
						
					default:
						immediateEffect = null;
					}
					
					switch(cardColor){
					
					case GREEN:
						String gtDiceValue = (String) cardEl.getElementsByTagName("diceValue").item(0).getTextContent();
						int gDiceValue = Integer.parseInt(gtDiceValue);
						
						dC = new GreenDevelopmentCard(id, cardName, cardColor, cardPeriod, gDiceValue, immediateEffect);
						developmentCards.add(dC);
					
					case YELLOW:
						Resources yCost = new Resources();
						String ytDiceValue = (String) cardEl.getElementsByTagName("diceValue").item(0).getTextContent();
						int yDiceValue = Integer.parseInt(ytDiceValue);
						String yCoinCardCost = (String) cardEl.getElementsByTagName("coinCardCost").item(0).getTextContent();
						int yCoinsCardCost = Integer.parseInt(yCoinCardCost);
						yCost.getResource("COINS").add(yCoinsCardCost);
						String yWoodCardCost = (String) cardEl.getElementsByTagName("woodCardCost").item(0).getTextContent();
						int yWoodsCardCost = Integer.parseInt(yWoodCardCost);
						yCost.getResource("WOODS").add(yWoodsCardCost);
						String yStoneCardCost = (String) cardEl.getElementsByTagName("stoneCardCost").item(0).getTextContent();
						int yStonesCardCost = Integer.parseInt(yStoneCardCost);
						yCost.getResource("STONES").add(yStonesCardCost);
						String yServantCardCost = (String) cardEl.getElementsByTagName("servantCardCost").item(0).getTextContent();
						int yServantsCardCost = Integer.parseInt(yServantCardCost);
						yCost.getResource("SERVANTS").add(yServantsCardCost);
						String yMilitaryCardCost = (String) cardEl.getElementsByTagName("militaryCardCost").item(0).getTextContent();
						int yMilitarysCardCost = Integer.parseInt(yMilitaryCardCost);
						yCost.getResource("MILITARYPOINTS").add(yMilitarysCardCost);
						
						//dC = new YellowDevelopmentCard(id, cardName, cardColor, cardPeriod, yDiceValue, yCost, immediateEffect);
						developmentCards.add(dC);
					
					case BLUE:
						Resources bCost = new Resources();
						String bCoinCardCost = (String) cardEl.getElementsByTagName("coinCardCost").item(0).getTextContent();
						int bCoinsCardCost = Integer.parseInt(bCoinCardCost);
						bCost.getResource("COINS").add(bCoinsCardCost);
						String bWoodCardCost = (String) cardEl.getElementsByTagName("woodCardCost").item(0).getTextContent();
						int bWoodsCardCost = Integer.parseInt(bWoodCardCost);
						bCost.getResource("WOODS").add(bWoodsCardCost);
						String bStoneCardCost = (String) cardEl.getElementsByTagName("stoneCardCost").item(0).getTextContent();
						int bStonesCardCost = Integer.parseInt(bStoneCardCost);
						bCost.getResource("STONES").add(bStonesCardCost);
						String bServantCardCost = (String) cardEl.getElementsByTagName("servantCardCost").item(0).getTextContent();
						int bServantsCardCost = Integer.parseInt(bServantCardCost);
						bCost.getResource("SERVANTS").add(bServantsCardCost);
						String bMilitaryCardCost = (String) cardEl.getElementsByTagName("militaryCardCost").item(0).getTextContent();
						int bMilitarysCardCost = Integer.parseInt(bMilitaryCardCost);
						bCost.getResource("MILITARYPOINTS").add(bMilitarysCardCost);
						
						//dC = new BlueDevelopmentCard(id, cardName, cardColor, cardPeriod, bCost, immediateEffect);
						developmentCards.add(dC);
					
					case VIOLET:
						List<Resources> costChoices = new ArrayList<Resources>(2);
						String choice = (String) cardEl.getElementsByTagName("check").item(0).getTextContent();
						int choices = Integer.parseInt(choice);
						for(int c = 0; c < choices; c++){
							Resources vCost = new Resources();
							String vCoinCardCost = (String) cardEl.getElementsByTagName("coinCardCost").item(0).getTextContent();
							int vCoinsCardCost = Integer.parseInt(vCoinCardCost);
							vCost.getResource("COINS").add(vCoinsCardCost);
							String vWoodCardCost = (String) cardEl.getElementsByTagName("woodCardCost").item(0).getTextContent();
							int vWoodsCardCost = Integer.parseInt(vWoodCardCost);
							vCost.getResource("WOODS").add(vWoodsCardCost);
							String vStoneCardCost = (String) cardEl.getElementsByTagName("stoneCardCost").item(0).getTextContent();
							int vStonesCardCost = Integer.parseInt(vStoneCardCost);
							vCost.getResource("STONES").add(vStonesCardCost);
							String vServantCardCost = (String) cardEl.getElementsByTagName("servantCardCost").item(0).getTextContent();
							int vServantsCardCost = Integer.parseInt(vServantCardCost);
							vCost.getResource("SERVANTS").add(vServantsCardCost);
							String vMilitaryCardCost = (String) cardEl.getElementsByTagName("militaryCardCost").item(0).getTextContent();
							int vMilitarysCardCost = Integer.parseInt(vMilitaryCardCost);
							vCost.getResource("MILITARYPOINTS").add(vMilitarysCardCost);
							costChoices.add(vCost);
						}
						dC = new VioletDevelopmentCard(id, cardName, cardColor, cardPeriod, costChoices, immediateEffect);
						developmentCards.add(dC);
						
					default:
						dC = null;
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
	private void readResources(Element cardEl, Resources resources, String pre){
		for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){
			int value = readIntFromFile(cardEl, pre + entry.getKey().toLowerCase());
			resources.getResource(entry.getKey()).add(value);
		}
	}
	
	
	

}

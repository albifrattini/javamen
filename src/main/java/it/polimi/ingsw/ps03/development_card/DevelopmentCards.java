package it.polimi.ingsw.ps03.development_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
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
	public static List<DevelopmentCard> getCardsList(){
		return developmentCards;
	}
	
	public static List<DevelopmentCard> getCardsOfColor(TowerColor color, List<DevelopmentCard> developmentCards){
		List<DevelopmentCard> deck = new ArrayList<DevelopmentCard>();
		for(int i = 0; i < developmentCards.size(); i++){
			if(developmentCards.get(i).getCardColor() == color){
				deck.add(developmentCards.get(i));
			}
		}
		return deck;
	}
	
	public static List<DevelopmentCard> getCardsOfPeriod(int cardPeriod, List<DevelopmentCard> developmentCards){
		List<DevelopmentCard> deck = new ArrayList<DevelopmentCard>();
		for(int i = 0; i < developmentCards.size(); i++){
			developmentCards.get(i);
			if(developmentCards.get(i).getCardPeriod() == cardPeriod){
				deck.add(developmentCards.get(i));
			}
		}
		return deck;
	}
	
	public static DevelopmentCard getRandomCard(List<DevelopmentCard> developmentCards){
		Random random = new Random();
		int choice = random.nextInt(developmentCards.size());
		return developmentCards.get(choice);
	}
	

	
	//BUILDER
	public void build(){
		try{
			File devCardsXml = new File("./src/development_cards.xml");
			Document docDevCards = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(devCardsXml);
			
			docDevCards.getDocumentElement().normalize();
			docDevCards.getDocumentElement().getNodeName();

			NodeList devCardsList = docDevCards.getElementsByTagName("developmentCard");
			
			for(int i = 0; i < devCardsList.getLength(); i++){
				Node devCard = devCardsList.item(i);
				if(devCard.getNodeType() == Node.ELEMENT_NODE){
					Element cardEl = (Element) devCard;
					
					int id = readIntFromFile(cardEl,"id");
					int period = readIntFromFile(cardEl,"period");
					int diceValue = readIntFromFile(cardEl,"diceValue");
					String cardName = readStringFromFile(cardEl, "name");
					TowerColor cardColor = readColorFromFile(cardEl, "color");
					
					DevelopmentCard developmentCard = new DevelopmentCard(id, period, diceValue, cardName, cardColor);
					
					for(int j = 0; j < 2; j++){
						Resources resourcesCost = new Resources();
						readResources(cardEl, resourcesCost, "C" + String.valueOf(j));
						if(!(isFreeCard(resourcesCost)) || developmentCard.getCosts().size() < 1){
							developmentCard.getCosts().add(resourcesCost);
						}
					}
					for(int k = 0; k < 2; k++){
						Resources resourcesRequirements = new Resources();
						readResources(cardEl, resourcesRequirements, "R" + String.valueOf(k));
						if(!(isFreeCard(resourcesRequirements)) || developmentCard.getRequirements().size() < 1){
							developmentCard.getRequirements().add(resourcesRequirements);
						}
					}

					String choiceEffect = readStringFromFile(cardEl, "immediateEffect");
					
					Resources effectsResources = new Resources();
					switch(choiceEffect){
						case "GIVE":	
							readResources(cardEl, effectsResources, "G");
							developmentCard.setImmediateEffect(new GiveResourcesImmediateEffect(effectsResources, "GIVE"));
							break;
						case "PLACE":
							Resources discount = new Resources();
							readResources(cardEl, effectsResources, "G");
							TowerColor cColor = readColorFromFile(cardEl, "colorP");
							int placeDiceValue = readIntFromFile(cardEl, "dicevalueP");
							readResources(cardEl, discount, "P");
							developmentCard.setImmediateEffect(
									new PlaceImmediateEffect(cColor, placeDiceValue, effectsResources, discount, "PLACE"));
							break;
						case "EARN":
							TowerColor eColor = readColorFromFile(cardEl, "colorE");
							readResources(cardEl, effectsResources, "E");
							developmentCard.setImmediateEffect(
									new EarnImmediateEffect(eColor, effectsResources, "EARN"));
							break;
						case "HARVORPROD":
							readResources(cardEl, effectsResources, "G");
						
					 		TowerColor hpColor = readColorFromFile(cardEl, "colorHP");
					 		int hpDiceValue = readIntFromFile(cardEl, "dicevalueHP");
					 		developmentCard.setImmediateEffect(
					 				new HarvestingOrProductionImmediateEffect(hpDiceValue, hpColor, effectsResources, "HARVORPROD"));
					 		break;
						default:
							developmentCard.setImmediateEffect(null);
							break;
					}

					developmentCards.add(developmentCard);
					

					
					
				}
			}
					
		}catch(Exception e){
			System.out.println("Errore durante il caricamento delle carte in build()");
		}
				
	}
					
	
	private int readIntFromFile(Element element, String intName){
		String read = readStringFromFile(element, intName);
		int readInt = Integer.parseInt(read);
		return readInt;
	}
	private TowerColor readColorFromFile(Element element, String colorName){
		String read = readStringFromFile(element, colorName);
		TowerColor readColor = null;
		try{
			readColor = TowerColor.valueOf(read);
		}catch(IllegalArgumentException e){
			return readColor;
		}
		return readColor;
	}
	private String readStringFromFile(Element element, String stringName){
		return (String) element.getElementsByTagName(stringName).item(0).getTextContent();
	}
	private void readResources(Element element, Resources resources, String post){
		for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){;
			int value = readIntFromFile(element, entry.getKey().toLowerCase() + post);
			resources.getResource(entry.getKey()).add(value);
		}
	}
	private boolean isFreeCard(Resources resources){
		for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){
			if(resources.getResource(entry.getKey()).getValue() > 0){
				return false;
			}
		}
		return true;
	}
			
}
	


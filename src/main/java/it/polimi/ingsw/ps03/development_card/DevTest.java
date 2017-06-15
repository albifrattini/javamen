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

public class DevTest {
	private static final int STANDARD_SIZE = 96;
	private static List<DevelopmentCard> developmentCards;
	
	//COSTRUTTORE
	public DevTest(){
		this(STANDARD_SIZE);
	}
	public DevTest(int deckLength){
		developmentCards = new ArrayList<DevelopmentCard>(deckLength);
	}
	
	//BUILDER
	public void build(){
		try{
			
			
			File devCardsXml = new File("./src/development_card_final.xml");
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
					
					
					NodeList costCardsList = docDevCards.getElementsByTagName("cost");
					for(int cost = 0; cost < 2; cost ++){
						Node costCard = costCardsList.item(cost);
						Resources resourcesCost = new Resources();
						if(costCard.getNodeType() == Node.ELEMENT_NODE){
							Element costEl = (Element) costCard;
					
                            readResources(costEl, resourcesCost, "");
                            developmentCard.getCosts().add(resourcesCost);
							}
						}
					

							
					 NodeList reqCardsList = docDevCards.getElementsByTagName("requirement");
					 for(int requirement = 0; requirement < 2; requirement++){
						Node reqCard = reqCardsList.item(requirement);
						Resources resourcesRequirement = new Resources();
						if(reqCard.getNodeType() == Node.ELEMENT_NODE){
							Element reqEl = (Element) reqCard;
							
							readResources(reqEl, resourcesRequirement, "");
							developmentCard.getRequirements().add(resourcesRequirement);
							      }
								}
					 
					 String choice = readStringFromFile(cardEl, "immediateEffect");
					 
				 		Resources resourcesGive = new Resources();
				 		Resources resourcesPlace = new Resources();
				 		Resources resourcesEarn = new Resources();
				 		
					 switch(choice) {

					 	case "GIVE": 
					 		readResources(cardEl, resourcesGive, "g");
					 		developmentCard.setImmediateEffect(new GiveResourcesImmediateEffect(resourcesGive));
					 	
					 	case "PLACE":
					 		readResources(cardEl, resourcesGive, "g");
					 		developmentCard.setImmediateEffect(new GiveResourcesImmediateEffect(resourcesGive));
					 		
					 		TowerColor placeCardColor = readColorFromFile(cardEl, "pcolor");
					 		int placeDiceValue = readIntFromFile(cardEl, "pdicevalue");
					 		readResources(cardEl, resourcesPlace, "p");
					 		developmentCard.setImmediateEffect(new PlaceImmediateEffect(placeCardColor, placeDiceValue, resourcesPlace));
					 	
					 	case "EARN":
					 		TowerColor earnCardColor = readColorFromFile(cardEl, "ecolor");
					 		readResources(cardEl, resourcesEarn, "e");
					 		developmentCard.setImmediateEffect(new EarnImmediateEffect(earnCardColor, resourcesEarn));
					 		
					 		
					 		
					 	case "HARVORPROD":
					 		
					 		readResources(cardEl, resourcesGive, "g");
					 		developmentCard.setImmediateEffect(new GiveResourcesImmediateEffect(resourcesGive));
					 		
					 		
					 		TowerColor hpCardColor = readColorFromFile(cardEl, "hpcolor");
					 		int hpDiceValue = readIntFromFile(cardEl, "hpdicevalue");
					 		developmentCard.setImmediateEffect(new HarvestingOrProductionImmediateEffect( hpDiceValue, hpCardColor));
					 		
					 	}
					
					
				}
			}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
					
	
				private int readIntFromFile(Element element, String intName){
					String read = readStringFromFile(element, intName);
					int readInt = Integer.parseInt(read);
					return readInt;
				}
				private TowerColor readColorFromFile(Element element, String colorName){
					String read = readStringFromFile(element, colorName);
					TowerColor readColor = TowerColor.valueOf(read);
					return readColor;
				}
				private String readStringFromFile(Element element, String stringName){
					return (String) element.getElementsByTagName(stringName).item(0).getTextContent();
				}
				private void readResources(Element element, Resources resources, String pre){
					for(Map.Entry<String, Resource> entry : resources.getResourcesMap().entrySet()){
						int value = readIntFromFile(element, pre + entry.getKey().toLowerCase());
						resources.getResource(entry.getKey()).add(value);
					}
			}
			
		}
	


package it.polimi.ingsw.ps03.development_card;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.room_pack.TowerColor;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DevelopmentCards {

	private static final int STANDARD_SIZE = 96;
	private ArrayList<DevelopmentCard> developmentCards;
	
	//COSTRUTTORE
	public DevelopmentCards(){
		developmentCards = new ArrayList<DevelopmentCard>(STANDARD_SIZE);
	}
	
	//METODI GET
	
	//BUILDER
	public void build(){
		try{
			File devCardsXml = new File("/Users/alberto/prova-finale-template/src/main"
					+ "/java/it/polimi/ingsw/ps03/development_cards.xml");
			Document docDevCards = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(devCardsXml);
			
			docDevCards.getDocumentElement().normalize();
			docDevCards.getDocumentElement().getNodeName();
			
			NodeList devCardsList = docDevCards.getElementsByTagName("developmentCard");
			for(int i = 0; i < devCardsList.getLength(); i++){
				Node devCard = devCardsList.item(i);
				if(devCard.getNodeType() == Node.ELEMENT_NODE){
					Element cardEl = (Element) devCard;
					String id_t = (String) cardEl.getElementsByTagName("id").item(0).getTextContent();
					int id = Integer.parseInt(id_t);
					String cardName = (String) cardEl.getElementsByTagName("cardName").item(0).getTextContent();
					String cardColor_t = (String) cardEl.getElementsByTagName("cardColor").item(0).getTextContent();
					TowerColor cardColor = TowerColor.valueOf(cardColor_t);
					String cardPeriod_t = (String) cardEl.getElementsByTagName("cardPeriod").item(0).getTextContent();
					int cardPeriod = Integer.parseInt(cardPeriod_t);
					String diceValue_t = (String) cardEl.getElementsByTagName("diceValue").item(0).getTextContent();
					int diceValue = Integer.parseInt(diceValue_t);
					DevelopmentCard developmentCard = new DevelopmentCard(id, cardName, cardColor, cardPeriod, diceValue);
					developmentCards.add(i, developmentCard);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	//METODI VARI
	//private void displaceCards(); 
	//private void displaceTowerCards();
	//private DevelopmentCard getRandomCard(int cardPeriod, TowerColor cardColor);
}

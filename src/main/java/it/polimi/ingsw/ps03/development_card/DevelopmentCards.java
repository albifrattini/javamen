package it.polimi.ingsw.ps03.development_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.room_pack.TowerColor;

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
			File devCardsXml = new File("./src/development_cards.xml");
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

}

package it.polimi.ingsw.ps03.zzRegoleAvanzateLasciateLi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

import java.io.File;

public class LeaderCards {
	public static final int MAX_SIZE_OF_LDC = 20; 
	private static List<LeaderCard> leaderCards;
	
	public LeaderCards(){
		leaderCards = new ArrayList<LeaderCard>(MAX_SIZE_OF_LDC);	
	}

	public List<LeaderCard> getLCList(){
		return leaderCards;
	}
	
	public void buildLCs(){
		try {	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = documentFactory.newDocumentBuilder();
		Document document = builder.parse(new File("./src/leaderCards.xml"));

		document.getDocumentElement().normalize();
		document.getDocumentElement().getNodeName();
		NodeList leaderCardsList = document.getElementsByTagName("leaderCard");

		for(int i=0; i<=leaderCardsList.getLength(); i++) {
			
			Node nodo = leaderCardsList.item(i);

			if(nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element leaderCard = (Element) nodo;

				String id_t = (String) leaderCard.getElementsByTagName("id").item(0).getTextContent();
				int id = Integer.parseInt(id_t);
				String name = leaderCard.getElementsByTagName("name").item(0).getTextContent();
				String ldEffect = leaderCard.getElementsByTagName("effect").item(0).getTextContent();       
				String color1_t = (String) leaderCard.getElementsByTagName("yellow").item(0).getTextContent();
				int color1 = Integer.parseInt(color1_t);
				for (int j = 0; j < color1; j++){
					
				}
				
						}
							}
	
								} catch(Exception e) {
									e.printStackTrace();
															}
																			}
}

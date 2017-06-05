package it.polimi.ingsw.ps03.excommunication_pack;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;

public class Excommunications {

	public static final int MAX_SIZE_OF_ECOMMUNICATION = 21; 
	private static List<Excommunication> excommunications;
	
	//private List<Excommunication> excommunicationsToBillboard;
	//dare come massima dimensiome 21, che sarebbe la totalità dei ban, seguendo la logica delle carte
	//o definire la massima dimensione dei deck da cui estrarre le carte che quindi sarebbe 7?

	public Excommunications(){
	excommunications = new ArrayList<Excommunication>(MAX_SIZE_OF_ECOMMUNICATION);	
	}


	public static List<Excommunication> getExcommunicationsList(){
	return excommunications;
	}


	public List<Excommunication> getExcommunicationsOfPeriod(int excommunicationPeriod, List<Excommunication> excommunications){
		List<Excommunication> deck = new ArrayList<Excommunication>();
			for(int i = 0; i < MAX_SIZE_OF_ECOMMUNICATION; i++){
				if(excommunications.get(i).getExcommunicationPeriod() == 
						excommunicationPeriod){
					deck.add(excommunications.get(i));
				}
			}
		 return deck;
	}
	
	//metodo estrae casualmente un intero per periodo, 
	//le carte tra 0 e 6 apparterranno al primo periodo
	//tra 7 e 13 al secondo e tra 14 e 20 al terzo
	
	/*public List<Excommunication> getExcommunicationsToBillboard(){
		int n = 2, k = 6, j = 0, m = 13, p = 7, q = 20, t = 14;
		Random random = new Random();
		
		excommunicationsToBillboard = new ArrayList<Excommunication>(n);
		excommunicationsToBillboard.add(0, excommunicationEffect).id = random.nextInt(k)+j;
		excommunicationsToBillboard[1].id = random.nextInt(m)+p;
	    excommunicationsToBillboard[2].id = random.nextInt(q)+t;
		
	    return excommunicationsToBillboard;
	}*/
	
	
	
	
	public void buildExcomm(){
		try {	
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			Document document = builder.parse(new File("./src/excommunications.xml"));

			document.getDocumentElement().normalize();
			document.getDocumentElement().getNodeName();
		
			NodeList excommunicationsList = document.getElementsByTagName("excommunication");

			for(int i=0; i<excommunicationsList.getLength(); i++) {
			
				Node nodo = excommunicationsList.item(i);

				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element excommunication = (Element) nodo;

					String id_t = (String) excommunication.getElementsByTagName("id").item(0).getTextContent();
					int id = Integer.parseInt(id_t);
					String excommunicationPeriod_t = (String) excommunication.getElementsByTagName("excommunicationPeriod").item(0).getTextContent();
					int excommunicationPeriod = Integer.parseInt(excommunicationPeriod_t);
                	String excommunicationEffect = excommunication.getElementsByTagName("excommunicationEffect").item(0).getTextContent();       
				
                	excommunications.add(new Excommunication(id, excommunicationPeriod, excommunicationEffect));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}

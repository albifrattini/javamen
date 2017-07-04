package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.polimi.ingsw.ps03.actions.ChangeTurn;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCards;
import it.polimi.ingsw.ps03.networking.controller.Controller;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.players.PlayerColor;

public class Game implements Runnable{
	
	private Map<String, Connection> playersConnected;
	private List<String> playersName = new ArrayList<String>();
	private Billboard model;
	private Controller controller;
	private DevelopmentCards developmentCards;
	
	public Game(Map<String, Connection> waitingConnection){
		playersConnected = new HashMap<String, Connection> (waitingConnection);
	}


	@Override
	public void run() {			
			List<RemoteView> players = new ArrayList<RemoteView>(2);		 						
			model = new Billboard();	
			controller = new Controller(model);
			developmentCards = new DevelopmentCards();
			developmentCards.build();			 	
			model.getTable().buildTable(playersConnected.size());
			
			System.out.println("Sono entrato");
 
			
			for(int i = 0; i < playersConnected.size(); i++ ){//aggiunge i giocatori alla partita
						
							PlayerColor [] colors = PlayerColor.values();		 		
					 		List<String> keys = new ArrayList<>(playersConnected.keySet());	//Returns a Set view of the keys contained in this map.
					 		Connection c = playersConnected.get(keys.get(i));
					 		RemoteView player = new RemoteView(new Player(keys.get(i) ,colors[i] , 5+i ) , c);
					 		players.add(player);
					 		controller.addRemote(player);//creo lista di remoteView
					 		playersName.add(keys.get(i));//creo lista di nomiUtente
					 		model.addPlayerRemote(i, keys.get(i), colors[i], 5+i);
					 		System.out.println("giocatore: " + playersName.get(i).toString() + " Colore: " + colors[i].toString());		 				 		
//					 		controller.addToPlay(keys.get(i));
					 		model.addObserver(controller);
					 		player.addObserver(controller);
					 		controller.addObserver(player);
			 			 	}
			
			model.getTurnOfPlay().setNumberOfPlayers(model.getPlayers().size());
			try {
				controller.initGame(new ChangeTurn());
			} catch (IOException e) {
				System.out.println("Non sono riuscito a far partire il turno");
			}

//	
//			for(int y = 0; y < players.size(); y++){//invia a tutti il model
//			
//							players.get(y).showModel(model);
//							
//							}
//		 					 
//			
//			
		 
	 }
}




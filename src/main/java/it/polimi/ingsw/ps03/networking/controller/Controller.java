package it.polimi.ingsw.ps03.networking.controller;

import java.util.Observable;
import java.util.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.effects.EarnImmediateEffect;
import it.polimi.ingsw.ps03.effects.Effect;
import it.polimi.ingsw.ps03.effects.GiveResourcesImmediateEffect;
import it.polimi.ingsw.ps03.effects.HarvestingOrProductionImmediateEffect;
import it.polimi.ingsw.ps03.effects.PlaceImmediateEffect;
import it.polimi.ingsw.ps03.finalcount.FinalCount;
import it.polimi.ingsw.ps03.networking.virtualView.RemoteView;
import it.polimi.ingsw.ps03.actions.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;

/**
 * This class represents the controller that takes the object from the remote view, process them, applies the action and sends them to the client 
 * @author Amministratore
 *
 */
public class Controller extends Observable implements Observer {

	private Billboard model;
	private List<RemoteView> players = new ArrayList<RemoteView>(4);
		
	public Controller(Billboard billboard){
		this.model = billboard;
	}
		
	public void addRemote(RemoteView player){
			players.add(player);
	}
/**
 * this method starts the game
 * @param action ChangeTurn
 * @throws IOException
 */
	public void initGame(ChangeTurn action) throws IOException{
		action.setBillboard(model);
		action.applyAction();
		sendBillboard();
		sendToPlayingClient("\n\n ==> E' il tuo turno:  ");
	}
	
	private void sendBillboard(){
		sendObservers(model);
	}
		
	private void sendObservers(Object obj){
		for(RemoteView player : players){
			try {
				player.getConnection().send(obj);
			}catch (IOException e) {
				System.out.println("[CONTROLLER]  Oggetto non inviato agli osservatori");
			}
		}
	}
	/**
	 * this method, if the type of the object is not PLACE , sends the object to the player
	 * @param obj Object
	 */
	private void sendToPlayingClient(Object obj){
		RemoteView player = players.get(model.getTurnOfPlay().getPlayerToPlay());
		try {
			if(obj instanceof Place){
				player.getConnection().send("\n\nWARNING! Requisiti non soddisfatti!");
			}
			else{
				player.getConnection().send(obj);
			}
		}catch (IOException e) {
			System.out.println("[CONTROLLER]  Oggetto non inviato al giocatore che deve giocare");
		}
	}
	
	
	
	//PARTI RIGUARDANTI LA GESTIONE AZIONI
	
/**
 * this method applies the action chosen by a player	
 * @param action Action
 */
	private void applyAction(Action action){
		action.setBillboard(model);
		if(action instanceof ChangeTurn){
			((ChangeTurn) action).applyAction();
			sendBillboard();
		}
		if(action instanceof Place){
			if(checkPlaceAction((Place) action)){
				DevelopmentCard drawnCard = ((Place) action).applyAction();
				if(hasEffect(drawnCard)){
					System.out.println("[AZIONE IN CORSO]");
					activateEffect(drawnCard);
					if(!(drawnCard.getImmediateEffect() instanceof PlaceImmediateEffect)){
						checkTurn(((Place) action).getPlayer());
					}
					System.out.println("[EFFETTO ATTIVATO E PASSATO CHECKTURN]");
				}
				else{
					checkTurn(((Place) action).getPlayer());
				}
			}
			else{
				sendToPlayingClient(action);
			}
		}
		if(action instanceof CheckPlayer){
			Player player = ((CheckPlayer) action).applyAction();
			sendToPlayingClient(player);
		}
	}
		
	private boolean hasEffect(DevelopmentCard drawnCard){
		return drawnCard != null && drawnCard.getImmediateEffect() != null;
	}
	/**
	 * this method activates the effect of development cards	
	 * @param drawnCard DevelopmentCard
	 */
	private void activateEffect(DevelopmentCard drawnCard){
		Effect effect = drawnCard.getImmediateEffect();
		Player player = model.getPlayers().get(model.getTurnOfPlay().getPlayerToPlay());
		if(effect instanceof GiveResourcesImmediateEffect){
			if(effect instanceof PlaceImmediateEffect){
				PlaceImmediateEffect plEffect = (PlaceImmediateEffect) effect;
				plEffect.applyEffect(player);
				ClientFakePlace clFakePlace = new ClientFakePlace(model, player.getColor(), 
													plEffect.getPlaceTowerColor(),
													plEffect.getPlaceDiceValue());
				sendToPlayingClient(clFakePlace);
			}
			else{
				((GiveResourcesImmediateEffect) effect).applyEffect(player);
			}
		}
		if(effect instanceof EarnImmediateEffect){
			((EarnImmediateEffect) effect).applyEffect(player);
		}
		if(effect instanceof HarvestingOrProductionImmediateEffect){
			((HarvestingOrProductionImmediateEffect) effect).applyEffect(player);
		}
	}
		
		
	private boolean checkPlaceAction(Place action){
		if(checkOccupation(action)){
			if(checkRequirement(action)){
				if(checkResources(action)){
						return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	private boolean checkOccupation(Place action){
		if(action.getRoom().isFull()){
			return false;
		}
		return true;
	}
		
		
	private boolean checkRequirement(Place action){
		if(action.getRoom().getRequirement() <= (action.getPawn().getValue() + 
				action.getRequiredResources().getResource("SERVANTS").getValue())){
			return true;
		}
		return false;
	}
		
	private boolean checkResources(Place action){
		boolean result = true;
		for(Map.Entry<String, Resource> entry : action.getPlayer().getResources().getResourcesMap().entrySet()){
			if(entry.getValue().getValue() < action.getRequiredResources().getResource(entry.getKey()).getValue()){
				result = false;
			}
			if(action.getRoom() instanceof TowerRoom){
				if(!(checkCost(action))){
					result = false;
				}
			}
		}
		return result;
	}
		
	private boolean checkCost(Place action){
		boolean result = true;
		for(Map.Entry<String, Resource> entry : action.getChosenCost().getResourcesMap().entrySet()){
			if(action.getRequiredResources().getResource(entry.getKey()).getValue() < 
					action.getChosenCost().getResource(entry.getKey()).getValue()){
				result = false;
			}
		}
		return result;
	}
		
	public void checkTurn(Player player){
		if(!(player.hasCouncilPrivileges())){
			model.getTurnOfPlay().nextPlayer();
			System.out.println("[CHECKTURN] " + model.getTurnOfPlay().hasNextMiniTurn() + 
					model.getTurnOfPlay().getMiniTurn() + model.getTurnOfPlay().getTurn()
					+ model.getTurnOfPlay().getPeriod());
			if(!(model.getTurnOfPlay().hasNextMiniTurn())){
				System.out.println("[POSTO SBAGLIATO MINITURN]");
				if(!(model.getTurnOfPlay().gameIsEnded())){
					ChangeTurn changeTurn = new ChangeTurn();
					applyAction(changeTurn);
				}
				else if(model.getTurnOfPlay().gameIsEnded()){
					finalCount();
				}
				else{
				sendBillboard();
				sendToPlayingClient("\n\n ==> E' il tuo turno:  ");
				}
			}
			else{
				System.out.println("[NUMERO DI GIOCATORI] "  + model.getTurnOfPlay().getNumberOfPlayers());
				System.out.println("[PRIMA DI INVIARE LA BILLBOARD IN CHECKTURN]");
				sendBillboard();
				sendToPlayingClient("\n\n ==> E' il tuo turno:  ");
				
			}
		}
		else{
			System.out.println("[ELSE NESSUN PRIVILEGIO]");
			sendToPlayingClient(player.getResources());
		}
	}		
	/**
	 * this methods ends the game and shows the winner
	 */
	private void finalCount(){
		for(Player p: model.getPlayers()){
			for(FinalCount f: model.getFinalPoints()){
				p.getResources().add(f.giveVictoryPoints(p.getCards()));
			}
		}
		int result = getFromMilitaryPoints(5);
		if(result <= 1){
			getFromMilitaryPoints(2);
		}
		convertResources();
		sendFinalMessage();
	}
	
	private void sendFinalMessage(){
		int max_value = 0;
		Player player = model.getPlayers().get(0);
		for(Player p: model.getPlayers()){
			if(p.getResources().getResource("VICTORYPOINTS").getValue() > max_value){
				player = p;
			}
		}
		try{
			sendObservers("###  Ha vinto il giocatore " + player.getColor() + "!  ###");
		}catch(NullPointerException e){
			System.out.println("Errore! Giocatore null");
		}
	}
	
	private void convertResources(){
		for(Player p: model.getPlayers()){
			int valueToAdd = 0;
			valueToAdd += p.getResources().getResource("STONES").getValue();
			valueToAdd += p.getResources().getResource("WOODS").getValue();
			valueToAdd += p.getResources().getResource("COINS").getValue();
			valueToAdd += p.getResources().getResource("SERVANTS").getValue();
			valueToAdd = valueToAdd/5;
			p.getResources().getResource("VICTORYPOINTS").add(valueToAdd);
			p.getResources().add(
					model.getFaithCount().getResources(
					p.getResources().getResource("FAITHPOINTS").getValue()));
		}
	}
	/**
	 * this method turns the military points owned by a player into victory   points
	 * @param value
	 * @return
	 */
	private int getFromMilitaryPoints(int value){
		int max_value = 0, counter = 0;
		for(Player p: model.getPlayers()){
			max_value = Math.max(max_value, p.getResources().getResource("MILITARYPOINTS").getValue());
		}
		for(Player p: model.getPlayers()){
			if(p.getResources().getResource("MILITARYPOINTS").getValue() == max_value){
				p.getResources().getResource("MILITARYPOINTS").setValue(0);
				p.getResources().getResource("VICTORYPOINTS").add(value);
				counter++;
			}
		}
		return counter;
	}
	

				
	public Billboard getBillboard(){
		return model;
	}


	private void printMessage(String message){
		System.out.println(message);
		sendToPlayingClient("\n\nE' il tuo turno:  ");
	}
	private Place convertToPlace(ClientPlace action){
		Place place = new Place(model.getPlayerOfColor(action.getPlayerColor()));
		place.setRoom(model.getTable().getRooms().get(action.getRoom()));
		place.setPawn(place.getPlayer().getPawn(action.getPawnColor().toString()));
		place.setRequiredResources(action.getSpentResources());
		place.setChosenCost(action.getChosenCost());
		return place;
	}
	private FakePlace convertToFakePlace(ClientFakePlace action){
		FakePlace fakePlace = new FakePlace(model, model.getPlayerOfColor(action.getPlayerColor()),
								action.getTowerColor(), action.getPawn().getValue());
		fakePlace.setRoom(model.getTable().getTowersRoomsOfColor(
				model.getTable().getTowerRoomList(), action.getTowerColor()).get(action.getRoom()));
		fakePlace.setRequiredResources(action.getSpentResources());
		fakePlace.setChosenCost(action.getChosenCost());
		return fakePlace;
	}
	

	public void addResourcesFromPrivileges(Resources resources){
		Player player = model.getPlayers().get(model.getTurnOfPlay().getPlayerToPlay());
		player.getResources().getResource("COUNCILPRIVILEGES").setValue(0);
		player.getResources().add(resources);
		checkTurn(player);
	}
		
	@Override
	public void update(Observable o, Object obj){
		if(!(o instanceof RemoteView)){
			throw new IllegalArgumentException();
		}
		if(obj instanceof Action){
			if(obj instanceof Pass){
				checkTurn(model.getPlayers().get(model.getTurnOfPlay().getPlayerToPlay()));
			}
			applyAction((Action) obj);
		}
		if(obj instanceof ClientPlace){			
			if(obj instanceof ClientFakePlace){
				obj = convertToFakePlace((ClientFakePlace) obj);
			}
			else{
				obj = convertToPlace((ClientPlace) obj);
			}
			applyAction((Action) obj);
		}
		if(obj instanceof String){
			printMessage((String) obj);
		}
		if(obj instanceof Resources){
			addResourcesFromPrivileges((Resources) obj);
		}
	}

}



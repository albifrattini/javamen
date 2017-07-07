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
import it.polimi.ingsw.ps03.networking.virtualView.RemoteView;
import it.polimi.ingsw.ps03.actions.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;


public class Controller extends Observable implements Observer {

	private Billboard model;
	private List<RemoteView> players = new ArrayList<RemoteView>(4);
		
	public Controller(Billboard billboard){
		this.model = billboard;
	}
		
	public void addRemote(RemoteView player){
			players.add(player);
	}

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
					checkTurn(((Place) action).getPlayer());
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
		
	private void activateEffect(DevelopmentCard drawnCard){
		Effect effect = drawnCard.getImmediateEffect();
		Player player = model.getPlayers().get(model.getTurnOfPlay().getPlayerToPlay());
		if(effect instanceof GiveResourcesImmediateEffect){
			if(effect instanceof PlaceImmediateEffect){
				PlaceImmediateEffect plEffect = (PlaceImmediateEffect) effect;
				plEffect.applyEffect(player);
				FakePlace fakePlace = new FakePlace(model, player,
												plEffect.getPlaceTowerColor(),
												plEffect.getPlaceDiceValue());
				sendToPlayingClient(fakePlace);
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
			if(!(model.getTurnOfPlay().hasNextMiniTurn())){
				System.out.println("[POSTO SBAGLIATO MINITURN]");
				ChangeTurn changeTurn = new ChangeTurn();
				applyAction(changeTurn);
				sendBillboard();
				sendToPlayingClient("\n\n ==> E' il tuo turno:  ");
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
				
	public Billboard getBillboard(){
		return model;
	}
		

		
//	private void notifyToView(Object obj){
//		if(obj instanceof Place){
//			setChanged();
//			notifyObservers("\n\nERRORE! Impossibile posizionare, requisiti non soddisfatti!");
//		}
//		setChanged();
//		notifyObservers(obj);
//		}
		
//	private void notifyToView(){
//		notifyToView(model);
//	}

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
		if(obj instanceof ClientPlace){
			obj = convertToPlace((ClientPlace) obj);
		}
		if(obj instanceof String){
			printMessage((String) obj);
		}
		if(obj instanceof Resources){
			addResourcesFromPrivileges((Resources) obj);
		}
		applyAction((Action) obj);
	}

}



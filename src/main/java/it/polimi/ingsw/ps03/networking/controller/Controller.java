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
import it.polimi.ingsw.ps03.room_pack.Room;
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
		sendToPlayingClient("\n\n####E' il tuo turno####");
	}
		
	private void sendBillboard(){
		setChanged();
		notifyObservers(model);
	}
		

		
	private void applyAction(Action action){
		action.setBillboard(model);
		if(action instanceof ChangeTurn){
			((ChangeTurn) action).applyAction();
			notifyToView();
		}
		if(action instanceof Place){
			printPlace((Place) action);
			if(checkPlaceAction((Place) action)){
				DevelopmentCard drawnCard = ((Place) action).applyAction();
				printPlace((Place)action);
				if(hasEffect(drawnCard)){
					activateEffect(drawnCard);
				}
			}
			else{
				notifyToView(action);
			}
			checkTurn(((Place) action).getPlayer());
		}
		if(action instanceof CheckPlayer){
			Player player = ((CheckPlayer) action).applyAction();
			sendToPlayingClient(player);
		}
	}
		
		
		
	private void printPlace(Place action){
		System.out.println(action.getPlayer().getPawns().size());
		System.out.println(action.getRoom().toString());
		System.out.println(action.getRequiredResources().toString());
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
				notifyToView(fakePlace);
			}
			else{
				((GiveResourcesImmediateEffect) effect).applyEffect(player);
				checkTurn(player);
			}
		}
		if(effect instanceof PlaceImmediateEffect){
			PlaceImmediateEffect plEffect = (PlaceImmediateEffect) effect;
			FakePlace fakePlace = new FakePlace(model, player,
											plEffect.getPlaceTowerColor(),
											plEffect.getPlaceDiceValue());
			notifyToView(fakePlace);
		}
		if(effect instanceof EarnImmediateEffect){
			((EarnImmediateEffect) effect).applyEffect(player);
			checkTurn(player);
		}
		if(effect instanceof HarvestingOrProductionImmediateEffect){
			((HarvestingOrProductionImmediateEffect) effect).applyEffect(player);
			checkTurn(player);
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
		printRooms(model.getTable().getRooms());
		if(!(player.hasCouncilPrivileges())){
			model.getTurnOfPlay().nextPlayer();
			if(!(model.getTurnOfPlay().hasNextMiniTurn())){
				ChangeTurn changeTurn = new ChangeTurn();
				applyAction(changeTurn);
			}
			else{
				notifyToView();
				sendToPlayingClient("\n\n####E' il tuo turno####");
			}
		}
		else{
			notifyToView(player.getResources());
		}
	}		
				
	public Billboard getBillboard(){
		return model;
	}
		
	private void sendToPlayingClient(Object obj){
		RemoteView player = players.get(model.getTurnOfPlay().getPlayerToPlay());
		try {
			player.getConnection().send(obj);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void notifyToView(Object obj){
		if(obj instanceof Place){
			setChanged();
			notifyObservers("\n\nERRORE! Impossibile posizionare, requisiti non soddisfatti!");
		}
		setChanged();
		notifyObservers(obj);
		}
		
	private void notifyToView(){
		notifyToView(model);
	}
				
	private void printRooms(List<Room> rooms){
		int spaces = 0;
		for(int i = 0; i < rooms.size(); i++){
			if((Pawn) rooms.get(i).getPawn() == null){
				System.out.print(spaces + " - ");
				System.out.println(((Room) rooms.get(i)).toString());
			}
			spaces++;
		}
	}
		
	@Override
	public void update(Observable o, Object obj){
		if(!(o instanceof RemoteView)){
			throw new IllegalArgumentException();
		}
		applyAction((Action) obj);
	}

}



package it.polimi.ingsw.ps03.mvc;

import java.util.Observer;
import java.util.Map;
import java.util.Observable;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.effects.EarnImmediateEffect;
import it.polimi.ingsw.ps03.effects.Effect;
import it.polimi.ingsw.ps03.effects.GiveResourcesImmediateEffect;
import it.polimi.ingsw.ps03.effects.HarvestingOrProductionImmediateEffect;
import it.polimi.ingsw.ps03.effects.PlaceImmediateEffect;
import it.polimi.ingsw.ps03.actions.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;

public class Controller extends Observable implements Observer {

	private Billboard model;
	private BillboardView view;
	
	public Controller(Billboard billboard, BillboardView view){
		this.model = billboard;
		this.view = view;
	}

	
	
	
	
	
	private void applyAction(Action action){
		action.setBillboard(model);
		if(action instanceof ChangeTurn){
			((ChangeTurn) action).applyAction();
			notifyToView();
		}
		if(action instanceof Place){
			if(action instanceof FakePlace){
				FakePlace place = (FakePlace) action;
				if(checkPlaceAction(place)){
					DevelopmentCard drawnCard = place.applyAction();
					if(drawnCard != null){
						if(drawnCard.getImmediateEffect() != null){
							activateEffect(drawnCard);
						}
						checkTurn();
					}
					else{
						checkTurn();
					}
				}
				else{
					notifyToView(getError(action));
				}
			}
			else{
				Place place = (Place) action;
				if(checkPlaceAction(place)){
					DevelopmentCard drawnCard = place.applyAction();
					if(drawnCard != null){
						if(drawnCard.getImmediateEffect() != null){
							activateEffect(drawnCard);
						}
						checkTurn();
					}
					else{
						checkTurn();
					}
				}	
				else{
					notifyToView(getError(action));
				}
			}
		}
		if(action instanceof CheckPlayer){
			Player player = ((CheckPlayer) action).applyAction();
			notifyToView(player);
		}
	}
	
	
	private void activateEffect(DevelopmentCard drawnCard){
		Effect effect = drawnCard.getImmediateEffect();
		Player player = model.getPlayers().get(model.getTurnOfPlay().getPlayerToPlay());
		if(effect instanceof GiveResourcesImmediateEffect){
			((GiveResourcesImmediateEffect) effect).applyEffect(player);
			checkTurn();
		}
		if(effect instanceof PlaceImmediateEffect){
			PlaceImmediateEffect plEffect = (PlaceImmediateEffect) effect;
			plEffect.applyEffect(player);
			FakePlace fakePlace = new FakePlace(model, player,
										plEffect.getPlaceTowerColor(),
										plEffect.getPlaceDiceValue());
			notifyToView(fakePlace);
		}
		if(effect instanceof EarnImmediateEffect){
			((EarnImmediateEffect) effect).applyEffect(player);
			checkTurn();
		}
		if(effect instanceof HarvestingOrProductionImmediateEffect){
			((HarvestingOrProductionImmediateEffect) effect).applyEffect(player);
			checkTurn();
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
	
	public void checkTurn(){
		model.getTurnOfPlay().nextPlayer();
		if(!(model.getTurnOfPlay().hasNextMiniTurn())){
			ChangeTurn changeTurn = new ChangeTurn();
			applyAction(changeTurn);
		}
		else{
			notifyToView();
		}
	}
	
	private String getError(Action action){
		if(action instanceof Place){
			return "\n\nERRORE! Impossibile piazzare a causa di risorse insufficienti o requisiti non soddisfatti!";
		}
		if(action instanceof CheckPlayer){
			return "\n\nERRORE! Giocatore inesistente!";
		}
		return "Errore imprevisto...";
	}
	

	
	
	
	
	
	public Billboard getBillboard(){
		return model;
	}
	
	
	
	
	

	private void notifyToView(Object obj){
		if(obj instanceof String){
			setChanged();
			notifyObservers((String) obj);
		}
		if(obj instanceof Player){
			setChanged();
			notifyObservers((Player) obj);
		}
		if(obj instanceof Billboard){
			setChanged();
			notifyObservers((Billboard) obj);
		}
		if(obj instanceof FakePlace){
			setChanged();
			notifyObservers((FakePlace) obj);
		}
	}
	private void notifyToView(){
		notifyToView(model);
	}
	
	@Override
	public void update(Observable o, Object obj){
		if(o != view || !(obj instanceof Action)){
			throw new IllegalArgumentException();
		} 
		applyAction((Action) obj);
	}
}

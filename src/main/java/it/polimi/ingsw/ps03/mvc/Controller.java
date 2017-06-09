package it.polimi.ingsw.ps03.mvc;

import java.util.Observer;
import java.util.Map;
import java.util.Observable;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.actions.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.Room;

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
			Place place = (Place) action;
			if(checkPlaceAction(place)){
				place.applyAction();
				notifyToView();
			}
			else{
				notifyToView(getError(action));
			}
		}
		if(action instanceof CheckPlayer){
			Player player = ((CheckPlayer) action).applyAction();
			notifyToView(player);
		}
		
	}
	
	
	private boolean checkPlaceAction(Place action){
		action.getPawn().addServants(action.getRequiredResources().getResource("SERVANTS").getValue());
		if(checkRequirement(action)){
			if(checkResources(action)){
				return true; //adesso non controlla ancora il costo della carta sviluppo
			}
			return false;
		}
		return false;
	}
	
	private boolean checkRequirement(Place action){
		if(action.getRoom().getRequirement() <= action.getPawn().getValue()){
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
		}
		return result;
	}
	
	private String getError(Action action){
		if(action instanceof Place){
			return "Errore! Impossibile piazzare a causa di risorse insufficienti o requisiti non soddisfatti!";
		}
		if(action instanceof CheckPlayer){
			return "Errore! Giocatore inesistente!";
		}
		return "Errore imprevisto...";
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
	}
	private void notifyToView(){
		notifyToView(model);
	}
	
	
	
	
	public Billboard getBillboard(){
		return model;
	}
	
	@Override
	public void update(Observable o, Object obj){
		if(o != view || !(obj instanceof Action)){
			throw new IllegalArgumentException();
		} 
		applyAction((Action) obj);
	}
	
	
	
	
	
}

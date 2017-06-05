package it.polimi.ingsw.ps03.mvc;

import java.util.Observer;
import java.util.Observable;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.actions.*;

public class Controller implements Observer {

	private Billboard model;
	private BillboardView view;
	
	public Controller(Billboard mBillboard, BillboardView mView){
		model = mBillboard;
		view = mView;
	}

	
	
	
	
	
	private void applyAction(Action action){
		action.setBillboard(model);
		if(action instanceof ChangeTurn){
			((ChangeTurn) action).applyAction();
		}
		if(action instanceof Place){
			((Place) action).applyAction();
		}
		model.toNotify();
	}
	
	
	
	
	
	
	@Override
	public void update(Observable o, Object obj){
		if(o != view || !(obj instanceof Action)){
			throw new IllegalArgumentException();
		} 
		applyAction((Action) obj);
	}
	
	
	
	
	
}

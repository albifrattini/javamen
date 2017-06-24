package it.polimi.ingsw.ps03.networking.controller;

import it.polimi.ingsw.ps03.networking.model.Choice;
import it.polimi.ingsw.ps03.networking.model.Model;
import it.polimi.ingsw.ps03.networking.virtualView.View;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
	private Model model;

	public Controller(Model model) {
		super();
		this.model = model;
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		if(!(o instanceof View)|| !(arg instanceof Choice)){
			throw new IllegalArgumentException();
		}
	//	model.setPlayerChoice(((View)o).getPlayer(), (Choice)arg);		
	}	
}

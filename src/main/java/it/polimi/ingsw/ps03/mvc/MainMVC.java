package it.polimi.ingsw.ps03.mvc;

import java.util.Scanner;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;

public class MainMVC {

	public static void main(String[] args){
		
		Billboard model = new Billboard();
		
		BillboardView view = new BillboardView();
		
		Controller billboardController = new Controller(model, view);
		
		Controller.insertPlayers(getNumberOfPlayers());
		
		
		
	}
	
	
	private static int getNumberOfPlayers(){
		BillboardView.printInitGame();
		return Controller.checkNumberOfPlayers();
	}
	
	
	
}

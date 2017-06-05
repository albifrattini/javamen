package it.polimi.ingsw.ps03.mvc;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.PlayerColor;


public class MainMVC {

	private Billboard billboard;
	private BillboardView billboardView;
	private Controller billboardController;

	public MainMVC(){
		billboard = new Billboard();
		billboardView = new BillboardView(System.in, System.out);
		billboard.getTable().buildTable(4);
		billboard.addPlayer(PlayerColor.BLUE, 5);
		billboard.addPlayer(PlayerColor.RED, 6);
		billboard.addPlayer(PlayerColor.GREEN, 7);
		billboard.addPlayer(PlayerColor.YELLOW, 8);
		billboardController = new Controller(billboard, billboardView);
		billboardView.addObserver(billboardController);
		billboard.addObserver(billboardController);
		billboard.addObserver(billboardView);
		billboard.getTurnOfPlay().addObserver(billboard);
	}
	
	public static void main(String[] args){
		try{	
			MainMVC main = new MainMVC();
			main.startGame();
		}catch(IllegalArgumentException e){
			System.out.println("Errore da chiedere!!");
		}
	}
	private void startGame(){
		billboardView.run();
	}
	

	
	
		
	
}

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
		billboard.addPlayer(0, PlayerColor.BLUE, 5);
		billboard.addPlayer(1, PlayerColor.RED, 6);
		billboard.addPlayer(2, PlayerColor.GREEN, 7);
		billboard.addPlayer(3, PlayerColor.YELLOW, 8);
		billboard.getTurnOfPlay().setNumberOfPlayers(billboard.getPlayers().size());
		billboardController = new Controller(billboard, billboardView);
		billboardView.addObserver(billboardController);
		billboard.addObserver(billboardController);
		billboardController.addObserver(billboardView);
	}
	
	public static void main(String[] args){
		try{	
			MainMVC main = new MainMVC();
			main.startGame();
		}catch(IllegalArgumentException e){
			System.out.println("Errore in Main!");
		}
	}
	private void startGame(){
		billboardView.run();
	}
	

	
	
		
	
}

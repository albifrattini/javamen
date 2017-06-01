package it.polimi.ingsw.ps03.mvc;

import java.util.Scanner;
import java.util.Observer;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.PlayerColor;

public class Controller {

	private static Billboard model;
	private static BillboardView view;
	
	public Controller(Billboard mBillboard, BillboardView mView){
		model = mBillboard;
		view = mView;				
	}
	
	
	
	
	private static void addPlayer(PlayerColor color, int initialCoins){
		Billboard.addPlayer(color, initialCoins);
	}
	
	
	
	
	
	public static int checkNumberOfPlayers(){
		Scanner scanner = new Scanner(System.in);
		int numberOfPlayers = scanner.nextInt();
		while(rightNumberOfPlayers(numberOfPlayers)){
			BillboardView.printErrorPlayers();
			numberOfPlayers = scanner.nextInt();
		}
		scanner.close();
		return  numberOfPlayers;
	}
	
	
	
	
	private static boolean rightNumberOfPlayers(int numberOfPlayers){
		if(numberOfPlayers <= 4 && numberOfPlayers >=2){
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	public static void insertPlayers(int numberOfPlayers){
		Scanner scanner = new Scanner(System.in);
		BillboardView.printChooseColor();
		String choosenColor = scanner.next();
		while(possibleColor(choosenColor)){
			BillboardView.printErrorColor();
		}
		for(int i = 0; i < numberOfPlayers; i++){
			PlayerColor playerColor = PlayerColor.valueOf(choosenColor);
			addPlayer(playerColor, 5+i);
		}
		scanner.close();
	}
	
	
	
	
	
	private static boolean possibleColor(String inputColor){
		for(PlayerColor c : PlayerColor.values()){
			if(c == PlayerColor.valueOf(inputColor)){
				return false;
			}
		}
		return true;
	}

	
	
	
}

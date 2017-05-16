package it.polimi.ingsw.ps03.billboard_pack;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] args){
		
		Billboard billboard = new Billboard();
		Scanner scanner = new Scanner(System.in);
		
//		try{	
			System.out.println("Insert number of Players (between 2-4): ");
			int numberOfPlayers = scanner.nextInt();
/*			if (numberOfPlayers < 2 || numberOfPlayers > 4) 
				throw new Exception("UnAccepted number of players!");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Reboot game...");
		}
*/
		
		System.out.println("Available color -> BLUE\nGREEN\nYELLOW\nRED\n");
		for (int i = 1; i < numberOfPlayers; i++){
			System.out.printf("Insert color of player number: ", i);
			String colorDecision = scanner.next();
			billboard.addPlayer(i, colorDecision);
		}
		
		
		
		billboard.changeDicesValues();
		
		
		scanner.close();
	}
}

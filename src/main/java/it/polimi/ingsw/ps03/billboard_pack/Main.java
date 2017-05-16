package it.polimi.ingsw.ps03.billboard_pack;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] args){
		
		Billboard billboard = new Billboard();
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 4; i++){
			System.out.println("Inserisci un colore tra\n"
					+ "BLUE\nGREEN\nRED\nYELLOW\n: ");
			String colorDecision = scanner.next();
			billboard.addPlayer(i, colorDecision);
		}
		
		
		
		billboard.changeDicesValues();
		
		
		scanner.close();
	}
}

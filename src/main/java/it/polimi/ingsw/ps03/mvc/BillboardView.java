package it.polimi.ingsw.ps03.mvc;

import java.util.Observable;

public class BillboardView extends Observable {

	public static void printInitGame(){
		System.out.println("Benvenuti a Lorenzo Il Magnifico!\n");
		System.out.println("Inserire il numero di giocatori: ");
	}
	
	
	
	public static void printErrorPlayers(){
		System.out.println("Numero di giocatori non supportato!\n");
	}
	
	public static void printChooseColor(){
		System.out.println("\nScegli un colore:");
		System.out.println("\tBLUE" + "\n\tGREEN" + "\n\tYELLOW" + "\n\tRED" + "\n--> ");
	}
	
	public static void printErrorColor(){
		System.out.println("Colore non supportato!\n");
	}
}

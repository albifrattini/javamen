package it.polimi.ingsw.ps03.billboard_pack;

public class Instant {

	private static int turn;
	private static int period;
	
	public Instant(){
		turn = 0;
		period = 0;
	}
	
	public static int getTurn(){
		return turn;
	}
	public static int getPeriod(){
		return period;
	}
	
	public static void nexTurn(){
		turn++;
	}
	public static void nextPeriod(){
		period++;
	}
	
}

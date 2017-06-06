package it.polimi.ingsw.ps03.mvc;

import it.polimi.ingsw.ps03.billboard_pack.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.actions.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class BillboardView extends Observable implements Observer, Runnable {

	private Scanner scanner;
	private PrintStream output; 
	
	public BillboardView(InputStream inputStream, OutputStream output){
		this.scanner = new Scanner(inputStream);
		this.output = new PrintStream(output);
	}
	
	
	/* Override of the method of the Interface Runnable. It simply initializes the game, changing 
	 * the turn that calls setChanged() and notifyObservers() from the model for the first time.
	 */
	@Override
	public void run(){
		output.println("Benvenuti in Lorenzo il Magnifico!\n\n");
		setChanged();
		notifyObservers(new ChangeTurn());
	}
	
	
	/* Is the principal function of the class 'BillboardView'. It receives the already edited model
	 * (billboard in our case) and an integer that represents the player that is called at playing.
	 * In this method we give an output on the screen representing all the possible actions that a 
	 * player can do. After choosing one, a specified method will be called to ask and process all
	 * the details necessary to build the object 'Action'.
	 */
	public void doSomething(Billboard billboard){
		ActionChoices choice = null;
		int turnOfPlayer = billboard.getTurnOfPlay().getPlayerToPlay();
		do{
			choice = whatToDo(billboard, turnOfPlayer);
		} while(choice != ActionChoices.PLACE && choice != ActionChoices.CHECKPLAYER);
		switch(choice){
			case PLACE:
				placeAction(billboard, turnOfPlayer);
			case CHECKPLAYER:
				output.println("ciao");
			default:
				output.println("Errore nella scelta azione!");
		}
	}
	
	
	/* In this method we simply show the possible actions and after taking one, we return it to the
	 * method that called this.
	 */
	public ActionChoices whatToDo(Billboard billboard, int turnOfPlayer){
		PlayerColor color = billboard.getPlayers().get(turnOfPlayer).getColor();
		ActionChoices choice = null;
		try{
			output.println("E' il turno del giocatore di colore " + 
						color.toString().substring(0, 1).toUpperCase() + 
						color.toString().substring(1, color.toString().length()).toLowerCase());
			printPlayerResources(billboard.getPlayers().get(turnOfPlayer));
			output.println("\nCosa desideri fare?");
			output.println("Azioni possibili:\tPlace");
			choice = ActionChoices.valueOf(scanner.next().toUpperCase());
		}catch(IllegalArgumentException e){
			output.println("\nWARNING: Soluzione non valida!\n");
		}
		return choice;
	}	
	
	
	/* After showing available rooms (action spaces) and pawns, this method builds the object 'Action' asking
	 * the user what to do. After this a setChanged() and a notifyObservers(action) with argument the action
	 * object just built. This will be processed by the Controller.
	 */
	public void placeAction(Billboard billboard, int i){
		Place action = new Place();
		if(action.getPawn() == null){
			action.setPawn(billboard.getPlayers().get(i).getPawn(pawnChoice(billboard.getPlayers().get(i))));
		}
		
		try{
			action.setRoom(billboard.getTable().getRooms().get(roomChoice(billboard)));
		}catch(IndexOutOfBoundsException e){
			output.println("\nWARNING: Spazio azione inesistente!\n");
			placeAction(billboard, i);
		}
//		checkRequirement(action);
		setChanged();
		notifyObservers(action);
	}
	
	
	/* Method that helps showing all the possible pawns to place and gets the input from the user. It will
	 * return an object String representing the chosen pawn.
	 */
	public String pawnChoice(Player player){
		String pawn = null;
		do{
			output.println("\nQuale pedone desideri piazzare?");
			output.println("Scelte disponibili: \n");
			for(Map.Entry<String, Pawn> entry : player.getPawns().entrySet()){
				if(entry.getValue() != null){
					output.println(entry.getValue().toString());
				}	
			}
			pawn = scanner.next().toUpperCase();
			if(!(player.getPawns().containsKey(pawn))){
				output.println("\nWARNING: Pedina inesistente!\n");
			}
		}while(!(player.getPawns().containsKey(pawn)));
		return pawn;
	}
	
	
	/* Method that helps showing all the possible rooms and gets the input from the user. It will return an 
	 * object String representing the chosen room.
	 */
	public int roomChoice(Billboard billboard){
		output.println("\nQuale spazio azione desideri occupare?");
		output.println("Scelte disponibili:\n");
		int spaces = 0;
		List<Room> rooms = billboard.getTable().getRooms();
		for(int i = 0; i < rooms.size(); i++){
			if((Pawn) rooms.get(i).getPawn() != null){
				output.print(spaces + " - ");
				output.print(((Room) rooms.get(i)).toString());
				output.println("\tNot Available!");
			}
			else{
				output.print(spaces + " - ");
				output.println(((Room) rooms.get(i)).toString());
			}
			spaces++;
		}
		output.println(spaces + " - Palazzo del consiglio");
		String input = scanner.next();
		int choice = Integer.parseInt(input);
		return choice;
	}
	
	
	/* Method always called by the update method. It will show the current situation of the billboard, with 
	 * particular regard to rooms. So it will not show player's resources and the personal board in general.
	 * Will be given an action able to show player's personal board.
	 */
	public void showModel(Billboard billboard){
		showChangeOfTurn(billboard.getTurnOfPlay());
		printTowers(billboard.getTable().getTowerRoomList());
		printMarket(billboard.getTable().getMarketRoomList());
		printHarvesting(billboard.getTable().getHarvestingRoomList());
		printProduction(billboard.getTable().getProductionRoomList());
		printCouncil(billboard.getTable().getCouncilPalaceList());
	}
	public void showChangeOfTurn(TurnOfPlay turnOfPlay){
		output.println("Turno " + turnOfPlay.getTurn() + 
				" del periodo numero " + turnOfPlay.getPeriod() + ":\n");
	}
	private void printTowers(List<TowerRoom> towerRooms){
		for(TowerRoom t : towerRooms){
			if(t.getPawn() == null){
				output.print(t.toString());
				output.println(t.getResources().toString());
			}
		}	
		output.print("\n");
	}
	private void printMarket(List<MarketRoom> market){
		for(MarketRoom m : market){
			if(m.getPawn() == null){
				output.print(m.toString());
				output.println(m.getResources().toString());
			}
		}
		output.print("\n");
	}
	private void printHarvesting(List<HarvestingRoom> harvestings){
		for(HarvestingRoom h : harvestings){
			if(h.getPawn() == null){
				output.println(h.toString());
			}
		}
		output.print("\n");
	}
	private void printProduction(List<ProductionRoom> productions){
		for(ProductionRoom p : productions){
			if(p.getPawn() == null){
				output.println(p.toString());
			}
		}
		output.print("\n");
	}
	private void printCouncil(List<CouncilRoom> councils){
		Iterator<CouncilRoom> it = councils.iterator();
		int counter = 0;
		while(it.hasNext()){
			counter++;
		}
		System.out.printf("%d pedoni già piazzati nel Palazzo del Consiglio!\n", counter);
		System.out.println("Bonus 1 Moneta e un Privilegio del Consiglio.\n");
	}
	private void printPlayerResources(Player player){
		output.println("\nRisorse possedute:");
		for(Map.Entry<String, Resource> entry : player.getResources().getResourcesMap().entrySet()){
			output.println(entry.getValue().toString());
		}
	}
	
	
	
	
	@Override
	public void update(Observable o, Object obj){
		if(!(o instanceof Billboard)){
			throw new IllegalArgumentException();
		}
		showModel((Billboard) o);
		doSomething(((Billboard)o));
	}
	
	
	
}

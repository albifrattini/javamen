package it.polimi.ingsw.ps03.mvc;

import it.polimi.ingsw.ps03.billboard_pack.*;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;
import it.polimi.ingsw.ps03.room_pack.MarketRoom;
import it.polimi.ingsw.ps03.room_pack.HarvestingRoom;
import it.polimi.ingsw.ps03.room_pack.ProductionRoom;
import it.polimi.ingsw.ps03.room_pack.Room;
import it.polimi.ingsw.ps03.room_pack.Table;
import it.polimi.ingsw.ps03.room_pack.CouncilRoom;
import it.polimi.ingsw.ps03.actions.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
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
	
	@Override
	public void run(){
		output.println("Benvenuti in Lorenzo il Magnifico!\n");
		setChanged();
		notifyObservers(new ChangeTurn());
	}
	
	
	
	
	
	
	
	public void doSomething(Billboard billboard, int turnOfPlay){
		ActionChoices choice = null;
		do{
			if(choice != null){
				output.println("Scelta non valida!");
			}
			choice = whatToDo(billboard.getPlayers().get(turnOfPlay).getColor());
		} while(choice != ActionChoices.PLACE);
		switch(choice){
			case PLACE:
				placeAction(billboard, turnOfPlay);
			case CHECKPLAYER:
				output.println("ciao");
			default:
				output.println("Errore nella scelta azione!");
		}
	}
	public ActionChoices whatToDo(PlayerColor color){
			output.println("E' il turno del giocatore di colore " + color.toString().toLowerCase());
			output.println("Cosa desideri fare?");
			output.println("Azioni possibili:\tPlace");
			ActionChoices choice = ActionChoices.valueOf(scanner.next().toUpperCase());
			return choice;
	}
	
	
	public void placeAction(Billboard billboard, int i){
		Place action = new Place();
		action.setPawn(billboard.getPlayers().get(i).getPawn(pawnChoice(billboard.getPlayers().get(i))));
		output.println("Quale spazio azione desideri occupare?");
		action.setRoom(Table.getTowerRoomList().get(roomChoice(billboard)));
		setChanged();
		notifyObservers(action);
	}
	
	public String pawnChoice(Player player){
		output.println("Quale pedone desideri piazzare?");
		output.println("Scelte disponibili: \n");
		for(Map.Entry<String, Pawn> entry : player.getPawns().entrySet()){
			if(entry.getValue() != null){
				output.println(entry.getValue().toString());
			}
		}
		String pawn = scanner.next().toUpperCase();
		return pawn;
	}
	
	public int roomChoice(Billboard billboard){
		output.println("Quale spazio azione desideri occupare?");
		output.println("Scelte disponibili:\n");
		int spaces = 0;
		List<Room> rooms = billboard.getTable().getRooms();
		for(int i = 0; i < rooms.size(); i++){
			output.print(spaces + " - ");
			output.println(((Room) rooms.get(i)).toString());
			spaces++;
		}
		output.println(spaces + " - Palazzo del consiglio");
		
		String s = scanner.next();
		int choice = Integer.parseInt(s);
		return choice;
	}
	
	
	
	
	
	
	
	
	
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
				" del periodo numero " + turnOfPlay.getPeriod() + ":\n\n");
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
	
	
	
	
	
	@Override
	public void update(Observable o, Object obj){
		if(!(o instanceof Billboard)){
			throw new IllegalArgumentException();
		}
		showModel((Billboard) o);
		int i = ((Billboard) obj).getTurnOfPlay().getPlayerToPlay();
		doSomething(((Billboard)obj), i);
	}
	
	
	
}

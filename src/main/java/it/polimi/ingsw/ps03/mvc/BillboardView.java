package it.polimi.ingsw.ps03.mvc;

import it.polimi.ingsw.ps03.billboard_pack.*;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.room_pack.*;
import it.polimi.ingsw.ps03.actions.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class BillboardView extends Observable implements Observer {

	private Scanner scanner;
	private PrintStream output; 
	
	public BillboardView(InputStream inputStream, OutputStream output){
		this.scanner = new Scanner(inputStream);
		this.output = new PrintStream(output);
	}
	
	
	/* Override of the method of the Interface Runnable. It simply initializes the game, changing 
	 * the turn that calls setChanged() and notifyObservers() from the model for the first time.
	 */
	public void initGame(){
		output.println("Benvenuti in LORENZO IL MAGNIFICO!\n");
		setChanged();
		notifyObservers(new ChangeTurn());
	}
	
	
	/* Is the principal function of the class 'BillboardView'. It receives the already edited model
	 * (billboard in our case) and an integer that represents the player that is called at playing.
	 * In this method we give an output on the screen representing all the possible actions that a 
	 * player can do. After choosing one, a specified method will be called to ask and process all
	 * the details necessary to build the object 'Action'.
	 */
	public void startTurn(Billboard billboard){
		int turnOfPlayer = billboard.getTurnOfPlay().getPlayerToPlay();
		ActionChoices choice = null;
		PlayerColor color = billboard.getPlayers().get(turnOfPlayer).getColor();
		output.println("\nE' il turno del giocatore " + 
				color.toString().substring(0, 1).toUpperCase() + 
				color.toString().substring(1, color.toString().length()).toLowerCase());
		do{
			choice = selectAction();
		} while(choice == null);
		switch(choice){
			case PLACE:
				placeAction(billboard, turnOfPlayer);
				break;
			case CHECKPLAYER:
				checkPlayerAction(turnOfPlayer);
				break;
			case CHECKCARDS:
				printCards(billboard.getTable().getTowerRoomList());
				startTurn(billboard);
				break;
			default:
				output.println("Errore nella scelta azione!");
				break;
		}
	}
	
	
	/* In this method we simply show the possible actions and after taking one, we return it to the
	 * method that called this.
	 */
	public ActionChoices selectAction(){
		ActionChoices choice = null;
		try{
			output.println("\nCosa desideri fare?");
			output.println("Azioni possibili:\n --> Place\n --> CheckPlayer\n --> CheckCards\n");
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
	public void placeAction(Billboard billboard, int turnOfPlayer){
		Place action = new Place(billboard.getPlayers().get(turnOfPlayer));
		
		if(action.getPawn() == null){
			Player player = action.getPlayer();
			action.setPawn(player.getPawn(pawnChoice(player)));
		}
		
		try{
			List<Room> rooms = billboard.getTable().getRooms();
			action.setRoom(rooms.get(roomChoice(rooms)));
			if(action.getRoom() instanceof TowerRoom){
				action.setChosenCost(((TowerRoom) action.getRoom()).
						getPlacedCard().getCosts().get(costChoice(action.getRoom())));
			}
		}catch(IndexOutOfBoundsException e){
			output.println("\nWARNING: Impossibile posizionare!\n");
			placeAction(billboard, turnOfPlayer);
		}
		
		action.setRequiredResources(resourcesChoice());
		
		setChanged();
		notifyObservers(action);
	}
	
	
	private void checkPlayerAction(int turnOfPlayer){
		CheckPlayer checkPlayer = new CheckPlayer(turnOfPlayer);
		setChanged();
		notifyObservers(checkPlayer);
	}
	
	
	private void printCards(List<TowerRoom> towerRooms){
		for(TowerRoom t : towerRooms){
			if(!(t.isFull())){
				output.println(t.getPlacedCard().toString());
			}
		}
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
	 * integer representing the chosen room.
	 */
	public int roomChoice(List<Room> rooms){
		output.println("\nQuale spazio azione desideri occupare?");
		output.println("Scelte disponibili:\n");
		printRooms(rooms);
		String input = scanner.next();
		int choice = Integer.parseInt(input);
		return choice;
	}
	
	
	public int towerRoomChoice(List<TowerRoom> towerRooms){
		output.println("\nQuale spazio azione desideri occupare?");
		output.println("Scelte disponibili:\n");
		printTowerRooms(towerRooms);
		String input = scanner.next();
		int choice = Integer.parseInt(input);
		return choice;
	}
	
	
	
	/* Method that asks the user for an input. In particular it questions the user if he wants to use some 
	 * resources during 'place' action. After instantiating a Resources' object, the method fills it with
	 * user inputs and returns the object to the caller. 
	 */
	private Resources resourcesChoice(){
		Resources resources = new Resources();
		String sResource = null;
		output.println("Desideri spendere qualche risorsa?" + 
				"\nCoins\tWoods\tStones\tServants\tMilitaryPoints");
		do{
			output.print("Inserisci il nome della risorsa: ['q' per terminare]  ");
			sResource = scanner.next().toUpperCase();
			
			if(sResource != "Q" && resources.getResourcesMap().containsKey(sResource)){
				output.printf("Inserisci la quantità di %s che desideri spendere:  ", sResource.toLowerCase());
				try{
					int value = Integer.parseInt(scanner.next());
					resources.getResource(sResource).add(value);
				}catch(NumberFormatException e){
					output.println("Inserire un numero!");
				}
			}
			else if(!(sResource.contains("Q"))){
				output.println("Risorsa inesistente!");
			}
			
		}while(!(sResource.contains("Q")));

		return resources;
	}
	
	
	private int costChoice(Room room){
		if(((TowerRoom) room).getPlacedCard().getCardColor() == TowerColor.GREEN){
			return 0;
		}
		else{
			TowerRoom towerRoom = (TowerRoom) room;
			output.println("Quale costo si desidera utilizzare?");
			for(int i = 0; i < towerRoom.getPlacedCard().getCosts().size(); i++){
				output.println(i + " - " + towerRoom.getPlacedCard().getCosts().get(i).toString());
			}
		}
		try{
			String costChoice = scanner.next();
			return Integer.parseInt(costChoice);
		}catch(NumberFormatException e){
			output.println("\nWARNING: Inserimento errato!");
			return -1;
		}
	}
	
	
	private void fakePlaceAction(FakePlace fakePlace, Billboard billboard){
		List<TowerRoom> towerRooms = billboard.getTable().getTowerRoomList();
		towerRooms = billboard.getTable().getTowersRoomsOfColor(towerRooms, fakePlace.getTowerColor());
		if(fakePlace.getRoom() != null){
			output.println("\n\nWARNING: Soluzione non valida!");
		}
		output.println("\nHai a disposizione un'azione su una torre " + 
						fakePlace.getTowerColor().toString().toLowerCase() + 
						" di valore " + fakePlace.getPawn().getValue());
		output.println("Desideri attivare l'effetto? [yes/no]");
		String choice = scanner.next().toLowerCase();
		if(choice.contains("yes")){
			try{
				fakePlace.setRoom(towerRooms.get(towerRoomChoice(towerRooms)));
					fakePlace.setChosenCost(((TowerRoom) fakePlace.getRoom()).
							getPlacedCard().getCosts().get(costChoice(fakePlace.getRoom())));
			}catch(IndexOutOfBoundsException e){
				output.println("\nWARNING: Impossibile posizionare!\n");
				fakePlaceAction(fakePlace, billboard);
			}
		
			fakePlace.setRequiredResources(resourcesChoice());
		
			setChanged();
			notifyObservers(fakePlace);
		}
		if(choice.contains("no")){
			setChanged();
			notifyObservers();
		}
		else{
			output.println("Soluzione non contemplata!");
			fakePlaceAction(fakePlace, billboard);
		}
	}
	

	
	
	/* Method always called by the update method. It will show the current situation of the billboard, with 
	 * particular regard to rooms. So it will not show player's resources and the personal board in general.
	 * Will be given an action able to show player's personal board.
	 */
	public void showModel(Billboard billboard){
		showChangeOfTurn(billboard.getTurnOfPlay());
		printRooms(billboard.getTable().getRooms());
		printCouncil(billboard.getTable().getCouncilPalaceList());
	}
	private void showChangeOfTurn(TurnOfPlay turnOfPlay){
		output.println("\n\nTurno " + turnOfPlay.getTurn() + 
				" del periodo numero " + turnOfPlay.getPeriod() + ":\n");
	}
	private void printRooms(List<Room> rooms){
		int spaces = 0;
		for(int i = 0; i < rooms.size(); i++){
			if((Pawn) rooms.get(i).getPawn() == null){
				output.print(spaces + " - ");
				output.println(((Room) rooms.get(i)).toString());
			}
			spaces++;
		}
	}
	private void printTowerRooms(List<TowerRoom> towerRooms){
		int spaces = 0;
		for(int i = 0; i < towerRooms.size(); i++){
			if(towerRooms.get(i).getPawn() == null){
				output.print(spaces + " - ");
				output.println(towerRooms.get(i).toString());
			}
			spaces++;
		}
	}

	private void printCouncil(List<CouncilRoom> councils){
		System.out.println(councils.size()-1 + " pedoni già piazzati nel Palazzo del Consiglio!");
	}
	private void printPlayer(Player player){
		output.println("Giocatore " + player.getColor().toString().substring(0, 1) + 
				player.getColor().toString().substring(1, player.getColor().toString().length()).toLowerCase());
		printPlayerResources(player);
		printPlayerCards(player);
	}
	private void printPlayerResources(Player player){
		output.println("\nRisorse possedute:");
		for(Map.Entry<String, Resource> entry : player.getResources().getResourcesMap().entrySet()){
			output.println(entry.getValue().toString());
		}
	}
	private void printPlayerCards(Player player){
		output.println("\nCarte sviluppo possedute:");
		for(DevelopmentCard d : player.getCards()){
			output.println("Nome: " + d.getCardName() + "\tColore: " + d.getCardColor());
		}
	}
	private void printMessage(String message){
		output.println(message);
	}
	private void printCouncilChoices(List<Resources> list){
		output.println("\n");
		for(int i = 0; i < list.size(); i++){
			output.print(i + " - ");
			output.println(list.get(i).toString());
		}
	}
	
	private void changeCouncilPrivileges(Billboard billboard, Resources resources){
		int i = 0;
		int quantity = resources.getResource("COUNCILPRIVILEGES").getValue();
		List<Resources> tempList = new ArrayList<Resources>(billboard.getCouncilChoices());
		Resources resourcesToAdd = new Resources();
		
		output.println("\nPuoi scegliere " + quantity
				+ " conversione/i al posto del privilegio del consiglio!");
		
		while(i < quantity){
			printCouncilChoices(tempList);
			output.print("\nScegli quale conversione effettuare: ");
			try{
				int value = Integer.parseInt(scanner.next());
				resourcesToAdd.add(tempList.get(value));
				tempList.remove(value);
				i++;	
			}catch(NumberFormatException e){
				output.println("\nWARNING! Non hai inserito un numero!\n");
			}catch(IndexOutOfBoundsException e){
				output.println("\nWARNING! Scelta non disponibile!");
			}
		}
		
		setChanged();
		notifyObservers(resourcesToAdd);
	}
	
	
	
	@Override
	public void update(Observable o, Object obj){
		if(!(o instanceof Controller)){
			throw new IllegalArgumentException();
		}
		if(obj instanceof Billboard){
			showModel((Billboard) obj);
			startTurn((Billboard) obj);
		}
		if(obj instanceof Player){
			printPlayer((Player) obj);
			startTurn(((Controller) o).getBillboard());
		}
		if(obj instanceof String){
			printMessage((String) obj);
			startTurn(((Controller) o).getBillboard());
		}
		if(obj instanceof FakePlace){
			fakePlaceAction((FakePlace) obj, ((Controller) o).getBillboard());
		}
		if(obj instanceof Resources){
			changeCouncilPrivileges(((Controller) o).getBillboard(), (Resources) obj);
		}
	}
	
	
	
}

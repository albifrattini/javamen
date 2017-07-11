package it.polimi.ingsw.ps03.networking.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import it.polimi.ingsw.ps03.actions.ActionChoices;
import it.polimi.ingsw.ps03.actions.CheckPlayer;
import it.polimi.ingsw.ps03.actions.ClientFakePlace;
import it.polimi.ingsw.ps03.actions.ClientPlace;
import it.polimi.ingsw.ps03.actions.Pass;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.billboard_pack.TurnOfPlay;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.resources.Resource;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.CouncilRoom;
import it.polimi.ingsw.ps03.room_pack.Room;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;
/**
 * this class shows to the client all the possible actions and instantiates objects that represents them.
 *
 */
public class LocalView extends Observable implements Observer{
	private Scanner scanner;
	private PrintStream output;
	private Billboard serverModel;
	
	public LocalView(InputStream inputStream, OutputStream output){
		this.scanner = new Scanner(inputStream);
		this.output = new PrintStream(output);
	}		
	/* Override of the method of the Interface Runnable. It simply initializes the game
	*/
	public void initGame(){
		output.println("####  LORENZO IL MAGNIFICO! ####\n"+"Come ti chiami? ");
		String name = scanner.next();
		setChanged();
		notifyObservers(name);		
	}
	
	
/**
 * 	  Is the principal function of the class 'BillboardView'. It receives the already edited model
 *	  (billboard in our case) and an integer that represents the player that is called at playing.
 *	  In this method we give an output on the screen representing all the possible actions that a 
 *	  player can do. After choosing one, a specified method will be called to ask and process all
 *	  the details necessary to build the object 'Action'.
 * @param billboard Billboard
 */
	 
		
	public void startTurn(Billboard billboard){
		int turnOfPlayer = billboard.getTurnOfPlay().getPlayerToPlay();
		ActionChoices choice = null;
			do{
				choice = selectAction();
			}while(choice == null);
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
				case PASS:
					setChanged();
					notifyObservers(new Pass());
					break;
				default:
					output.println("Errore nella scelta azione!");
					break;
				}
	}
	
	
	/** In this method we simply show the possible actions and after taking one, we return it to the
	 * method that called this.
	 */
	public ActionChoices selectAction(){
		ActionChoices choice = null;
		try{
			output.println("\nCosa desideri fare?");
			output.println("Azioni possibili:\n --> Place\n --> CheckPlayer\n --> CheckCards\n --> Pass\n");
			choice = ActionChoices.valueOf(scanner.next().toUpperCase());
		}catch(IllegalArgumentException e){
			output.println("\nWARNING: Soluzione non valida!\n");
		}
		return choice;
	}	
	
	
/**
 * 	After showing available rooms (action spaces) and pawns, this method builds the object 'Action' asking
 *	the user what to do. After this a setChanged() and a notifyObservers(action) with argument the action
 *	object just built. This will be processed by the Controller.
 * @param billboard Billboard
 * @param turnOfPlayer Int that represents who has to move
 */
	 
	public void placeAction(Billboard billboard, int turnOfPlayer){
		Player player = billboard.getPlayers().get(turnOfPlayer);
		Resources spentResources = new Resources();
		ClientPlace action = new ClientPlace(ActionChoices.PLACE, player.getColor());	
		action.setPawnColor(player.getPawn(pawnChoice(player)).getDiceColor());
		List<Room> rooms = billboard.getTable().getRooms();
		action.setRoom(roomChoice(rooms));
		Room room = rooms.get(action.getRoom());
		if(room instanceof TowerRoom){
			try{
				action.setChosenCost(((TowerRoom) room).
						getPlacedCard().getCosts().get(costChoice(room)));
			}catch(IndexOutOfBoundsException e){
				output.println("[WARNING]  Inserimento non corretto!");
				placeAction(billboard, turnOfPlayer);
			}
			if(towerHasPawns((TowerRoom) room)){
				subPlacementCoins(spentResources, action);
			}
		}
		action.setSpentResources(resourcesChoice(spentResources));
		setChanged();
		notifyObservers(action);
	}
	
	private boolean towerHasPawns(TowerRoom towerRoom){
		for(TowerRoom t: serverModel.getTable().getTowerRoomList()){
			if(t.getTowerRoomColor() == towerRoom.getTowerRoomColor() && 
					t.getPawn() != null){
				return true;
			}
		}
		return false;
	}
	
	public void subPlacementCoins(Resources resources, ClientPlace action){
		output.println("Desideri posizionare sulla torre pagando 3 monete? [yes/no]");
		String choice = scanner.next();
		if(choice.contains("y")){
			resources.getResource("COINS").add(3);
		}
		else{
			if(action instanceof ClientFakePlace){
				fakePlaceAction((ClientFakePlace) action);
			}
			startTurn(serverModel);
		}
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
	
	
/**
 * 	 Method that helps showing all the possible pawns to place and gets the input from the user. 	 
 * @param player Player
 * @return String representing the chosen pawn
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
	
	
/**
 * 	 Method that helps showing all the possible rooms and gets the input from the user. It will return an 
 *	 integer representing the chosen room.
 *	
 * @param rooms List<Room>
 * @return int
 */
	public int roomChoice(List<Room> rooms){
		int choice = -1;
		output.println("\nQuale spazio azione desideri occupare?");
		output.println("Scelte disponibili:\n");
		printRooms(rooms);
		try{
			String input = scanner.next();
			choice = Integer.parseInt(input);
		}catch(IndexOutOfBoundsException e){
			output.println("\n[WARNING]  Spazio azione non individuato!");
			roomChoice(rooms);
		}catch(NumberFormatException e){
			output.println("\n[WARNING]  Non hai inserito un numero!");
			roomChoice(rooms);
		}
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
/**
 * 	 Method that asks the user for an input. In particular it questions the user if he wants to use some 
 *	 resources during 'place' action. After instantiating a Resources' object, the method fills it with
 *	 user inputs and returns the object to the caller. 
 * @param resources Resources 
 * @return Resources
 */
	 
	private Resources resourcesChoice(Resources resources){
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
	
	
	private void fakePlaceAction(ClientFakePlace fakePlace){
		Billboard actualModel = fakePlace.getModel();
		List<TowerRoom> towerRooms = actualModel.getTable().getTowerRoomList();
		Resources spentResources = new Resources();
		towerRooms = actualModel.getTable().getTowersRoomsOfColor(towerRooms, fakePlace.getTowerColor());
		if(fakePlace.getRoom() != -1){
			output.println("\n\nWARNING: Soluzione non valida!");
		}
		output.println("\nHai a disposizione un'azione su una torre " + 
						fakePlace.getTowerColor().toString().toLowerCase() + 
						" di valore " + fakePlace.getPawn().getValue());
		output.println("Desideri attivare l'effetto? [yes/no]");
		String choice = scanner.next().toLowerCase();
		if(choice.contains("yes")){
			try{
				fakePlace.setRoom(towerRoomChoice(towerRooms));
				TowerRoom room = towerRooms.get(fakePlace.getRoom());
				if(towerHasPawns((TowerRoom) room)){
					subPlacementCoins(spentResources, fakePlace);
				}
				fakePlace.setChosenCost(((TowerRoom) room).
				getPlacedCard().getCosts().get(costChoice(room)));
			}catch(IndexOutOfBoundsException e){
				output.println("\nWARNING: Impossibile posizionare!\n");
				fakePlaceAction(fakePlace);
			}		
			fakePlace.setSpentResources(resourcesChoice(spentResources));		
			setChanged();
			notifyObservers(fakePlace);
		}
		else if(choice.contains("no")){
			setChanged();
			notifyObservers();
		}
		else{
			output.println("Soluzione non contemplata!");
			fakePlaceAction(fakePlace);
		}
	}
		
/**
 * 	 Method always called by the update method. It will show the current situation of the billboard, with 
 *	 particular regard to rooms. So it will not show player's resources and the personal board in general.
 *	 Will be given an action able to show player's personal board.
 * @param billboard Billboard
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
	
	
	private void updateModel(Billboard billboard){
		this.serverModel = billboard;
	}
	
	private void printCouncilChoices(List<Resources> list){
		output.println("\n");
		for(int i = 0; i < list.size(); i++){
			output.print(i + " - ");
			output.println(list.get(i).toString());
		}
	}
	
	private void changeCouncilPrivileges(Resources resources){
		int i = 0;
		int quantity = resources.getResource("COUNCILPRIVILEGES").getValue();
		List<Resources> tempList = new ArrayList<Resources>(serverModel.getCouncilChoices());
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
		if(!(o instanceof Client)){
			throw new IllegalArgumentException();
		}
		if(obj instanceof Billboard){
			Billboard model = (Billboard) obj;
			updateModel(model);
			showModel(model);
		}
		if(obj instanceof Player){
			printPlayer((Player) obj);
			startTurn(serverModel);
		}
		if(obj instanceof String){
			printMessage((String) obj);
			if(serverModel != null && !(((String) obj).contains("vinto"))){
				startTurn(serverModel);
			}	
		}
		if(obj instanceof ClientFakePlace){
			fakePlaceAction((ClientFakePlace) obj);
		}
		if(obj instanceof Resources){
			changeCouncilPrivileges((Resources) obj);
		}
	}

}

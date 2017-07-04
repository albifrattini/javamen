package it.polimi.ingsw.ps03.networking.virtualView;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.billboard_pack.TurnOfPlay;
import it.polimi.ingsw.ps03.players.*;
import it.polimi.ingsw.ps03.room_pack.CouncilRoom;
import it.polimi.ingsw.ps03.room_pack.Room;
import it.polimi.ingsw.ps03.actions.ActionChoices;
import it.polimi.ingsw.ps03.actions.FakePlace;

import java.io.IOException;
import java.util.List;


public class RemoteView extends View implements Observer<Object> {

	private Connection connection;
	
	
	public RemoteView(Player player, Connection c){
		super(player);
		this.connection = c;
		c.register(this);
	    System.out.println("aggiunto alla lista di observer");	
	}

	public Connection getConnection(){
		return connection;
	}

	public void startTurn(Billboard billboard) throws IOException{
		connection.send("E' il tuo turno");
		showModel(billboard);
	}
	@Override
	protected void showModel(Billboard billboard){
		try {
			System.out.println("Invia copia del model");
				
//			Billboard model = (Billboard) billboard.clone();			
//			showChangeOfTurn(billboard.getTurnOfPlay());
//			printRooms(billboard.getTable().getRooms());
//			printCouncil(billboard.getTable().getCouncilPalaceList());			
			connection.send(billboard);
		
		} catch (IOException/* | CloneNotSupportedException*/ e) {
			 System.out.println("Non son riuscito a inviare il model");
		}
	}
	
	
//	private void printTowerRooms(List<TowerRoom> towerRooms) throws IOException{
//		int spaces = 0;
//		for(int i = 0; i < towerRooms.size(); i++){
//			if(towerRooms.get(i).getPawn() == null){
//				connection.send(spaces + " - ");
//				connection.send(towerRooms.get(i).toString());
//			}
//			spaces++;
//		}
//	}
	
	private void showChangeOfTurn(TurnOfPlay turnOfPlay) throws IOException{
			connection.send("\n\nTurno " + turnOfPlay.getTurn() + 
				" del periodo numero " + turnOfPlay.getPeriod() + ":\n");
	}
	
	
	private void printRooms(List<Room> rooms) throws IOException{
		int spaces = 0;
		for(int i = 0; i < rooms.size(); i++){
			if((Pawn) rooms.get(i).getPawn() == null){
				connection.send(spaces + " - ");
				connection.send(((Room) rooms.get(i)).toString());
			}
			spaces++;
		}
	}
	
	private void printCouncil(List<CouncilRoom> councils){
		System.out.println(councils.size()-1 + " pedoni già piazzati nel Palazzo del Consiglio!");
	}
	
	
	@Override//sovrascrive la notify di Observer quando viene invocata
	public void notify(Object message) throws IOException {		
		
		System.out.println("Ricevuto " + message.toString() + " dal giocatore " +player.toString());//riceve man mano e stampa sul server ciò che ha ricevuto
		
		try{	
			System.out.println("entrato nella notify dellla remoteView");
			
//			ActionChoices choice = ActionChoices.parseInput(message);
			
			processChoice(message);
			
			
		}catch(IllegalArgumentException e){
			connection.send("Error, Action not valid!Please try again");
						
		}		
	}

	
	public void update(Observable o, Object obj) {
		
	}

	
//	public void update(Observable o, Object obj) {		
//		if(obj instanceof String){			
//			try {
//				connection.send((String) obj);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if(obj instanceof Player){
//			try {
//				connection.send((Player) obj);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if(obj instanceof Billboard){
//			showModel((Billboard)obj);
//			}
//		
//		if(obj instanceof FakePlace){
//			try {
//				connection.send((FakePlace) obj);
//			} catch (IOException e) {
//				e.printStackTrace();
//		}
//		}
//		}
	
}

		







		
	


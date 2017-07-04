package it.polimi.ingsw.ps03.actions;
import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.players.PawnDiceColor;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.players.PlayerColor;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.Room;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlaceTest {
	
	Place mPlace;
	Player mPlayer;
    Pawn mPawn;
    Room mRoom;
    Resources mRequiredResources;
    Resources mChosenCost;

	@Before
	public void setUp() throws Exception {
		mPlayer = new Player("nome prova",PlayerColor.BLUE, 5);
		mPlace = new Place(mPlayer);
	}

	@Test
	public void testSetPlayer() {
		mPlace.setPlayer(mPlayer);
	    assertEquals(mPlayer,mPlace.getPlayer());

	}

	@Test
	public void testSetPawn() {
		mPlace.setPawn(mPawn);
		assertEquals(mPawn,mPlace.getPawn());
	}

	@Test
	public void testSetRoom() {
		mPlace.setRoom(mRoom);
		assertEquals(mRoom, mPlace.getRoom());
	}

	@Test
	public void testSetRequiredResources() {
	   mPlace.setRequiredResources(mRequiredResources);
	   assertEquals(mRequiredResources, mPlace.getRequiredResources());
	}

	@Test
	public void testSetChosenCost() {
        mPlace.getChosenCost();
        assertEquals(mChosenCost,mPlace.getChosenCost());
        }
	@Test
	public void testGetPlayer() {
		assertEquals(mPlayer,mPlace.getPlayer());
	}

	@Test
	public void testGetPawn() {
		assertEquals(null,mPlace.getPawn());
	}

	@Test
	public void testGetRequiredResources() {
		   assertEquals(null, mPlace.getRequiredResources());
	}

	@Test
	public void testGetChosenCost() {
        assertEquals(null,mPlace.getChosenCost());
	}

	@Test
	public void testGetRoom() {
		assertEquals(null, mPlace.getRoom());
	}

}

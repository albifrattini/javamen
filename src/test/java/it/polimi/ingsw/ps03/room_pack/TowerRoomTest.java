package it.polimi.ingsw.ps03.room_pack;
import it.polimi.ingsw.ps03.resources.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TowerRoomTest {
 TowerRoom mRoom;
 Resources mRes;
 TowerColor mColor = TowerColor.BLUE;
	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mRoom = new TowerRoom(mColor, 3, mRes);
	}

	@Test
	public void testGetTowerRoomColor() {
		assertEquals(mColor, mRoom.getTowerRoomColor());
	}

	@Test
	public void testGetPlacedCard() {
		assertEquals(null, mRoom.getPlacedCard());
	}

	@Test
	public void testFreeCardSpace() {
		assertEquals(null, mRoom.getPlacedCard());
	}

}

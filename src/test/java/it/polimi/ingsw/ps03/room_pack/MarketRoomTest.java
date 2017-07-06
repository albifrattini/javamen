package it.polimi.ingsw.ps03.room_pack;
import it.polimi.ingsw.ps03.resources.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MarketRoomTest {
	
	MarketRoom mRoom;
	Resources mRes;

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mRoom = new MarketRoom(mRes);
	}

	@Test
	public void testGetResources() {
		assertEquals(mRes, mRoom.getResources());
	}

}

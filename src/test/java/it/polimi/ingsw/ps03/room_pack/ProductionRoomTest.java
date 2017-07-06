package it.polimi.ingsw.ps03.room_pack;
import it.polimi.ingsw.ps03.resources.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductionRoomTest {
	ProductionRoom mRoom;
	Resources mRes;
	TowerColor mColor=TowerColor.BLUE;
	

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mRoom = new ProductionRoom(mColor,mRes);
	}

	@Test
	public void testGetColorEffect() {
		assertEquals(mColor, mRoom.getColorEffect());
	}

}

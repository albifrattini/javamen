package it.polimi.ingsw.ps03.room_pack;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TableTest {
	Table mTable;
	List<TowerRoom> mRooms;
	

	@Before
	public void setUp() throws Exception {
		mTable = new Table();
		mTable.buildTowerRooms();
		mTable.buildHarvestingRooms(4);
		mTable.buildMarketRooms(4);
		mTable.buildTable(4);
		mRooms = mTable.getTowerRoomList();
	}

	@Test
	public void testGetTowerRoomList() {
	   assertEquals(32,mTable.getTowerRoomList().size());
	}

	@Test
	public void testGetMarketRoomList() {
		assertEquals(8, mTable.getMarketRoomList().size());
	}

	@Test
	public void testGetHarvestingRoomList() {
		assertEquals(8, mTable.getHarvestingRoomList().size());
	}

	@Test
	public void testGetProductionRoomList() {
		assertEquals(4, mTable.getProductionRoomList().size());
	}

	@Test
	public void testGetCouncilPalaceList() {
		assertEquals(1, mTable.getCouncilPalaceList().size());
	}

	@Test
	public void testGetRooms() {
		assertEquals(53, mTable.getRooms().size());
	}

	@Test
	public void testAddCouncilRoom() {
		mTable.addCouncilRoom();
		assertEquals(2,mTable.getCouncilPalaceList().size());
	}

	@Test
	public void testGetTowersRoomsOfColor() {
	   assertEquals(8,mTable.getTowersRoomsOfColor(mRooms, TowerColor.BLUE).size());
		
	}


}

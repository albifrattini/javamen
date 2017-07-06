package it.polimi.ingsw.ps03.actions;
import it.polimi.ingsw.ps03.billboard_pack.*;
import it.polimi.ingsw.ps03.players.PawnDiceColor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChangeTurnTest {
	ChangeTurn mChange;
	Billboard mBillboard;
	PawnDiceColor mColor = PawnDiceColor.BLACK;
	

	@Before
	public void setUp() throws Exception {
		mBillboard = new Billboard();
		mChange = new ChangeTurn(mBillboard);
	}

	@Test
	public void testApplyAction() {
		mChange.applyAction();
		assertEquals(1,mChange.getBillboard().getTurnOfPlay().getPeriod());
		assertEquals(1,mChange.getBillboard().getTurnOfPlay().getMiniTurn());
		assertEquals(1,mChange.getBillboard().getTurnOfPlay().getTurn());

	}
	

	@Test
	public void testRefreshTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewPawns() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDiceColorValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplaceCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeTurnOfPlay() {
		fail("Not yet implemented");
	}

}

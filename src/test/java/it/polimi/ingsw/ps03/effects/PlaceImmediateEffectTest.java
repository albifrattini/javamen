package it.polimi.ingsw.ps03.effects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class PlaceImmediateEffectTest {

	PlaceImmediateEffect mEffect;
	Resources mRes;
	Resources mDis;

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mDis = new Resources(5);
		mEffect = new PlaceImmediateEffect( TowerColor.BLUE ,3 ,mRes, mDis, "mNome");
	}

	@Test
	public void testGetPlaceTowerColor() {
		assertEquals(TowerColor.BLUE, mEffect.getPlaceTowerColor());
	}

	@Test
	public void testGetPlaceDiceValue() {
		assertEquals(3, mEffect.getPlaceDiceValue());
	}

	@Test
	public void testGetDiscount() {
		assertEquals(mDis, mEffect.getDiscount());
	}

}

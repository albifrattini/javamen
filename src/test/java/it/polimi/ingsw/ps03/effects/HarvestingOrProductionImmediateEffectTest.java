package it.polimi.ingsw.ps03.effects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class HarvestingOrProductionImmediateEffectTest {

	HarvestingOrProductionImmediateEffect mEffect;
	Resources mRes;
	Resources mHRes;
	

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mHRes = new Resources();
		mEffect = new HarvestingOrProductionImmediateEffect(3,TowerColor.YELLOW ,mRes, "mName");
	}

	@Test
	public void testGetDiceValue() {
        assertEquals(3, mEffect.getDiceValue());
	}

	@Test
	public void testGetColor() {
        assertEquals(TowerColor.YELLOW, mEffect.getColor());
	}

	@Test
	public void testEffectType() {
		assertEquals("Produzione", mEffect.effectType());
	}

}

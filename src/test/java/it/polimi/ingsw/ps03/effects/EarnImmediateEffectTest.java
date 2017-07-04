package it.polimi.ingsw.ps03.effects;
import it.polimi.ingsw.ps03.resources.*;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EarnImmediateEffectTest {
	EarnImmediateEffect mEffect;
	Resources mRes;
	

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mEffect = new EarnImmediateEffect(TowerColor.GREEN, mRes, "mNome");
	}

	@Test
	public void testGetEarnResources() {
	   assertEquals(mRes, mEffect.getEarnResources());
	}

	@Test
	public void testGetEarnTowerColor() {
		assertEquals(TowerColor.GREEN, mEffect.getEarnTowerColor());
	}

	@Test
	public void testEffectType() {
		assertEquals("In base al numero di carte green possedute", mEffect.effectType());
	}

}

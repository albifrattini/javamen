package it.polimi.ingsw.ps03.effects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.resources.Resources;

public class GiveResourcesImmediateEffectTest {
	
	GiveResourcesImmediateEffect mEffect;
	Resources mRes;
	

	@Before
	public void setUp() throws Exception {
		mRes = new Resources();
		mEffect = new GiveResourcesImmediateEffect( mRes, "mNome");
	}


	@Test
	public void testGetGivenResources() {
		assertEquals(mRes, mEffect.getGivenResources());
	}

}

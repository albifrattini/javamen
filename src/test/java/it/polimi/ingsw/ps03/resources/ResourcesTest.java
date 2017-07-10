package it.polimi.ingsw.ps03.resources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResourcesTest {
	Resources mRes;
	Resources aRes;

	@Before
	public void setUp() throws Exception {
		mRes = new Resources(5);
		aRes = new Resources (3);
	}

	@Test
	public void testAdd() {
		mRes.add(aRes);
		assertEquals(8,mRes.getResource("COINS").getValue());
		
	}

	@Test
	public void testSub() {
		mRes.sub(aRes);
		assertEquals(2,mRes.getResource("COINS").getValue());
		
	}
	@Test
	public void testGetResource(){
		assertEquals("Coins",mRes.getResource("COINS").getName());
	}

	

}

package it.polimi.ingsw.ps03.development_card;
import it.polimi.ingsw.ps03.effects.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.billboard_pack.TurnOfPlay;
import it.polimi.ingsw.ps03.effects.Effect;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevelopmentCardTest {
	
 DevelopmentCard devCard;
 Effect mEffect;
 Resources mRes;
	
 @Before
	public void setUp() throws Exception {
	 	 mRes = new Resources();
		 devCard = new DevelopmentCard(3,2,3,"il Boscaiolo", TowerColor.GREEN);
		 mEffect = new GiveResourcesImmediateEffect(mRes, "GIVE");
	}

	
 	@Test
 	public void testGetDevelopmentCard(){
 		assertEquals(devCard, devCard.getCard());
 	}

	@Test
	public void testGetId() {
		assertEquals(3,devCard.getId());
	}

	@Test
	public void testGetCardName() {
		assertEquals("il Boscaiolo", devCard.getCardName());
	}

	@Test
	public void testGetCardColor() {
		assertEquals(TowerColor.GREEN, devCard.getCardColor());
	}

	@Test
	public void testGetCardPeriod() {
	     assertEquals(2,devCard.getCardPeriod());
		
	}

	@Test
	public void testGetDiceValue() {
		assertEquals(3, devCard.getDiceValue());
		
	}
	
	@Test
	public void testGetImmediateEffect(){
		assertEquals(null, devCard.getImmediateEffect());
	}

	@Test
	public void setImmediateEffect() {
		devCard.setImmediateEffect(mEffect);
		assertEquals(mEffect, devCard.getImmediateEffect()); 
		}
	
	@Test
	public void testSetPeriod() {
		devCard.setPeriod(1);
		assertEquals(1,devCard.getCardPeriod());
	}

	@Test
	public void testSetColor() {
		devCard.setColor(TowerColor.VIOLET);
		assertEquals(TowerColor.VIOLET, devCard.getCardColor());
	}

	@Test
	public void testGetCosts() {
		assertTrue(devCard.getCosts().isEmpty());
	}

	@Test
	public void testGetRequirements() {
		assertTrue(devCard.getRequirements().isEmpty());
	}

}
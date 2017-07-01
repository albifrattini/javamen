package it.polimi.ingsw.ps03.development_card;
import  it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.effects.Effect;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevelopmentCardTest {
	
	private int cardId=3;
	private int cardPeriod=2;
	private int diceValue=3;
	private String cardName="il Boscaiolo";
	private TowerColor cardColor=TowerColor.GREEN;
	private List<Resources> costs;
	private List<Resources> requirements;
	private Effect immediateEffect;

	public void DevelopmentCard(int mId,int mCardPeriod, int diceValue, String mCardName, 
			TowerColor mCardColor){ 
		
		cardId = mId;
		cardName = mCardName;
		cardColor = mCardColor;
		cardPeriod = mCardPeriod;
		costs = new ArrayList<Resources>(2);
		requirements = new ArrayList<Resources>(2);
		immediateEffect = null ; 
		}
	
	public int getId(){
		return cardId;
	}
	public String getCardName(){
		return cardName;
	}
	public TowerColor getCardColor(){
		return cardColor;
	}
	public int getCardPeriod(){
		return cardPeriod;
	}
	public int getDiceValue(){
		return diceValue;
	}
	public Effect getImmediateEffect(){
		return immediateEffect;
	}
	
	public void setImmediateEffect(Effect immidiateEffect) {
		this.immediateEffect = immidiateEffect; 
	}

	public void setPeriod(int period){
		this.cardPeriod = period;
	}
	public void setColor(TowerColor color){
		this.cardColor = color;
	}
	public List<Resources> getCosts() {
		return costs; 
	}
	public List<Resources> getRequirements(){
		return requirements;
	}

	

	@Test
	public void testGetId() {
		
		DevelopmentCard(cardId,cardPeriod,diceValue,cardName, cardColor);
		assertEquals(3,getId());
	}

	@Test
	public void testGetCardName() {
		DevelopmentCard(cardId,cardPeriod,diceValue,cardName, cardColor);
		assertEquals("il Boscaiolo", getCardName());
	}

	@Test
	public void testGetCardColor() {
		DevelopmentCard(cardId,cardPeriod,diceValue,cardName, cardColor);
		assertEquals(TowerColor.GREEN, getCardColor());
	}

	@Test
	public void testGetCardPeriod() {
		DevelopmentCard(cardId,cardPeriod,diceValue,cardName, cardColor);
		assertEquals(2,getCardPeriod());
		
	}

	@Test
	public void testGetDiceValue() {
		DevelopmentCard(cardId,cardPeriod,diceValue,cardName, cardColor);
		assertEquals(3, getDiceValue());
		
	}

	@Test
	public void testGetImmediateEffect() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetImmediateEffect() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPeriod() {
		setPeriod(1);
		assertEquals(1,cardPeriod);
	}

	@Test
	public void testSetColor() {
		setColor(TowerColor.VIOLET);
		assertEquals(TowerColor.VIOLET, cardColor);
	}

	@Test
	public void testGetCosts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRequirements() {
		fail("Not yet implemented");
	}

}

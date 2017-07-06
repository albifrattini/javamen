package it.polimi.ingsw.ps03.development_card;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevelopmentCardsTest {
	DevelopmentCards mDevCards;
	List<DevelopmentCard> mDeck;
	TowerColor mColor = TowerColor.BLUE;
	DevelopmentCard mDevCard = null;
	

	@Before
	public void setUp() throws Exception {
		mDevCards = new DevelopmentCards(96);
		mDevCards.build();
		mDeck = mDevCards.getCardsList();
	}

	@Test
	public void testGetCardsList() {
		assertEquals(96, mDevCards.getCardsList().size());
	}

	@Test
	public void testGetCardsOfColor() {
		assertEquals(24, mDevCards.getCardsOfColor(mColor, mDeck).size());
	}

	@Test
	public void testGetCardsOfPeriod() {
		assertEquals(32, mDevCards.getCardsOfPeriod(1, mDeck).size());
	}

	@Test
	public void testGetRandomCard() {
		assertThat(mDevCards.getRandomCard(mDeck), is(not(mDevCard)));
	}



}

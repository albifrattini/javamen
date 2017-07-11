package it.polimi.ingsw.ps03.development_card;

/**
 * this class prints the deck of development cards
 *
 */
public class MainCards {
	
	public static void main(String[] args){
		DevelopmentCards dev = new DevelopmentCards();
		dev.build();
		for(int i = 0; i < DevelopmentCards.getCardsList().size(); i++){
			System.out.println(DevelopmentCards.getCardsList().get(i).getId());
			System.out.println(DevelopmentCards.getCardsList().get(i).toString());
		}
	}
}

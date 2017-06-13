package it.polimi.ingsw.ps03.development_card;

public class MainCards {
	private DevCardFra dv = new DevCardFra();
	
	public static void main(String[] args){
		DevCardFra dv = new DevCardFra();
		dv.build();
		for(DevelopmentCard d : DevCardFra.getCardsList()){
			System.out.println(d.toString());
		}
	}
}

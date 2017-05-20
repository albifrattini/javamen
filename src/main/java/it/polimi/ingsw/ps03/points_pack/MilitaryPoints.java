package it.polimi.ingsw.ps03.points_pack;

public class MilitaryPoints {
	
	private int value;
	
	public MilitaryPoints(int value) {
		this.value = value;
	}
	public int getValue() {	
		return  this.value;
	}
	//il metodo set lo vorrei rendere privato
	public void setValue( int value) {
		this.value = value;	
	}

}

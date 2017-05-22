package it.polimi.ingsw.ps03.resource_pack;

public class Stones {

	private int value;
	
	public Stones(int value) {
		this.value = value;
	}
	public int getValue() {	
		return  this.value;
	}
	public void setValue(int value) {
		this.value = value;	
	}
	public void addValue(int value){
		this.value =+ value;
	}
	public void stealValue(int value){
		this.value =- value;
	}
	
}

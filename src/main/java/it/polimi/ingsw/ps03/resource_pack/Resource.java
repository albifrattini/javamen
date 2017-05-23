package it.polimi.ingsw.ps03.resource_pack;

public abstract class Resource {

	private int value;
	
	public Resource(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	public void addValue(int value){
		this.value =+ value;
	}
	public void stealValue(int value){
		this.value =- value;	//se <0 come lo gestisco?
	}
}

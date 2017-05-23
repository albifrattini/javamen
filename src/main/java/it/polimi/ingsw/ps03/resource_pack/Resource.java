package it.polimi.ingsw.ps03.resource_pack;

public abstract class Resource {

	private int value;
	
	public Resource(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	public void add(int value){
		this.value =+ value;
	}
	public void sub(int value){
		this.value =- value;
		if(this.value < 0){
			throw new IllegalArgumentException("Resource not sufficient!\n");
		}
	}
}

package it.polimi.ingsw.ps03.resources;

public abstract class Resource {
	
	private String rName;
	private int value;
	
	public Resource(String rName, int value){
		this.rName = rName.toUpperCase();
		this.value = value;
	}
	public Resource(String rName){
		this.rName = rName.toUpperCase();
		this.value = 0;
	}

	public String getName(){
		return this.rName;
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

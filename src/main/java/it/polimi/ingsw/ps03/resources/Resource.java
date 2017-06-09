package it.polimi.ingsw.ps03.resources;

public abstract class Resource {
	
	private String rName;
	private int value;
	
	public Resource(String resName, int value){
		rName = resName;
		this.value = value;
	}
	public Resource(String resName){
		this(resName, 0);
	}

	public String getName(){
		return rName;
	}
	public int getValue(){
		return this.value;
	}
	public void add(int value){
		this.value =+ value;
	}
	public void sub(int value){
		if(this.value < value){
			throw new IllegalArgumentException();
		}
		this.value =- value;
	}
	
	@Override
	public String toString(){
		return rName + ": " + value;
	}
}

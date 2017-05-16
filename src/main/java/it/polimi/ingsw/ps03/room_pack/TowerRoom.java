package it.polimi.ingsw.ps03.room_pack;

public class TowerRoom extends Room {
	
	private String color;
	private int floor;
	
	public TowerRoom (int requirement, String color, int floor){
		super(requirement);
		this.color = color;
		this.floor = floor;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public int getFloor(){
		return this.floor;
	}
	
	
}

package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.room_pack.TowerColor;;

public class RaiseValueOnColorDrawEffect extends Effect{

	private TowerColor color;
	private int valueToAdd;
	
	public RaiseValueOnColorDrawEffect(TowerColor mColor, int mValueToAdd, String name){
		super(name);
		color = mColor;
		valueToAdd = mValueToAdd;
	}
	
	public TowerColor getColor(){
		return color;
	}
	
	public int getValueToAdd(){
		return valueToAdd;
	}
	
	
}

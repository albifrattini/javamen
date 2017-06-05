package it.polimi.ingsw.ps03.room_pack;

public class ProductionRoom extends Room{
	
	public ProductionRoom(){
		super(1);
	}
	
	@Override
	public String toString(){
		return "Spazio Produzione:\tRequisito: " + getRequirement();
	}
}

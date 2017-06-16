package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class EarnImmediateEffect extends Effect{

	private Resources earnResources;
	private TowerColor earnCardColor; 
	
	public EarnImmediateEffect(TowerColor eCardColor, Resources eResources, String name){
		super(name);
		earnResources = eResources ;
		earnCardColor = eCardColor;
	}
	public Resources getEarnResources(){
		return earnResources;
	}
	public TowerColor getEarnTowerColor() {
		return earnCardColor;
	}
	
	public String effectType(){
		if(earnCardColor != null){
			return "In base al numero di carte " + earnCardColor.toString().toLowerCase() + " possedute"; 
		}
		else{
			return "Ogni due punti militari posseduti";
		}
	}
	
	@Override
	public String toString(){
		return "Nome effetto: " + getName() + "\nRisorse date: " + earnResources + effectType();
	}
}

package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.MilitaryPoints;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.resources.VictoryPoints;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class EarnImmediateEffect extends Effect{

	private Resources earnResources;
	TowerColor earnCardColor; 
	
	public EarnImmediateEffect(TowerColor eCardColor, Resources eResources){
		Resources earnResources = eResources ;
		TowerColor earnCardColor = eCardColor;
	
	}
	public Resources getEarnResources(){
		return earnResources;
	}
	public TowerColor getEarnTowerColor() {
		return earnCardColor;
	}
}

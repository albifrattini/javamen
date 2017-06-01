package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.resources.MilitaryPoints;
import it.polimi.ingsw.ps03.resources.VictoryPoints;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class HearnImmediateEffect extends Effect{

	private VictoryPoints givenVictoryPoints;
	private TowerColor color;
	private MilitaryPoints possMilitaryPoints;
	
	public HearnImmediateEffect(VictoryPoints gVictoryPoints, TowerColor gColor, MilitaryPoints gMilitaryPoints){
		givenVictoryPoints = gVictoryPoints;
		color = gColor;
		possMilitaryPoints = gMilitaryPoints;
	}
}

package it.polimi.ingsw.ps03.zzRegoleAvanzateLasciateLi;

import java.util.List;
import java.util.ArrayList;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.resources.Resource;

public class ResAndDev extends ResReq{

	private List<TowerColor> devCardYouNeed;
	
	public ResAndDev(int id, String name, String lcEffect, List<Resource> mResYouNeed) {
		super(id, name, lcEffect);
      
		List<TowerColor> devCardYouNeed = new ArrayList<TowerColor>();
	}

	public List<TowerColor>getDevCard(){
		return devCardYouNeed;
	}
}

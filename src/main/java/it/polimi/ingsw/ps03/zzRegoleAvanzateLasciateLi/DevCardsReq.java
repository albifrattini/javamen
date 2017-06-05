package it.polimi.ingsw.ps03.zzRegoleAvanzateLasciateLi;

import java.util.List;
import java.util.ArrayList;
import it.polimi.ingsw.ps03.room_pack.TowerColor;

public class DevCardsReq extends LeaderCard{

	private List<TowerColor> mColorYouNeed;
	
	public DevCardsReq(int id, String name, String lcEffect) {
		super(id, name, lcEffect);
		mColorYouNeed = new ArrayList<TowerColor>();
		}

	public List<TowerColor> getColors(){
		return mColorYouNeed;
	}
	
}
	
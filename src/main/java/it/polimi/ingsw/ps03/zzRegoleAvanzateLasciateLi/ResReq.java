package it.polimi.ingsw.ps03.zzRegoleAvanzateLasciateLi;

import java.util.List;
import java.util.ArrayList;
import it.polimi.ingsw.ps03.resources.Resource;

public class ResReq extends LeaderCard{

	private List<Resource> mResYouNeed;
	
	public ResReq(int id, String name, String lcEffect) {
		super(id, name, lcEffect);
		List<Resource> mResYouNeed = new ArrayList<Resource>();
		}

	public List<Resource> getResources(){
		return mResYouNeed;
		}
	
	

	}
	
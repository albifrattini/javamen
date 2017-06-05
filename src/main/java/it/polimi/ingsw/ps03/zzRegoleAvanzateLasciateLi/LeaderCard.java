package it.polimi.ingsw.ps03.zzRegoleAvanzateLasciateLi;

public abstract class LeaderCard {

	private int mId;
	private String mName;
	private String mLcEffect; //per ora lo lasciamo string per importare da xml gli effetti e poi lo modificheremo
	
	public LeaderCard(int id, String name, String lcEffect){
		mId = id;
		mName = name;
		mLcEffect = lcEffect;	
								}
	
	@Override
	public String toString(){
		return "id: " + mId + "\nCard Name: " + mName +
				"\nCard Effect: " + mLcEffect;
	}
	
	public int getLcId(){
		return mId;
	}
	
	public String getLcName(){
		return mName;
	}
	
	public String getLcEffect(){
		return mLcEffect;
	}
	
	
	
}

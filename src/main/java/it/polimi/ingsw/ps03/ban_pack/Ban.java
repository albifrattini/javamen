package it.polimi.ingsw.ps03.ban_pack;

public class Ban {

	private FirstBan firstBan;
	private SecondBan secondBan;
	private ThirdBan thirdBan;
	
	public Ban(){
		firstBan = new FirstBan(false, false, false, false);
		secondBan = new SecondBan(false, false, false, false);
		thirdBan = new ThirdBan(false, false, false, false);
	}



}


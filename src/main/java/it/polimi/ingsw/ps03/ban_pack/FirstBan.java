package it.polimi.ingsw.ps03.ban_pack;

public class FirstBan {
	/*private BanEffect effect;*/
	private boolean banBlue;
	private boolean banYellow;
	private boolean banGreen;
	private boolean banRed;
	
	public FirstBan (/* BanEffect effect,*/ boolean banBlue, boolean banYellow, boolean banGreen, boolean banRed) {
		/*this.effect = effect;*/
		this.banBlue = banBlue;
		this.banYellow = banYellow;
		this.banGreen = banGreen;
		this.banRed = banRed;
	}
	/*public BanEffect getEffect() {
		return this.effect;
	}
	public void setEffect (BanEffect effect) {
		this.effect = effect; }*/
	
	public boolean banBlue () {
		return this.banBlue;
	}
	
	public void banBlue ( boolean banBlue) {
		this.banBlue = banBlue;
	}

	public boolean banYellow () {
		return this.banYellow;
	}
	
	public void banYellow ( boolean banYellow) {
		this.banYellow = banYellow;
	}

	public boolean banGreen () {
		return this.banBlue;
	}
	
	public void banGreen ( boolean banGreen) {
		this.banGreen = banGreen;
	}

	public boolean banRed () {
		return this.banRed;
	}
	
	public void banRed( boolean banRed) {
		this.banRed = banRed;
	}
}

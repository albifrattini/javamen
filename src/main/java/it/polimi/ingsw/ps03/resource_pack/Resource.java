package it.polimi.ingsw.ps03.resource_pack;

public class Resource {

	private Coins coins;
	private Woods woods;
	private Stones stones;
	private Servants servants;
	
	public Resource(Coins coins, Woods woods, Stones stones, Servants servants){
		this.coins = coins;
		this.woods = woods;
		this.stones = stones;
		this.servants = servants;
	}
	
	//METODI GET
	public Coins getCoins(){
		return this.coins;
	}
	public Woods getWoods(){
		return this.woods;
	}
	public Stones getStones(){
		return this.stones;
	}
	public Servants getServants(){
		return this.servants;
	}
	
	public int getCoinsValue(){
		return coins.getValue();
	}
	public int getWoodsValue(){
		return woods.getValue();
	}
	public int getStonesValue(){
		return stones.getValue();
	}
	public int getServantsValue(){
		return servants.getValue();
	}

	
}

package it.polimi.ingsw.ps03.excommunication_pack;

public class Excommunication {

	private int id;
	private final int excommunicationPeriod;
	private final String excommunicationEffect; 
	
	public Excommunication(int id, int excommunicationPeriod, String excommunicationEffect){
		this.id = id;
		this.excommunicationPeriod =  excommunicationPeriod;
		this.excommunicationEffect = excommunicationEffect;
	}

//override toString?

	public int getId(){
		return id;
	}

	public int getExcommunicationPeriod(){
		return excommunicationPeriod;	
	}          

	public String getExcommunicationEffect(){
		return excommunicationEffect;
	}




}

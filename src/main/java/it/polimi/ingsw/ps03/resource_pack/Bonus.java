package it.polimi.ingsw.ps03.resource_pack;

import it.polimi.ingsw.ps03.points_pack.*;

/* La classe bonus è stata aggiunta per poter includere anche i punti militari
 * tra i premi ricevuti nel caso di piazzamento di un familiare in uno spazio
 * azione che conceda, oltre a risorse, dei punti militari.
 */
public class Bonus {

	private Resource resource;
	private MilitaryPoints militaryPoints;
	
	public Bonus (){	
	}
	public Bonus (Resource resource, MilitaryPoints militaryPoints){
		this.resource = resource;
		this.militaryPoints = militaryPoints;
	}
	public Resource getResource(){
		return this.resource;
	}
	public int getMilitaryPoints(){
		return militaryPoints.getValue();
	}
	
	//non aggiungo i metodi set perchè penso non servano in questa classe e 
	//potrebbero creare problemi
}

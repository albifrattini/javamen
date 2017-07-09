package it.polimi.ingsw.ps03.development_card;

import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.effects.*;
import it.polimi.ingsw.ps03.resources.Resources;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class describes the attributes of a Development Card
 * @author Alberto Frattini, Francesco Ehrenheim, Gabriele Ghiringhelli
 *
 */
public class DevelopmentCard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cardId;
	private int cardPeriod;
	private int diceValue;
	private String cardName;
	private TowerColor cardColor;
	private List<Resources> costs;
	private List<Resources> requirements;
	private Effect immediateEffect;
		
	//COSTRUTTORE
	public DevelopmentCard(int mId,int mCardPeriod, int mDiceValue, String mCardName, 
			TowerColor mCardColor){ 
		
		cardId = mId;
		cardName = mCardName;
		cardColor = mCardColor;
		cardPeriod = mCardPeriod;
		costs = new ArrayList<Resources>(2);
		requirements = new ArrayList<Resources>(2);
		immediateEffect = null; 
		diceValue = mDiceValue;
	}
	
	/**
	 * this method prints price, name, color and immediate effect of the Card
	 */
	@Override
	public String toString(){
		String prices = "\n";
		for(int i = 0; i < costs.size(); i++){
			prices = prices + "Costo " + String.valueOf(i+1) + ": " + costs.get(i).toString() + "\n";
			try{
				prices = prices + "Requisito " + String.valueOf(i+1) + ": " + requirements.get(i).toString() + "\n";
			}catch(IndexOutOfBoundsException e){
				prices = prices + "Non c'è nessun requisito per il costo " + String.valueOf(i+1) + "\n";
			}
		}
		if(immediateEffect != null) {
			return "Nome: " + cardName + "\tColore: " + cardColor + prices + 
					immediateEffect.toString() + "\n";
		}
		else {
			  return "Nome: " + cardName + "\tColore: " + cardColor + prices + "Nessun effetto immediato" + "\n" ;
		}
		
	}
	
	//METODI GET
	
	public DevelopmentCard getCard(){
		return this;
	}
	public int getId(){
		return cardId;
	}
	public String getCardName(){
		return cardName;
	}
	public TowerColor getCardColor(){
		return cardColor;
	}
	public int getCardPeriod(){
		return cardPeriod;
	}
	public int getDiceValue(){
		return diceValue;
	}
	public Effect getImmediateEffect(){
		return immediateEffect;
	}
	
	public void setImmediateEffect(Effect immidiateEffect) {
		this.immediateEffect = immidiateEffect; 
	}
	public void setPeriod(int period){
		this.cardPeriod = period;
	}
	public void setColor(TowerColor color){
		this.cardColor = color;
	}
	public List<Resources> getCosts() {
		return costs; 
	}
	public List<Resources> getRequirements(){
		return requirements;
	}
	


	
	
}

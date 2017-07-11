package it.polimi.ingsw.ps03.effects;

import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.resources.Resources;
import it.polimi.ingsw.ps03.room_pack.HarvestingRoom;
import it.polimi.ingsw.ps03.room_pack.ProductionRoom;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
/**
 * this class describes the immediate effect that activates the harvesting or the market room in the billboard of a player
 *
 */
public class HarvestingOrProductionImmediateEffect extends GiveResourcesImmediateEffect {
	

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int diceValue;
    private TowerColor  color;
	
	public HarvestingOrProductionImmediateEffect(int diceValue, TowerColor color, Resources givenResources, String name){
		super(givenResources, name);
		this.diceValue = diceValue;
		this.color = color;
	}
	
	public int getDiceValue(){
		return diceValue;
	}
	public TowerColor getColor(){
		return color;
	}
	/**
	 * this method chose between Harvesting or Production relying on the color of the card
	 * @return
	 */
	public String effectType(){
		if(color == TowerColor.GREEN){
			return "Raccolto";
		}
		else{
			return "Produzione";
		}
	}
/**
 * This method actives the effect Harvesting or the effect Production and add the resources earned to the resources owned by the player
 */
	@Override
	public void applyEffect(Player player){
		if(color == TowerColor.GREEN){
			HarvestingRoom room = new HarvestingRoom(createResourcesForHarvesting());
			player.getResources().add(room.getResources());
		}
		if(color == TowerColor.YELLOW){
			ProductionRoom room = new ProductionRoom(createResourcesForProduction());
			player.getResources().add(room.getResources());
		}
	}
	/**
	 * chiedi ad Albi
	 * @return
	 */
	private Resources createResourcesForHarvesting(){
		Resources resources = new Resources();
		resources.getResource("SERVANTS").add(1);
		resources.getResource("STONES").add(1);
		resources.getResource("WOODS").add(1);
		return resources;
	}
	private Resources createResourcesForProduction(){
		Resources resources = new Resources();
		resources.getResource("COINS").add(1);
		resources.getResource("MILITARYPOINTS").add(2);
		return resources;
	}
	
	
	@Override
	public String toString(){
		return "Nome effetto: " + effectType() + "\tValore azione: " + getDiceValue()
			+ "\nRisorse date: " + getGivenResources();
	}
	
}

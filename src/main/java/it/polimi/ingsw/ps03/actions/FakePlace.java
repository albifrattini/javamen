package it.polimi.ingsw.ps03.actions;

import it.polimi.ingsw.ps03.billboard_pack.Billboard;
import it.polimi.ingsw.ps03.development_card.DevelopmentCard;
import it.polimi.ingsw.ps03.players.Pawn;
import it.polimi.ingsw.ps03.players.Player;
import it.polimi.ingsw.ps03.room_pack.TowerColor;
import it.polimi.ingsw.ps03.room_pack.TowerRoom;
/**
 * This class represents the action Place activated by the immediate effect PlaceImmediateEffect
 * @author Amministratore
 *
 */
public class FakePlace extends Place{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TowerColor color;

	public FakePlace(Billboard billboard, Player player, TowerColor color, int actionValue){
		super("FAKEPLACE", billboard, player);
		this.color = color;
		setPawn(new Pawn(actionValue));
	}

	@Override
	public DevelopmentCard applyAction(){
		getPlayer().getCards().add(((TowerRoom) getRoom()).getPlacedCard());
		getPlayer().getResources().add(((TowerRoom) getRoom()).getResources());
		getPlayer().getResources().sub(getRequiredResources());
		DevelopmentCard temp = ((TowerRoom) getRoom()).getPlacedCard();
		((TowerRoom)getRoom()).setDevelopmentCard(null);
		return temp;	
	}

	
	public TowerColor getTowerColor(){
		return color;
	}
}

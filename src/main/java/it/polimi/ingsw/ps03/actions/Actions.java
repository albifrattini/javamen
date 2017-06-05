package it.polimi.ingsw.ps03.actions;

import java.util.Map;
import java.util.HashMap;

public class Actions {
	
	private Map<String, Action> actions;
	
	public Actions(){
		actions = new HashMap<String, Action>(5);
		actions.put("CHANGETURN", new ChangeTurn());
		actions.put("PLACE", new Place());
		actions.put("CHECKPLAYER", new CheckPlayer());
	}
	
	public Map<String, Action> getActions(){
		return actions;
	}
}

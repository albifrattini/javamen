package it.polimi.ingsw.ps03.actions;

public enum ActionChoices {
	PLACE, CHECKPLAYER, CHECKCARDS;

	public static ActionChoices parseInput(String input){
		return Enum.valueOf(ActionChoices.class, input.toUpperCase());
	}
}

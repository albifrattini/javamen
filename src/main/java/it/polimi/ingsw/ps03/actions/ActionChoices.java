package it.polimi.ingsw.ps03.actions;

public enum ActionChoices {
	PLACE, CHECKPLAYER, CHECKCARDS;

	public static ActionChoices parseInput(Object input){
		System.out.println("Entrato nella classe ActionChoices");
		return Enum.valueOf(ActionChoices.class, input.toString().toUpperCase());
	}
}

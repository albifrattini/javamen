package it.polimi.ingsw.ps03.networking.model;

	public class Outcome {
	
		private final String message;

		private Outcome(String message){
			this.message = message;
		}
		
		@Override
		public String toString() {
			return message;
		}
}

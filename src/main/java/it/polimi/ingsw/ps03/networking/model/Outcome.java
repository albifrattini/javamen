package it.polimi.ingsw.ps03.networking.model;

		private final String message;

		private Outcome(String message){
			this.message = message;
		}
		
		@Override
		public String toString() {
			return message;
		}
}

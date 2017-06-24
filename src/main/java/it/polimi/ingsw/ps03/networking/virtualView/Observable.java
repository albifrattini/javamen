package it.polimi.ingsw.ps03.networking.virtualView;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
	
	private List<Observer<T>> observers = new ArrayList<Observer<T>>();
	
			/*aggiunge alla lista di osservatori i giocatori*/
			public void register(Observer<T> observer){
				synchronized (observers) {
					observers.add(observer);			
		}		
	}
	
			public void deregister(Observer<T> observer){
				synchronized (observers) {
					observers.remove(observer);
		}
	}
	
			protected void notify(T message){
				synchronized (observers) {
					for(Observer<T> observer : observers){
						observer.notify(message);
			}
		}
	}
}

package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Observable<Object> {
	
	private List<Observer<Object>> observers = new ArrayList<Observer<Object>>();
	
			/*aggiunge alla lista di osservatori i giocatori*/
	public void register(Observer<Object> observer){
		synchronized (observers){
		observers.add(observer);			
		}		
	}
	
	public void deregister(Observer<Object> observer){
		synchronized (observers) {
		observers.remove(observer);
		}
	}
	
	protected void notifyObservers(Object read) throws IOException{
		synchronized (observers) {
		for(Observer<Object> observer : observers){
			observer.notify(read);
		}
		}
	}
}

package it.polimi.ingsw.ps03.networking.virtualView;

import java.util.Observable;

public interface Observer<T> {

	void notify(T message);

	void update(Observable o, Object obj);

}

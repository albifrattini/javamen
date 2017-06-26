package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.util.Observable;

public interface Observer<T> {

	void notify(Object read) throws IOException;

	void update(Observable o, Object obj);


}

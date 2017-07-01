package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;
import java.util.Observable;

public interface Observer<Object> {

	void notify(Object read) throws IOException;

	void update(Observable o, Object obj);


}

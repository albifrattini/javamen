package it.polimi.ingsw.ps03.networking.virtualView;

import java.io.IOException;

public interface Observer<T> {

	void notify(Object read) throws IOException;

	void update(Observable<T> o, Object obj);


}

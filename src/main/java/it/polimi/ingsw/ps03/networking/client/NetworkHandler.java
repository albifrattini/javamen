package it.polimi.ingsw.ps03.networking.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
//import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps03.networking.virtualView.Observer;

//con il metodo update invia eventi alla virtual view del server
public class NetworkHandler extends Client implements Observer<String>/*<VCevent>*/ {
	
	public NetworkHandler(String ip, int port) {
		super(ip, port);
	}





	
	public synchronized void update(Observable arg0, Object arg1) {
		
		
	}

	@Override
	public void notify(String message) {
		
	}
	
}

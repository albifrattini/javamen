package it.polimi.ingsw.ps03.server;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args){
		Client client = new Client("127.0.0.1", 1457);
		
		try{
			client.startClient();
		}catch (IOException e){
			System.err.println(e.getMessage());
		}
	}
}

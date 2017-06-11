package it.polimi.ingsw.ps03.server;


public class MainServer {
	
	public static void main(String[] args){
		Server server = new Server(1457);
		server.startServer();
	}
}

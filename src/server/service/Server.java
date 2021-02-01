package server.service;

import server.presentation.MainView;

public abstract class Server {
	protected int portServer;
	
	public Server(int pPortServer) {
		this.portServer= pPortServer;
	}
	
	public abstract void runServer();
	
	public abstract void addView(MainView pMainView);
	
}

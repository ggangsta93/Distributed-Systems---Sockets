package client.service;

import java.io.IOException;

import client.presentation.MainView;

public abstract class Client {
	String ipServer;
	int portServer;
	
	public Client(int pPortServer, String pIpServer) {
		this.ipServer = pIpServer;
		this.portServer= pPortServer;
	}
	
	public abstract void createConnection()throws IOException ;
	public abstract void closeConecction()throws IOException ;
	public abstract void communication();
	
	public abstract void addView(MainView pMainView);

}

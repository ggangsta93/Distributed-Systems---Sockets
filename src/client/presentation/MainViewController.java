package client.presentation;
import java.io.IOException;

import client.service.Client;
import client.service.ClientCreator;
import server.service.Server;
import server.service.ServerCreator;

public class MainViewController {
		
	private MainView mainView;
	
	public MainViewController(MainView pMainView) {
		this.mainView = pMainView;
	}
	
	public boolean start(int pPortServer, String pIpServer, String pProtocol) {
		
		try {
    		Client objClient = ClientCreator.factoryMethod(pPortServer, pIpServer, pProtocol);
			objClient.addView(mainView);
    		objClient.createConnection();
			objClient.communication();		
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return true;
	}
	
	
	
	

}

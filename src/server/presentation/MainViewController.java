package server.presentation;
import java.io.IOException;

import server.service.Server;
import server.service.ServerCreator;

public class MainViewController {
	
	
	private MainView mainView;
	
	public MainViewController(MainView pMainView) {
		this.mainView = pMainView;
	}
	
	public boolean start(int pPortServer, String pProtocol, String pType) {
		
		try {
			Server objServer=ServerCreator.FactoryMethod(pPortServer, pProtocol, pType);
			objServer.addView(mainView);
			objServer.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	

}

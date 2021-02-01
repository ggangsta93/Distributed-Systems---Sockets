package client.service;

public class ClientCreator {
		
	public static Client factoryMethod(int pPortServer, String pIpServer, String pProtocol) {
	
		Client ObjClient = null;
		if(pProtocol.equalsIgnoreCase("tcp")) { 
			ObjClient = new ClientTCP(pPortServer, pIpServer);
		}
		if(pProtocol.equalsIgnoreCase("udp")) { 
			ObjClient = new ClientUDP(pPortServer, pIpServer);
		}
		return ObjClient;
	}

}

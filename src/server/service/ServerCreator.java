package server.service;

import java.io.IOException;

public class ServerCreator {
	
	public static Server FactoryMethod(int pPortServer, String pProtocol, String pType) throws IOException {
		Server ObjServer=null;
		//System.out.println("Parametros de llegada: "+pPortServer+" "+pProtocol+" "+pType);
		
		if(pType.equalsIgnoreCase("secuencial")) {
		
			if(pProtocol.equalsIgnoreCase("tcp")) 
				ObjServer = new SequentialTCP(pPortServer);	
			
			if(pProtocol.equalsIgnoreCase("udp"))
				ObjServer = new SequentialUDP(pPortServer);
		}
		
		if(pType.equalsIgnoreCase("concurrente")) {

			if(pProtocol.equalsIgnoreCase("tcp")) 
				ObjServer = new ConcurrentTCP(pPortServer);	
			
			if(pProtocol.equalsIgnoreCase("udp")) 
				ObjServer = new ConcurrentUDP(pPortServer);	
			
		}
		
		return ObjServer;
	}

}

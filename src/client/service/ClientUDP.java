package client.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import client.presentation.MainView;
import model.InformationManager;
import model.maintenanceInformation;


public class ClientUDP extends Client {

	private DatagramSocket socketUDP = null;
	private MainView mainView;
	
	public ClientUDP(int pPortServer, String pIpServer) {
		super(pPortServer, pIpServer);
	}
	
	public void addView(MainView pMainView) {
		this.mainView = pMainView;
	}
	
	@Override
	public void createConnection() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void closeConecction() {
		socketUDP.close();		
	}

	@Override
	public void communication() {
        byte[] bufferPeticion;
        byte[] bufferRespuesta= new byte[1024];
        
        String JSON = "{}";
        try {
        	
        	
            InetAddress direccionIPServidor = InetAddress.getByName(ipServer);
 
            socketUDP = new DatagramSocket();
 
            
 
            bufferPeticion = JSON.getBytes();
            mainView.writeConsole("Request with " + bufferPeticion.length+" bytes.");
            
            DatagramPacket objMensajeDelCliente = new DatagramPacket(bufferPeticion, bufferPeticion.length, direccionIPServidor, portServer);
 
            socketUDP.send(objMensajeDelCliente);
            
            DatagramPacket objMensajeDeRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
 
            socketUDP.receive(objMensajeDeRespuesta); 
           
            JSON = new String(objMensajeDeRespuesta.getData());
            
            closeConecction(); 
            mainView.writeConsole(JSON+" ");  
            /*
            maintenanceInformation objMaintenanceInformation2 =InformationManager.getCharacteristics(JSON);
            System.out.println("DATE: "+objMaintenanceInformation2.getFechaHora());
            mainView.writeConsole("********Information*********");
            mainView.writeConsole("Date: "+objMaintenanceInformation2.getFechaHora());
            mainView.writeConsole("Architecture: "+objMaintenanceInformation2.getOsArchitecture());
            mainView.writeConsole("O.S Name: "+objMaintenanceInformation2.getOsName());
            mainView.writeConsole("O.S version: "+objMaintenanceInformation2.getOsVersion());
            mainView.writeConsole("Cpu: "+objMaintenanceInformation2.getSunCpuEndian());
            mainView.writeConsole("User Languague: "+objMaintenanceInformation2.getUserLanguage());
            mainView.writeConsole("User Name: "+objMaintenanceInformation2.getUserName());
            mainView.writeConsole("****************************");
             */
            mainView.writeConsole("Closed session.");	
 
        } catch (SocketException ex) {
            System.out.println("Error al crear el socket");
        } catch (UnknownHostException ex) {
            System.out.println("No se encontro al servidor");
        } catch (IOException ex) {
            System.out.println("Error al enviar o recibir los datos");
        }
        
       
        

	}
	
}

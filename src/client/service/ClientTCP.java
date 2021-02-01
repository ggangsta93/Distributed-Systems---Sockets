package client.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import client.presentation.MainView;
import model.InformationManager;
import model.maintenanceInformation;

public class ClientTCP extends Client{
	
	private DataInputStream objInFlow;
    private DataOutputStream objOutFlow;
    private Socket objSocket;
    private MainView mainView;
	
	
	public ClientTCP(int pPortServer, String pIpServer) {
		super(pPortServer, pIpServer);
	}

	public void addView(MainView pMainView) {
		this.mainView = pMainView;
	}
	
	@Override
	public void createConnection()throws IOException {
		try {
			objSocket = new Socket(ipServer, portServer);
			objInFlow = new DataInputStream(objSocket.getInputStream());
			objOutFlow = new DataOutputStream(objSocket.getOutputStream());
			
		 } catch (SocketException ex) {
	            mainView.writeConsole("Error, no existe un servidor	que este escuchando en ese puerto");
	        } catch (UnknownHostException ex) {
	            System.out.println("No se encontro al servidor");
	        } catch (IOException ex) {
	            System.out.println("Error al enviar o recibir los datos");
	        }
			
	}

	@Override
	public void closeConecction()throws IOException {
		objSocket.close();		
	}

	@Override
	public void communication() {
		String JSON = "{}";
        try {
            
        	//mainView.writeConsole("objOutFlow.writeUTF()");
        	objOutFlow.writeUTF(JSON);
        	//mainView.writeConsole("objInFlow.readUTF()");
            JSON = objInFlow.readUTF(); 
            maintenanceInformation objMaintenanceInformation =InformationManager.getCharacteristics(JSON);
            
            mainView.writeConsole("********Information*********");
            mainView.writeConsole("Date: "+objMaintenanceInformation.getFechaHora());
            mainView.writeConsole("Architecture: "+objMaintenanceInformation.getOsArchitecture());
            mainView.writeConsole("O.S Name: "+objMaintenanceInformation.getOsName());
            mainView.writeConsole("O.S version: "+objMaintenanceInformation.getOsVersion());
            mainView.writeConsole("Cpu: "+objMaintenanceInformation.getSunCpuEndian());
            mainView.writeConsole("User Languague: "+objMaintenanceInformation.getUserLanguage());
            mainView.writeConsole("User Name: "+objMaintenanceInformation.getUserName());
            mainView.writeConsole("****************************");




            closeConecction();     
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

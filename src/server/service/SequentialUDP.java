package server.service;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import model.InformationManager;

import server.presentation.MainView;

public class SequentialUDP extends Server {

	private DatagramSocket socketUDP=null; 
	private MainView mainView;
	
	public SequentialUDP(int pPortServer) throws SocketException {
		super(pPortServer);
		 socketUDP = new DatagramSocket(pPortServer);
	}
	 public void addView(MainView pMainView) {
	    	this.mainView = pMainView;
	 }

	@Override
	public void runServer() {
			mainView.writeConsole("Initializing SEQUENTIAL UDP server...");

	        try {      
	        	mainView.writeConsole("Online on port "+portServer+".");      

	            while (true) {
	            	byte[] bufferPeticion = new byte[1024];
	     	        byte[] bufferRespuesta;
	            	DatagramPacket objMensajeDelCliente = new DatagramPacket(bufferPeticion, bufferPeticion.length);
	                 
	                mainView.writeConsole("Listening...");
	                socketUDP.receive(objMensajeDelCliente);
	                mainView.writeConsole("Request received.");
	                mainView.writeConsole("Solving request...");
	                
	                
	                String JSON = new String(objMensajeDelCliente.getData());
	                
	                /*-----------Client technical data --------------*/
	                int portClient = objMensajeDelCliente.getPort();	                
	                InetAddress ipClient = objMensajeDelCliente.getAddress();
	                /*------------------------------------------------*/
	 
                        
	                JSON = InformationManager.getCharacteristics();
	                bufferRespuesta = JSON.getBytes();

	                DatagramPacket objMensajeDeRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, ipClient, portClient);
                        Thread.sleep(2000);
	                socketUDP.send(objMensajeDeRespuesta);
	                mainView.writeConsole("Sended.");	                 
	            }
	 
	        } catch (SocketException ex) {
	            System.out.println("Error al crear el socket");
	        } catch (IOException | InterruptedException ex) {
	            System.out.println("Error de lectura y escritura");
	        }
	 
	}

}


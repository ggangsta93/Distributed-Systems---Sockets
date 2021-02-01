package server.service;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import model.InformationManager;

import model.maintenanceInformation;
import server.presentation.MainView;

public class ConcurrentUDP extends Server {

	private DatagramSocket socketUDP;
	private MainView mainView;

	
	public ConcurrentUDP(int pPortServer) throws SocketException {
		super(pPortServer);
		 socketUDP = new DatagramSocket(pPortServer);
	}

	 public void addView(MainView pMainView) {
	    	this.mainView = pMainView;
	 }
	 
	@Override
	public void runServer() {
		   System.out.println("Initializing CONCURRENT UDP server...");

	        byte[] bufferPeticion = new byte[1024];
	        int i=0;
	        try {      
	        	mainView.writeConsole("Online on port "+portServer+".");      

	            while (true) {
	            	DatagramPacket objMensajeDelCliente = new DatagramPacket(bufferPeticion, bufferPeticion.length);
	                socketUDP.receive(objMensajeDelCliente);
		            ThreadConcurrentUDP thread=new ThreadConcurrentUDP(socketUDP, objMensajeDelCliente, i,mainView);
	                thread.start();
	                i++;
	            }
	 
	        } catch (SocketException ex) {
	            System.out.println("Error al crear el socket");
	        } catch (IOException ex) {
	            System.out.println("Error de lectura y escritura");
	        }
	 
	}

}


class ThreadConcurrentUDP extends Thread {
	
	private DatagramSocket socketUDP = null;
	private int numero;
	private DatagramPacket objMensajeDelCliente;
	private MainView mainView;
	
	public ThreadConcurrentUDP(DatagramSocket pSocketUDP,DatagramPacket pDatagramPacket, int pNumero, MainView pMmainView) {
		this.socketUDP=pSocketUDP;
		this.numero = pNumero;
		this.objMensajeDelCliente = pDatagramPacket;
		this.mainView = pMmainView;
	}
	
	  @Override
	  public void run() {
          byte[] bufferRespuesta;

          try {

            String JSON = new String(objMensajeDelCliente.getData());
            
              /*-----------Client technical data --------------*/
              int portClient = objMensajeDelCliente.getPort();	                
              InetAddress ipClient = objMensajeDelCliente.getAddress();
              /*------------------------------------------------*/
            System.out.println("Solving request #"+numero+" -> "+portClient);              
            System.out.println("Solving request #"+numero+" -> "+ipClient.toString());              

              JSON = InformationManager.getCharacteristics();
              bufferRespuesta = JSON.getBytes();

              DatagramPacket objMensajeDeRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, ipClient, portClient);
               Thread.sleep(1000);
              socketUDP.send(objMensajeDeRespuesta);
        	  mainView.writeConsole("Solved request #"+numero+".");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
          
			  
					
		  
	 }
	  
}

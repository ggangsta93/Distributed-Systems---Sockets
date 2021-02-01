package server.service;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import model.InformationManager;

import server.presentation.MainView;

public class SequentialTCP extends Server{	
	
	private DataInputStream inFlow;
	private DataOutputStream outFlow; 
	private ServerSocket objServerSocket = null;  
	private MainView mainView;
	
    public SequentialTCP(int pPortServer) throws IOException {
		super(pPortServer);
		objServerSocket=new ServerSocket(pPortServer);
	}
    public void addView(MainView pMainView) {
    	this.mainView = pMainView;
    }

	@Override
	public void runServer() {
		  mainView.writeConsole("Initializing SEQUENTIAL TCP server...");

        try {
        	
        	mainView.writeConsole("Online on port "+portServer+"."); 

            while (true) {
            	String JSON = "";

            	mainView.writeConsole("Listening...");
                Socket objSocket = objServerSocket.accept();
                mainView.writeConsole("Solving request...");
                
                inFlow = new DataInputStream(objSocket.getInputStream());
				outFlow = new DataOutputStream(objSocket.getOutputStream());
                
	            //mainView.writeConsole("inFlow.readUTF()");
				JSON = inFlow.readUTF();
				JSON = InformationManager.getCharacteristics();
				ThreadConcurrentTCP.sleep(3000);
			    outFlow.writeUTF(JSON+" ");    
                mainView.writeConsole("Solved request.");                                        
                objSocket.close();
                mainView.writeConsole("Close session."); 
            } 
            
        } 
        catch (IOException | InterruptedException ex) {
            System.out.println("error de conexión");
        }
		
	}
	  
}







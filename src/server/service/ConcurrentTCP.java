package server.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InformationManager;


import server.presentation.MainView;

public class ConcurrentTCP extends Server{

	private ServerSocket objServerSocket = null;  
	private MainView mainView;
	
    public ConcurrentTCP(int pPortServer) throws IOException {
		super(pPortServer);
		objServerSocket=new ServerSocket(pPortServer);
	}

    public void addView(MainView pMainView) {
    	this.mainView = pMainView;
    }
    
	@Override
	public void runServer() {
		mainView.writeConsole("Initializing CONCURRENT TCP server...");
		
        try {
        	
        	int i = 0;
        	mainView.writeConsole("Online on port "+portServer+"."); 
            while (true) {
            	/*Texto.setText("Listening...");*/
                Socket objSocket =objServerSocket.accept();
                ThreadConcurrentTCP thread=new ThreadConcurrentTCP(objSocket, i, mainView);
                thread.start();
                i++;

            } 
            
        } 
        catch (IOException ex) {
            System.out.println("error de conexión");
        }
		
	}

}


class ThreadConcurrentTCP extends Thread {
	
	private Socket socket = null;
	private int numero;
	private MainView mainView;
	
	public ThreadConcurrentTCP(Socket pSocket, int pNumero, MainView pMainView) {
		this.socket=pSocket;
		this.numero = pNumero;
		this.mainView=pMainView;
	}
	
        
	  @Override
	  public void run() {
			  
                                String JSON = "";
				try {
					  	 
					  DataInputStream inFlow = new DataInputStream(socket.getInputStream());
					  DataOutputStream outFlow = new DataOutputStream(socket.getOutputStream());
					  
					  JSON = inFlow.readUTF();
                                       
                                          mainView.writeConsole("Solving request #"+numero+"...");                                        
                                          JSON = InformationManager.getCharacteristics();
			                  mainView.writeConsole("Solved request #"+numero+".");                                        
                                          Thread.sleep(1000);
					  outFlow.writeUTF(JSON+" ");
		 
                                         socket.close();
                                         mainView.writeConsole("Close session for request #"+numero+"."); 
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException ex) {			
                                        Logger.getLogger(ThreadConcurrentTCP.class.getName()).log(Level.SEVERE, null, ex);
                                }			
		  
	 }
	  
}

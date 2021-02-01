package client.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MainView {

	private JFrame frmServer;
	private JTextField textPort;
	private MainViewController mainViewController;
	private String[] title= {"Client","With","TCP","-","Server Ip:","localhost","Server Port:","5000"};

	/*Text Pane*/
	TextArea txtpnConsole = new TextArea();

	

	public MainView() {
		initialize();

	}
	
	public void addController(MainViewController pMainViewController) {
		this.mainViewController = pMainViewController;
	}

	public void setVisible(boolean pValue) {
		this.frmServer.setVisible(pValue);	
	}
	
	public void writeConsole(String pString) {
		txtpnConsole.append(pString+"\n");
	}
	
	private void refreshTitle() {
		frmServer.setTitle(title[0]+" "+title[1]+" "+title[2]+" "+title[3]+" "+title[4]+" "+title[5]+" "+title[6]+" "+title[7]);
	}
	
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle(title[0]+" "+title[1]+" "+title[2]+" "+title[3]+" "+title[4]+" "+title[5]+" "+title[6]+" "+title[7]);
		frmServer.setBounds(100, 100, 500, 411);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		
		/*Label*/
		JLabel lblNewLabel = new JLabel("Server port:");
		lblNewLabel.setBounds(41, 39, 119, 15);
		frmServer.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server ip:");
		lblNewLabel_1.setBounds(41, 39, 119, 75);
		frmServer.getContentPane().add(lblNewLabel_1);
		
		/*Text Field*/
		textPort = new JTextField("5000");
		textPort.setBounds(148, 37, 114, 19);
		frmServer.getContentPane().add(textPort);
		textPort.setColumns(10);
		
		JTextField textIp = new JTextField("localhost");
		textIp.setBounds(148, 67, 114, 19);
		frmServer.getContentPane().add(textIp);
		textIp.setColumns(10);
		
		/*Button Group*/
		ButtonGroup protocolo=new ButtonGroup();
		
		/*Text Pane*/
		txtpnConsole.setEditable(false);
		txtpnConsole.setBounds(22, 190, 450, 176);
		frmServer.getContentPane().add(txtpnConsole);
		
		/*Panels*/
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setToolTipText("");
		panel.setBounds(41, 100, 84, 73);
		frmServer.getContentPane().add(panel);
				

		/*Radio Buttons*/
		JRadioButton rdbtnTcp = new JRadioButton("TCP");
		rdbtnTcp.setSelected(true);
		panel.add(rdbtnTcp);
		protocolo.add(rdbtnTcp);
		
		JRadioButton rdbtnUdp = new JRadioButton("UDP");
		panel.add(rdbtnUdp);
		protocolo.add(rdbtnUdp);
	
		
		/*Button*/
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(145, 145, 117, 25);
		frmServer.getContentPane().add(btnStart);	
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(497, 132, 117, 25);
		frmServer.getContentPane().add(btnStop);
		btnStop.setVisible(false);
		
		/*Events*/
		
		
		rdbtnTcp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnTcp.isSelected())
					title[2]="TCP";
				else
					title[2]="UDP";
				refreshTitle();
			}
		});
		
		
		textPort.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				title[7]=textPort.getText().toString();
				refreshTitle();
			}
		});
		
		textIp.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				title[5]=textIp.getText().toString();
				refreshTitle();
			}
		});
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] parameters=new String[1]; 
				int port = 0;
				
				if(rdbtnTcp.isSelected())
					parameters[0]="tcp";
				else
					parameters[0]="udp";
				
				try {
					port = Integer.parseInt(textPort.getText());
					rdbtnTcp.setEnabled(false);
					rdbtnUdp.setEnabled(false);
					textPort.setEnabled(false);	
					textIp.setEnabled(false);
					btnStart.setEnabled(false);
					eventClient thread=new eventClient(port,parameters[0],textIp.getText(),mainViewController);
	                thread.start();
				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "No se reconoció un número en el puerto.");
				}
					
			}
		});
		

	}	
}




class eventClient extends Thread {
	
	int portServer;
	String protocolClient;
	String ipServer;
	private MainViewController mainViewController;
	
	public eventClient(int pPortServer, String pProtocolClient, String pIpServer, MainViewController pMainViewController) {
		this.portServer=pPortServer;
		this.protocolClient=pProtocolClient;
		this.ipServer=pIpServer;
		this.mainViewController=pMainViewController;
	}
	
	  @Override
	  public void run() {
			mainViewController.start(portServer,ipServer,protocolClient);
		  
	 }
	  
}


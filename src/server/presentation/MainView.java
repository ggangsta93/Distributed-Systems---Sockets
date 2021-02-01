package server.presentation;

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
	private String[] title= {"Sequential","Server","With","TCP","On","Port","5000"};

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
		frmServer.setTitle(title[0]+" "+title[1]+" "+title[2]+" "+title[3]+" "+title[4]+" "+title[5]+" "+title[6]);
	}
	
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle(title[0]+" "+title[1]+" "+title[2]+" "+title[3]+" "+title[4]+" "+title[5]+" "+title[6]);
		frmServer.setBounds(100, 100, 662, 411);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		
		/*Label*/
		JLabel lblNewLabel = new JLabel("Server port:");
		lblNewLabel.setBounds(41, 39, 119, 15);
		frmServer.getContentPane().add(lblNewLabel);
		
		/*Text Field*/
		textPort = new JTextField("5000");
		textPort.setBounds(148, 37, 114, 19);
		frmServer.getContentPane().add(textPort);
		textPort.setColumns(10);
		
		/*Button Group*/
		ButtonGroup protocolo=new ButtonGroup();
		ButtonGroup procesamiento=new ButtonGroup();
		
		/*Text Pane*/
		txtpnConsole.setEditable(false);
		txtpnConsole.setBounds(40, 190, 591, 176);
		frmServer.getContentPane().add(txtpnConsole);
		
		/*Panels*/
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setToolTipText("");
		panel.setBounds(41, 84, 94, 73);
		frmServer.getContentPane().add(panel);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(151, 84, 195, 73);
		frmServer.getContentPane().add(panel_1);

		/*Radio Buttons*/
		JRadioButton rdbtnTcp = new JRadioButton("TCP");
		rdbtnTcp.setSelected(true);
		panel.add(rdbtnTcp);
		protocolo.add(rdbtnTcp);
		
		JRadioButton rdbtnUdp = new JRadioButton("UDP");
		panel.add(rdbtnUdp);
		protocolo.add(rdbtnUdp);
		
		JRadioButton rdbtnSequential = new JRadioButton("Sequential");
		rdbtnSequential.setSelected(true);
		panel_1.add(rdbtnSequential);
		procesamiento.add(rdbtnSequential);
		
		JRadioButton rdbtnConcurrent = new JRadioButton("Concurrent");
		panel_1.add(rdbtnConcurrent);
		procesamiento.add(rdbtnConcurrent);
		
		
		
		/*Button*/
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(370, 132, 117, 25);
		frmServer.getContentPane().add(btnStart);	
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(497, 132, 117, 25);
		frmServer.getContentPane().add(btnStop);
		btnStop.setVisible(false);
		
		/*Events*/
		
		
		rdbtnTcp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnTcp.isSelected())
					title[3]="TCP";
				else
					title[3]="UDP";
				refreshTitle();
			}
		});
		
		rdbtnSequential.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
						if(rdbtnSequential.isSelected())
							title[0]="Sequential";
						else
							title[0]="Concurrent";
						refreshTitle();
			}
		});
		
		textPort.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				title[6]=textPort.getText().toString();
				refreshTitle();
			}
		});
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] parameters=new String[2]; 
				int port = 0;
				
				
				if(rdbtnSequential.isSelected())
					parameters[1]="secuencial";
				else
					parameters[1]="concurrente";
				
				if(rdbtnTcp.isSelected())
					parameters[0]="tcp";
				else
					parameters[0]="udp";
				
				try {
					port = Integer.parseInt(textPort.getText());
					rdbtnTcp.setEnabled(false);
					rdbtnUdp.setEnabled(false);
					rdbtnSequential.setEnabled(false);
					rdbtnConcurrent.setEnabled(false);
					textPort.setEnabled(false);	
					btnStart.setEnabled(false);
					eventServer thread=new eventServer(port,parameters[0],parameters[1],mainViewController);
	                thread.start();
				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "No se reconoció un número en el puerto.");
				}
					
			}
		});
		

	}	
}




class eventServer extends Thread {/*El hilo se creó porque se quedaba pegado el evento del boton start y no imprimía en la consola*/
	
	int port;
	String protocol;
	String type;
	private MainViewController mainViewController;
	
	public eventServer(int pPort, String pProtocol, String pType, MainViewController pMainViewController) {
		this.port=pPort;
		this.protocol=pProtocol;
		this.type=pType;
		this.mainViewController=pMainViewController;
	}
	
	  @Override
	  public void run() {
			mainViewController.start(port,protocol,type);
		  
	 }
	  
}


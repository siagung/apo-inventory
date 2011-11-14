package com.apo.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.apo.gui.components.CustomerPanel;
import com.apo.gui.components.MainPanel;
import com.apo.gui.components.ManagementPanel;
import com.apo.gui.components.YouPanel;
import com.apo.gui.components.navigation.MainNavBar;
import com.apo.gui.components.user.UserPrompt;

public class MainWindow extends JFrame implements WindowListener {
	
	private MainPanel mainPanel;
	
	private boolean loggedIn;
	
	private final int HEIGHT = 768;
	private final int WIDTH = 1024;
	
	/**Card Identifiers**/
	final static String PRODUCT_TAB = "Product Management Panel";
	final static String YOU_TAB = "You Login";
	final static String CUSTOMER_TAB="Customer Management Panel";
	
	
	public MainWindow (String title) {
		super(title);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - WIDTH/2, dim.height/2 - HEIGHT/2);
		mainPanel = new MainPanel();
		this.setSize(WIDTH, HEIGHT);
		this.getContentPane().add(mainPanel);
		addPanels();
		assignListeners();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
	}
	
	public void addPanels() {
		JPanel contentPanel = mainPanel.getContentPane();
		
		//add YouPanel to contentPanel and put an identifier for it
		contentPanel.add(new YouPanel(), YOU_TAB);
		//add ManagementPanel to contentPanel and put an identifier for it
		contentPanel.add(new ManagementPanel(), PRODUCT_TAB);
		//add CustomerPanel to contentPanel and put an identifier for it
		contentPanel.add(new CustomerPanel(), CUSTOMER_TAB);
		
	}
	
	public void assignListeners() {
		MainNavBar tabs = mainPanel.getMainNavBar();
		
		tabs.addYouButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(mainPanel.getContentPane().getLayout());
				cl.show(mainPanel.getContentPane(), YOU_TAB);
				
			}
			
		});
		
		
		tabs.addProductsButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(mainPanel.getContentPane().getLayout());
				cl.show(mainPanel.getContentPane(), PRODUCT_TAB);
				
			}
			
		});
		
		
		tabs.addCustomersButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(mainPanel.getContentPane().getLayout());
				cl.show(mainPanel.getContentPane(), CUSTOMER_TAB);
				
			}
			
		});
	}
	
	public static void main (String[] args) {
		MainWindow mainWindow = new MainWindow("AP Oriental Industrial Trading, Inc.");
		mainWindow.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		switch (choice) {
		case JOptionPane.YES_OPTION:
			this.dispose();
			System.exit(0);
			break;
		default:
			break;
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

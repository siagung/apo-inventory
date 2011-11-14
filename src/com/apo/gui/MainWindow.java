package com.apo.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.apo.gui.components.MainPanel;;

public class MainWindow extends JFrame implements WindowListener {
	
	private MainPanel mainPanel;
	
	private final int HEIGHT = 768;
	private final int WIDTH = 1024;
	
	public MainWindow (String title) {
		super(title);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - WIDTH/2, dim.height/2 - HEIGHT/2);
		mainPanel = new MainPanel();
		this.setSize(WIDTH, HEIGHT);
		this.getContentPane().add(mainPanel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

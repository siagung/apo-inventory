package com.apo.gui.components.lightweight;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


public class DetailsNavigation extends DetailsView {

	private DetailsNavButtons navButtons;
	
	/**
	 * Create the panel.
	 */
	public DetailsNavigation() {
		super();
		JPanel panel = super.getPanel();
		navButtons = new DetailsNavButtons();
		panel.add(navButtons, BorderLayout.NORTH);
	}
	
	public DetailsNavButtons getNavButtons () {
		return navButtons;
	}

}

package com.apo.gui.components.details;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class DetailsPanel extends DetailsNavigation {

	JPanel mainPanel;
	DetailsOptionButtons optionButtons;
	
	/**
	 * Create the panel.
	 */
	public DetailsPanel() {
		super();
		mainPanel = super.getPanel();
		optionButtons = new DetailsOptionButtons();
		mainPanel.add(optionButtons, BorderLayout.SOUTH);
	}
	
	public DetailsOptionButtons getOptionButtons () {
		return optionButtons;
	}

}

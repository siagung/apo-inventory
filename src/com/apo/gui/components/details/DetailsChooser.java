package com.apo.gui.components.details;

import java.awt.BorderLayout;

import javax.swing.JPanel;



public class DetailsChooser extends DetailsNavigation {

	JPanel mainPanel;
	DetailsChooserButtons chooserButtons;
	
	/**
	 * Create the panel.
	 */
	public DetailsChooser() {
		super();
		mainPanel = super.getPanel();
		chooserButtons = new DetailsChooserButtons();
		mainPanel.add(chooserButtons, BorderLayout.SOUTH);
	}

	/**
	 * @return the chooserButtons
	 */
	public DetailsChooserButtons getChooserButtons() {
		return chooserButtons;
	}

}

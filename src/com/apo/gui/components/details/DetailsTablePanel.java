package com.apo.gui.components.details;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class DetailsTablePanel extends JPanel {

	private DetailsTable table;
	private DetailsOptionButtons optionButtons;
	
	/**
	 * Create the panel.
	 */
	public DetailsTablePanel() {
		setLayout(new BorderLayout(0, 0));
		table = new DetailsTable();
		add(table, BorderLayout.CENTER);
		optionButtons = new DetailsOptionButtons();
		optionButtons.getMoreButton().setVisible(false);
		optionButtons.getTrashCanButton().setVisible(false);
		add(optionButtons, BorderLayout.SOUTH);
		
	}

}

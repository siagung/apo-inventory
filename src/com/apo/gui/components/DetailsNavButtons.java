package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;

public class DetailsNavButtons extends JPanel {

	/**
	 * Create the panel.
	 */
	public DetailsNavButtons() {
		
		JLabel lblItemName = new JLabel("Item name");
		lblItemName.setToolTipText("The name of the currently displayed item");
		add(lblItemName);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(btnNext);

	}

}

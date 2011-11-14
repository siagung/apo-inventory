package com.apo.gui.components.details;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class TrashOptionButtons extends JPanel {

	/**
	 * Create the panel.
	 */
	public TrashOptionButtons() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(20, 40));
		add(leftGap);
		
		JButton restoreButton = new JButton("Restore");
		restoreButton.setHorizontalTextPosition(SwingConstants.CENTER);
		restoreButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		add(restoreButton);
		
		Component buttonGap = Box.createHorizontalGlue();
		add(buttonGap);
		
		JButton foreverDeleteButton = new JButton("Delete Forever");
		foreverDeleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		foreverDeleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(foreverDeleteButton);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 40));
		add(rigidArea);

	}

}

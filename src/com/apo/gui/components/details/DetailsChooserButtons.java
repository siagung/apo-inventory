package com.apo.gui.components.details;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class DetailsChooserButtons extends JPanel {

	private JButton chooseButton;
	private JButton cancelButton;
	
	/**
	 * Create the panel.
	 */
	public DetailsChooserButtons() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setPreferredSize(new Dimension(20, 10));
		topGap.setFocusable(false);
		add(topGap);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(leftGap);
		
		JButton chooseButton_1 = new JButton("Choose");
		buttonPanel.add(chooseButton_1);
		chooseButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component centralGap = Box.createHorizontalGlue();
		buttonPanel.add(centralGap);
		
		JButton cancelButton_1 = new JButton("Cancel");
		buttonPanel.add(cancelButton_1);
		cancelButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component rightGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(rightGap);
		
		Component bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bottomGap.setPreferredSize(new Dimension(20, 10));
		add(bottomGap);

	}

	/**
	 * @return the chooseButton
	 */
	protected JButton getChooseButton() {
		return chooseButton;
	}

	/**
	 * @return the cancelButton
	 */
	protected JButton getCancelButton() {
		return cancelButton;
	}
	
	public void addChooseButtonActionListener (ActionListener listener) {
		chooseButton.addActionListener(listener);
	}
	
	public void addCancelButtonActionListener (ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

}

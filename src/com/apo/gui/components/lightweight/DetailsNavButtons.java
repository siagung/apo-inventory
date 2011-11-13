package com.apo.gui.components.lightweight;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class DetailsNavButtons extends JPanel {

	private JButton previousButton;
	private JButton nextButton;
	private JLabel itemNameLabel;
	
	/**
	 * Create the panel.
	 */
	public DetailsNavButtons() {
		setBorder(UIManager.getBorder("SplitPaneDivider.horizontalGradientVariant"));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component nameGap = Box.createRigidArea(new Dimension(20, 20));
		nameGap.setFocusable(false);
		nameGap.setMinimumSize(new Dimension(10, 20));
		nameGap.setPreferredSize(new Dimension(10, 15));
		add(nameGap);
		
		itemNameLabel = new JLabel("Item name");
		itemNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		itemNameLabel.setToolTipText("The name of the currently displayed item");
		add(itemNameLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
		
		previousButton = new JButton("Previous");
		previousButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		previousButton.setPreferredSize(new Dimension(80, this.getHeight()));
		add(previousButton);
		
		nextButton = new JButton("Next");
		nextButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(nextButton);

	}
	
	public void addPreviousButtonActionListener (ActionListener listener) {
		previousButton.addActionListener(listener);
	}
	
	public void addNextButtonActionListener (ActionListener listener) {
		nextButton.addActionListener(listener);
	}
	
	public String getItemNameLabelText () {
		return itemNameLabel.getText();
	}
	
	public void setItemNameLabelText (String labelText) {
		itemNameLabel.setText(labelText);
	}

	/**
	 * @return the previousButton
	 */
	public JButton getPreviousButton() {
		return previousButton;
	}

	/**
	 * @return the nextButton
	 */
	public JButton getNextButton() {
		return nextButton;
	}

	/**
	 * @return the itemNameLabel
	 */
	public JLabel getItemNameLabel() {
		return itemNameLabel;
	}
	
	
	

}

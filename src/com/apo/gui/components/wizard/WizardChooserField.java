package com.apo.gui.components.wizard;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class WizardChooserField extends JPanel {

	private JButton browseButton;
	private InputIcon inputCheck;
	private JLabel fieldLabel;
	private JComboBox fieldComboBox;
	
	private boolean hasInputIndicator;
	private boolean hasBrowseButton;
	
	/**
	 * Create the chooser field for a wizard with the both inputIndicator and browseButton present
	 */
	public WizardChooserField() {
		super();
		hasBrowseButton = true;
		hasInputIndicator = true;
		initComponents();

	}
	
	/**Create a WizardChooserField with the option to omit either the inputIndicator or the browseButton (or both)
	 * 
	 * @param hasInputIndicator True will include the input indicator (that will be a visual cue to the user that an input is valid or invalid)
	 * @param hasBrowseButton True will include the browse button which may help a user choose an entry for a field using a certain browse window
	 */
	public WizardChooserField(boolean hasInputIndicator, boolean hasBrowseButton) {
		super();
		this.hasBrowseButton = hasBrowseButton;
		this.hasInputIndicator = hasInputIndicator;
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		fieldLabel = new JLabel("fieldLabel");
		fieldLabel.setMinimumSize(new Dimension(61, 16));
		fieldLabel.setMaximumSize(new Dimension(100, 16));
		add(fieldLabel);
		
		fieldComboBox = new JComboBox();
		fieldComboBox.setMaximumSize(new Dimension(170, 30));
		add(fieldComboBox);
		
		Component selectorGap = Box.createRigidArea(new Dimension(20, 20));
		add(selectorGap);
		
		if (hasBrowseButton) {
			browseButton = new JButton("");
			browseButton.setIcon(new ImageIcon(WizardChooserField.class.getResource("/com/apo/res/lowquality/moreNoPopUp.png")));
			add(browseButton);
			Component moreGap = Box.createRigidArea(new Dimension(20, 20));
			add(moreGap);
		}
		if (hasInputIndicator) {
			inputCheck = new InputIcon();
			add(inputCheck);
		}
		
		
	}

}

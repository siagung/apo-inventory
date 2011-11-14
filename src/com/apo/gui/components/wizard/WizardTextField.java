package com.apo.gui.components.wizard;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;

public class WizardTextField extends JPanel {

	private InputIcon inputIndicator;
	
	private boolean hasInputIndicator;
	private JTextField textField;
	private JLabel supplementaryLabel;
	private JLabel fieldLabel;
	
	
	/**
	 * Create the text input panel for the wizard with an input indicator
	 * 
	 * @param field the name of the field to be filled up
	 * 
	 */
	public WizardTextField(String field) {
		super();
		hasInputIndicator = true;
		initComponents();
		fieldLabel.setText(field);
		
		
	}
	
	/**Create the text input panel for the wizard
	 * 
	 * @param field the name of the field to be filled up
	 * @param hasInputIndicator Does the field have an input indicator?
	 */
	public WizardTextField(String field, boolean hasInputIndicator) {
		super();
		this.hasInputIndicator = hasInputIndicator;
		initComponents();
		fieldLabel.setText(field);
	}
	
	/**Create the text input panel for the wizard
	 * 
	 * @param field the name of the field to be filled up
	 * @param supplementary the name of a supplementary field (to the right of the text field, eg, for a percentage or a currency)
	 * @param hasInputIndicator Does the field have an input indicator?
	 */
	public WizardTextField(String field, String supplementary, boolean hasInputIndicator) {
		super();
		this.hasInputIndicator = hasInputIndicator;
		initComponents();
		fieldLabel.setText(field);
		supplementaryLabel.setText(supplementary);		
	}
	
	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		fieldLabel = new JLabel("fieldLabel");
		fieldLabel.setMaximumSize(new Dimension(100, 16));
		add(fieldLabel);
		
		Component fieldLabelGap = Box.createRigidArea(new Dimension(20, 20));
		fieldLabelGap.setMinimumSize(new Dimension(10, 20));
		fieldLabelGap.setMaximumSize(new Dimension(10, 20));
		fieldLabelGap.setPreferredSize(new Dimension(10, 20));
		add(fieldLabelGap);
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(170, 30));
		add(textField);
		textField.setColumns(10);
		
		supplementaryLabel = new JLabel("");
		add(supplementaryLabel);
		
		Component textFieldGap = Box.createRigidArea(new Dimension(20, 20));
		add(textFieldGap);
		
		if (hasInputIndicator) {
			inputIndicator = new InputIcon();
			add(inputIndicator);
		}
		
	}

}

package com.apo.gui.components.details;

import java.awt.BorderLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;


public class DetailsComboView extends DetailsView {

	private JPanel comboBoxPanel;
	private JPanel basePanel;
	private JComboBox selector;
	
	/**
	 * Create the panel.
	 */
	public DetailsComboView() {
		super();
		
		basePanel = super.getPanel();
		selector = new JComboBox();
		comboBoxPanel = new JPanel();
		
		basePanel.add(comboBoxPanel, BorderLayout.NORTH);
		comboBoxPanel.setLayout(new BorderLayout(0, 0));
		Component topGap = Box.createRigidArea(new Dimension(20, 10));
		comboBoxPanel.add(topGap, BorderLayout.NORTH);
		comboBoxPanel.add(selector, BorderLayout.CENTER);
		Component bottomGap = Box.createRigidArea(new Dimension(20, 10));
		comboBoxPanel.add(bottomGap, BorderLayout.SOUTH);
		
	}
	
	public JComboBox getComboBox () {
		return this.selector;
	}
	
	public void setComboBoxModel (ComboBoxModel model) {
		this.selector.setModel(model);
	}

}

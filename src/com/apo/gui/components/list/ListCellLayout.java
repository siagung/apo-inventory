package com.apo.gui.components.list;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class ListCellLayout extends JPanel {

	private JLabel pictureLabel;
	private JLabel itemNameLabel;
	private JLabel detailsLabel;
	
	/**
	 * Create the panel.
	 */
	public ListCellLayout() {
		initComponents();
		
	}
	
	private void initComponents() {
		setMinimumSize(new Dimension(240, 50));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(10, 20));
		leftGap.setMinimumSize(new Dimension(10, 20));
		add(leftGap);
		
		pictureLabel = new JLabel("");
		pictureLabel.setIcon(new ImageIcon(ListCellLayout.class.getResource("/com/apo/res/medquality/greenlight.png")));
		add(pictureLabel);
		
		Component middleGap = Box.createRigidArea(new Dimension(20, 20));
		add(middleGap);
		
		JPanel dualCellPanel = new JPanel();
		add(dualCellPanel);
		dualCellPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		itemNameLabel = new JLabel("Item name");
		itemNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		dualCellPanel.add(itemNameLabel);
		
		detailsLabel = new JLabel("Details");
		detailsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		dualCellPanel.add(detailsLabel);
	}
	
	public void setPicture (Icon icon) {
		pictureLabel.setIcon(icon);
	}
	
	public void setItemName (String itemName) {
		itemNameLabel.setText(itemName);
	}
	
	public void setDetailsText (String detailsText) {
		detailsLabel.setText(detailsText);
	}
	
	public void appendDetailsText (String appendedText) {
		detailsLabel.setText(detailsLabel.getText().concat(appendedText));
	}
	
	public void appendDetailsTextWithDash (String appendedText) {
		String append = " - " + appendedText;
		appendDetailsText(append);
	}
	

}

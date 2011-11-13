package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;

public class SearchPanel extends JPanel {
	private JTextField searchField;

	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel searchInputPanel = new JPanel();
		add(searchInputPanel);
		
		searchField = new JTextField();
		searchField.setToolTipText("Type a term here to narrow down the list.");
		searchField.setText("Search...");
		searchInputPanel.add(searchField);
		searchField.setColumns(10);
		
		JButton button = new JButton("");
		button.setToolTipText("Commence the search");
		searchInputPanel.add(button);

	}

}

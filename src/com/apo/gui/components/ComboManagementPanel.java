package com.apo.gui.components;

import javax.swing.JPanel;

import com.apo.gui.components.details.DetailsPanel;
import com.apo.gui.components.search.SearchComboPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class ComboManagementPanel extends JPanel {
	
	private SearchComboPanel searchComboPanel;
	private DetailsPanel detailsPanel;	
	private Component topGap;
	private Component bottomGap;
	private Component leftGap;
	private Component rightGap;
	
	public ComboManagementPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane innerPanel = new JSplitPane();
		add(innerPanel, BorderLayout.CENTER);
		
		searchComboPanel = new SearchComboPanel();
		innerPanel.setLeftComponent(searchComboPanel);
		detailsPanel = new DetailsPanel();
		innerPanel.setRightComponent(detailsPanel);
		
		topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setMinimumSize(new Dimension(20, 5));
		topGap.setPreferredSize(new Dimension(20, 5));
		add(topGap, BorderLayout.NORTH);
		
		bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bottomGap.setMinimumSize(new Dimension(20, 5));
		bottomGap.setPreferredSize(new Dimension(20, 5));
		add(bottomGap, BorderLayout.SOUTH);
		
		leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(5, 20));
		leftGap.setMinimumSize(new Dimension(5, 20));
		add(leftGap, BorderLayout.WEST);
		
		rightGap = Box.createRigidArea(new Dimension(20, 20));
		rightGap.setPreferredSize(new Dimension(5, 20));
		rightGap.setMinimumSize(new Dimension(5, 20));
		add(rightGap, BorderLayout.EAST);
		
	}

}

package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;

import com.apo.gui.components.details.DetailsPanel;
import com.apo.gui.components.search.SearchPanel;

public class ManagementPanel extends JPanel {

	private DetailsPanel detailsPanel;
	private SearchPanel searchPanel;
	private Component topGap;
	private Component bottomGap;
	private Component leftGap;
	private Component rightGap;
	
	/**
	 * Create the panel.
	 */
	public ManagementPanel() {
		setBorder(null);
		setPreferredSize(new Dimension(800, 650));
		setLayout(new BorderLayout(0, 0));
		
		topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setMinimumSize(new Dimension(20, 5));
		topGap.setPreferredSize(new Dimension(20, 5));
		add(topGap, BorderLayout.NORTH);
		
		rightGap = Box.createRigidArea(new Dimension(20, 20));
		rightGap.setPreferredSize(new Dimension(5, 20));
		rightGap.setMinimumSize(new Dimension(5, 20));
		add(rightGap, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		splitPane.setPreferredSize(new Dimension(780, 600));
		detailsPanel = new DetailsPanel();
		detailsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		searchPanel = new SearchPanel();
		searchPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		splitPane.setLeftComponent(searchPanel);
		splitPane.setRightComponent(detailsPanel);
		add(splitPane);
		
		bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bottomGap.setPreferredSize(new Dimension(20, 5));
		bottomGap.setMinimumSize(new Dimension(20, 5));
		add(bottomGap, BorderLayout.SOUTH);
		
		leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(5, 20));
		leftGap.setMinimumSize(new Dimension(5, 20));
		add(leftGap, BorderLayout.WEST);
				
	}

	/**
	 * @return the detailsPanel
	 */
	public DetailsPanel getDetailsPanel() {
		return detailsPanel;
	}

	/**
	 * @return the searchPanel
	 */
	public SearchPanel getSearchPanel() {
		return searchPanel;
	}
	
	
	

}

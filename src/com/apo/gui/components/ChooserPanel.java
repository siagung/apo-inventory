package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;

import com.apo.gui.components.lightweight.DetailsChooser;
import com.apo.gui.components.lightweight.SearchPanel;


public class ChooserPanel extends JPanel {

	private SearchPanel searchPanel;
	private DetailsChooser detailsChooser;
	private Component topGap;
	private Component bottomGap;
	private Component leftGap;
	private Component rightGap;
	
	/**
	 * Create the panel.
	 */
	public ChooserPanel() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		searchPanel = new SearchPanel();
		searchPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		detailsChooser = new DetailsChooser();
		detailsChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		splitPane.setLeftComponent(searchPanel);
		splitPane.setRightComponent(detailsChooser);
		
		add(splitPane);
		
		topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setPreferredSize(new Dimension(20, 5));
		topGap.setMinimumSize(new Dimension(20, 5));
		add(topGap, BorderLayout.NORTH);
		
		bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bottomGap.setPreferredSize(new Dimension(20, 5));
		bottomGap.setMinimumSize(new Dimension(20, 5));
		add(bottomGap, BorderLayout.SOUTH);
		
		leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setMinimumSize(new Dimension(5, 20));
		leftGap.setPreferredSize(new Dimension(5, 20));
		add(leftGap, BorderLayout.WEST);
		
		rightGap = Box.createRigidArea(new Dimension(20, 20));
		rightGap.setMinimumSize(new Dimension(5, 20));
		rightGap.setPreferredSize(new Dimension(5, 20));
		add(rightGap, BorderLayout.EAST);

	}

}

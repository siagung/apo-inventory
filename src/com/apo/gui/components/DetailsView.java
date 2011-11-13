package com.apo.gui.components;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

public class DetailsView extends JPanel {

	JPanel contentArea;
	
	/**
	 * Create the panel.
	 */
	public DetailsView() {
		setPreferredSize(new Dimension(277, 564));
		setLayout(new BorderLayout(0, 0));
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setPreferredSize(new Dimension(20, 10));
		add(topGap, BorderLayout.NORTH);
		
		Component bottomGap = Box.createRigidArea(new Dimension(20, 20));
		add(bottomGap, BorderLayout.SOUTH);
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(10, 20));
		add(leftGap, BorderLayout.WEST);
		
		Component rightGap = Box.createRigidArea(new Dimension(20, 20));
		rightGap.setPreferredSize(new Dimension(10, 20));
		add(rightGap, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(scrollPane, BorderLayout.CENTER);
		
		contentArea = new JPanel();
		scrollPane.setViewportView(contentArea);
		contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));

	}
	
	public JPanel getContentArea () {
		return contentArea;
	}

}

package com.apo.gui.components.details;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class DetailsTable extends JPanel {
	private JTable detailsTable;
	public DetailsTable () {
		super();
		setLayout(new BorderLayout(0, 0));
		
		detailsTable = new JTable();
		add(detailsTable);
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		add(topGap, BorderLayout.NORTH);
		
		Component bottomGap = Box.createRigidArea(new Dimension(20, 20));
		add(bottomGap, BorderLayout.SOUTH);
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		add(leftGap, BorderLayout.WEST);
		
		Component rightGap = Box.createRigidArea(new Dimension(20, 20));
		add(rightGap, BorderLayout.EAST);
	}
}

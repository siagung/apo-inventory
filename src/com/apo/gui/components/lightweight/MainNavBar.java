package com.apo.gui.components.lightweight;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class MainNavBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainNavBar() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel navButtonPanel = new JPanel();
		add(navButtonPanel);
		navButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton youButton = new JButton("You");
		youButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/user.png")));
		youButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		youButton.setHorizontalTextPosition(SwingConstants.CENTER);
		navButtonPanel.add(youButton);
		
		JButton productsButton = new JButton("Products");
		productsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		productsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		productsButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/product.png")));
		navButtonPanel.add(productsButton);
		
		JButton customersButton = new JButton("Customers");
		customersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/customer.png")));
		customersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		customersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		navButtonPanel.add(customersButton);
		
		JButton suppliersButton = new JButton("Suppliers");
		suppliersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/supplier.png")));
		suppliersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		suppliersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		navButtonPanel.add(suppliersButton);
		
		JButton ordersButton = new JButton("Orders");
		ordersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		ordersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		ordersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/orders.png")));
		navButtonPanel.add(ordersButton);
		
		JButton btnReports = new JButton("Reports");
		btnReports.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReports.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReports.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/reports.png")));
		navButtonPanel.add(btnReports);
		
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

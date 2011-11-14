package com.apo.gui.components.navigation;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class MainNavBar extends JPanel {

	private JButton youButton;
	private JButton productsButton;
	private JButton customersButton;
	private JButton suppliersButton;
	private JButton ordersButton;
	private JButton reportsButton;
	
	public void addYouButtonListener (ActionListener listener) {
		youButton.addActionListener(listener);
	}
	
	public void addProductsButtonListener (ActionListener listener) {
		productsButton.addActionListener(listener);
	}
	
	public void addCustomersButtonListener (ActionListener listener) {
		customersButton.addActionListener(listener);
	}
	
	public void addSuppliersButtonListener (ActionListener listener) {
		suppliersButton.addActionListener(listener);
	}
	
	public void addOrdersButtonListener (ActionListener listener) {
		ordersButton.addActionListener(listener);
	}
	
	public void addReportsButtonListener (ActionListener listener) {
		reportsButton.addActionListener(listener);
	}
	
	
	/**
	 * @return the youButton
	 */
	protected JButton getYouButton() {
		return youButton;
	}

	/**
	 * @return the productsButton
	 */
	protected JButton getProductsButton() {
		return productsButton;
	}

	/**
	 * @return the customersButton
	 */
	protected JButton getCustomersButton() {
		return customersButton;
	}

	/**
	 * @return the suppliersButton
	 */
	protected JButton getSuppliersButton() {
		return suppliersButton;
	}

	/**
	 * @return the ordersButton
	 */
	protected JButton getOrdersButton() {
		return ordersButton;
	}

	/**
	 * @return the reportsButton
	 */
	protected JButton getReportsButton() {
		return reportsButton;
	}

	/**
	 * Create the panel.
	 */
	public MainNavBar() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JPanel navButtonPanel = new JPanel();
		add(navButtonPanel);
		navButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		youButton = new JButton("You");
		youButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/user.png")));
		youButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		youButton.setHorizontalTextPosition(SwingConstants.CENTER);
		navButtonPanel.add(youButton);
		
		productsButton = new JButton("Products");
		productsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		productsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		productsButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/product.png")));
		navButtonPanel.add(productsButton);
		
		customersButton = new JButton("Customers");
		customersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/customer.png")));
		customersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		customersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		navButtonPanel.add(customersButton);
		
		suppliersButton = new JButton("Suppliers");
		suppliersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/supplier.png")));
		suppliersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		suppliersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		navButtonPanel.add(suppliersButton);
		
		ordersButton = new JButton("Orders");
		ordersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		ordersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		ordersButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/orders.png")));
		navButtonPanel.add(ordersButton);
		
		reportsButton = new JButton("Reports");
		reportsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		reportsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		reportsButton.setIcon(new ImageIcon(MainNavBar.class.getResource("/com/apo/res/medquality/reports.png")));
		navButtonPanel.add(reportsButton);
		
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

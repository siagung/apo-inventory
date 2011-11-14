package com.apo.gui.components;

import javax.swing.JPanel;

import com.apo.gui.components.navigation.MainNavBar;
import com.apo.gui.components.navigation.NavTitleBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;

public class MainPanel extends JPanel {

	private NavTitleBar titleBar;
	private MainNavBar mainNavBar;
	private JPanel contentFrame;
	
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setPreferredSize(new Dimension(1024, 768));
		setLayout(new BorderLayout(0, 0));
		titleBar = new NavTitleBar();
		mainNavBar = new MainNavBar();
		this.add(titleBar, BorderLayout.NORTH);
		this.add(mainNavBar, BorderLayout.WEST);
		
		JPanel bodyFrame = new JPanel();
		add(bodyFrame, BorderLayout.CENTER);
		bodyFrame.setLayout(new BorderLayout(0, 0));
		
		contentFrame = new JPanel();
		contentFrame.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bodyFrame.add(contentFrame, BorderLayout.CENTER);
		contentFrame.setLayout(new CardLayout(0, 0));
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		bodyFrame.add(topGap, BorderLayout.NORTH);
		
		Component bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bodyFrame.add(bottomGap, BorderLayout.SOUTH);
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		bodyFrame.add(leftGap, BorderLayout.WEST);
		
		Component rightGap = Box.createRigidArea(new Dimension(20, 20));
		bodyFrame.add(rightGap, BorderLayout.EAST);
	}

	/**
	 * @return the titleBar
	 */
	public NavTitleBar getTitleBar() {
		return titleBar;
	}

	/**
	 * @return the mainNavBar
	 */
	public MainNavBar getMainNavBar() {
		return mainNavBar;
	}

	/**
	 * @return the contentFrame
	 */
	public JPanel getContentFrame() {
		return contentFrame;
	}

}

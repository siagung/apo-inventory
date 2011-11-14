package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class LargeButtonTrio extends JPanel {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	
	/**
	 * Create the panel.
	 */
	public LargeButtonTrio() {
		initComponents();
	}
	
	public LargeButtonTrio(String button1Name, String button2Name, String button3Name) {
		initComponents();
		button1.setText(button1Name);
		button2.setText(button2Name);
		button3.setText(button3Name);
	}
	
	public LargeButtonTrio(String button1Name, Icon button1Icon, String button2Name, Icon button2Icon, String button3Name, Icon button3Icon) {
		initComponents();
		setButton1(button1Name, button1Icon);
		setButton2(button2Name, button2Icon);
		setButton3(button3Name, button3Icon);
	}
	
	private void initComponents() {
setLayout(new BorderLayout(0, 0));
		
		JPanel buttons = new JPanel();
		add(buttons);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		Component buttonGap1 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap1.setPreferredSize(new Dimension(5, 20));
		buttonGap1.setMinimumSize(new Dimension(5, 20));
		buttonGap1.setMaximumSize(new Dimension(3464, 20));
		buttons.add(buttonGap1);
		
		button1 = new JButton("New button");
		button1.setIcon(new ImageIcon(LargeButtonTrio.class.getResource("/com/apo/res/goodquality/icon.png")));
		button1.setHorizontalTextPosition(SwingConstants.CENTER);
		button1.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttons.add(button1);
		
		Component buttonGap2 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap2.setMinimumSize(new Dimension(5, 20));
		buttonGap2.setMaximumSize(new Dimension(3464, 20));
		buttonGap2.setPreferredSize(new Dimension(5, 20));
		buttons.add(buttonGap2);
		
		button2 = new JButton("New button");
		button2.setIcon(new ImageIcon(LargeButtonTrio.class.getResource("/com/apo/res/goodquality/icon.png")));
		button2.setHorizontalTextPosition(SwingConstants.CENTER);
		button2.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttons.add(button2);
		
		Component buttonGap3 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap3.setMaximumSize(new Dimension(3464, 20));
		buttonGap3.setMinimumSize(new Dimension(5, 20));
		buttonGap3.setPreferredSize(new Dimension(5, 20));
		buttons.add(buttonGap3);
		
		button3 = new JButton("New button");
		button3.setIcon(new ImageIcon(LargeButtonTrio.class.getResource("/com/apo/res/goodquality/icon.png")));
		button3.setHorizontalTextPosition(SwingConstants.CENTER);
		button3.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttons.add(button3);
		
		Component buttonGap4 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap4.setPreferredSize(new Dimension(5, 20));
		buttonGap4.setMinimumSize(new Dimension(5, 20));
		buttonGap4.setMaximumSize(new Dimension(3464, 20));
		buttons.add(buttonGap4);
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		add(topGap, BorderLayout.NORTH);
		
		Component bottomGap = Box.createRigidArea(new Dimension(485, 20));
		add(bottomGap, BorderLayout.SOUTH);
		
		Component leftGlue = Box.createGlue();
		add(leftGlue, BorderLayout.WEST);
		
		Component rightGlue = Box.createGlue();
		add(rightGlue, BorderLayout.EAST);
	}

	/**
	 * @return the button1
	 */
	public JButton getButton1() {
		return button1;
	}

	/**
	 * @return the button2
	 */
	public JButton getButton2() {
		return button2;
	}

	/**
	 * @return the button3
	 */
	public JButton getButton3() {
		return button3;
	}
	
	public void setButton1 (String buttonName, Icon icon) {
		button1.setText(buttonName);
		button1.setIcon(icon);
	}
	
	public void setButton2 (String buttonName, Icon icon) {
		button2.setText(buttonName);
		button2.setIcon(icon);
	}
	
	public void setButton3 (String buttonName, Icon icon) {
		button3.setText(buttonName);
		button3.setIcon(icon);		
	}
	
	public void addButton1ActionListener (ActionListener listener) {
		button1.addActionListener(listener);
	}
	
	
	public void addButton2ActionListener (ActionListener listener) {
		button2.addActionListener(listener);
	}
	
	public void addButton3ActionListener (ActionListener listener) {
		button3.addActionListener(listener);
	}
	
	
}

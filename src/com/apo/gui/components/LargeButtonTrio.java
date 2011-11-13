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
		setLayout(new BorderLayout(0, 0));
		
		JPanel buttons = new JPanel();
		add(buttons);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		Component buttonGap1 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap1.setPreferredSize(new Dimension(5, 20));
		buttonGap1.setMinimumSize(new Dimension(5, 20));
		buttonGap1.setMaximumSize(new Dimension(5, 20));
		buttons.add(buttonGap1);
		
		button1 = new JButton("New button");
		button1.setIcon(new ImageIcon(LargeButtonTrio.class.getResource("/com/apo/res/goodquality/icon.png")));
		button1.setHorizontalTextPosition(SwingConstants.CENTER);
		button1.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttons.add(button1);
		
		Component buttonGap2 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap2.setMinimumSize(new Dimension(5, 20));
		buttonGap2.setMaximumSize(new Dimension(5, 20));
		buttonGap2.setPreferredSize(new Dimension(5, 20));
		buttons.add(buttonGap2);
		
		button2 = new JButton("New button");
		button2.setIcon(new ImageIcon(LargeButtonTrio.class.getResource("/com/apo/res/goodquality/icon.png")));
		button2.setHorizontalTextPosition(SwingConstants.CENTER);
		button2.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttons.add(button2);
		
		Component buttonGap3 = Box.createRigidArea(new Dimension(20, 20));
		buttonGap3.setMaximumSize(new Dimension(5, 20));
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
		buttonGap4.setMaximumSize(new Dimension(5, 20));
		buttons.add(buttonGap4);
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		add(topGap, BorderLayout.NORTH);
		
		Component bottomGap = Box.createRigidArea(new Dimension(485, 20));
		add(bottomGap, BorderLayout.SOUTH);
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		add(leftGap, BorderLayout.WEST);
		
		Component rightGap = Box.createRigidArea(new Dimension(20, 161));
		add(rightGap, BorderLayout.EAST);

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
	
	/**Set the button's look
	 * 
	 * @param buttonName The name to be set on the button
	 * @param icon The icon to be put on the button
	 * @param button The actual button to be set (use getButton1(), getButton2(), getButton3())
	 */
	public void setButton (String buttonName, Icon icon, JButton button) {
		if (button == button1) {
			button1.setText(buttonName);
			button1.setIcon(icon);
		}
		else if (button == button2) {
			button2.setText(buttonName);
			button2.setIcon(icon);
		}
		else if (button == button3) {
			button3.setText(buttonName);
			button3.setIcon(icon);
		}
	}
	
	/**Add action listeners to the button stated in the parameter button
	 * 
	 * @param listener The listener to be added to a button
	 * @param button The button gotten from getButton1(), getButton2() or getButton3()
	 */
	public void addActionListener (ActionListener listener, JButton button) {
		if (button == button1) {
			button.addActionListener(listener);
		}
		else if (button == button2) {
			button.addActionListener(listener);
		}
		else if (button == button3) {
			button.addActionListener(listener);
		}
	}
	
}

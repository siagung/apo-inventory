package com.apo.gui.components;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Announcements extends JPanel {

	/**
	 * Create the panel.
	 */
	public Announcements() {
		setPreferredSize(new Dimension(275, 540));
		setLayout(new BorderLayout(0, 0));
		
		JPanel titleArea = new JPanel();
		add(titleArea, BorderLayout.NORTH);
		titleArea.setLayout(new BorderLayout(0, 0));
		
		Component topMargin_ta = Box.createRigidArea(new Dimension(275, 3));
		titleArea.add(topMargin_ta, BorderLayout.NORTH);
		
		Component bottomMargin_ta = Box.createRigidArea(new Dimension(275, 3));
		titleArea.add(bottomMargin_ta, BorderLayout.SOUTH);
		
		JLabel titleText = new JLabel("Announcements");
		titleText.setFont(new Font("Segoe UI", Font.BOLD, 13));
		titleArea.add(titleText, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		label.setMinimumSize(new Dimension(23, 20));
		label.setPreferredSize(new Dimension(23, 20));
		label.setIcon(new ImageIcon(Announcements.class.getResource("/com/apo/res/lowestsize/announcements.png")));
		titleArea.add(label, BorderLayout.WEST);
		
		JPanel anncStream = new JPanel();
		add(anncStream, BorderLayout.CENTER);
		anncStream.setLayout(new BorderLayout(0, 0));
		
		//Instantiate JPanel containing the announcement stream here
		
		JScrollPane anncStreamScrollPane = new JScrollPane();
		anncStreamScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//Set viewport view to announcement stream object here
		anncStream.add(anncStreamScrollPane, BorderLayout.CENTER);
		
		JPanel inputArea = new JPanel();
		add(inputArea, BorderLayout.SOUTH);
		inputArea.setLayout(new BorderLayout(0, 0));
		
		JPanel inputTextFrame = new JPanel();
		inputArea.add(inputTextFrame, BorderLayout.CENTER);
		inputTextFrame.setLayout(new BorderLayout(0, 0));
		
		Component topMargin_itf = Box.createRigidArea(new Dimension(275, 3));
		inputTextFrame.add(topMargin_itf, BorderLayout.NORTH);
		
		Component bottomMargin_itf = Box.createRigidArea(new Dimension(275, 3));
		inputTextFrame.add(bottomMargin_itf, BorderLayout.SOUTH);
		
		JTextArea inputText = new JTextArea();
		inputText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputText.setLineWrap(true);
		inputText.getPreferredScrollableViewportSize();
		
		JScrollPane inputScrollPane = new JScrollPane();
		inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inputScrollPane.setViewportView(inputText);
		inputTextFrame.add(inputScrollPane, BorderLayout.CENTER);
		
		Component leftMargin_itf = Box.createRigidArea(new Dimension(275, 3));
		leftMargin_itf.setPreferredSize(new Dimension(3, 76));
		inputTextFrame.add(leftMargin_itf, BorderLayout.WEST);
		
		Component rightMargin_itf = Box.createRigidArea(new Dimension(275, 3));
		rightMargin_itf.setPreferredSize(new Dimension(3, 76));
		inputTextFrame.add(rightMargin_itf, BorderLayout.EAST);
		
		JPanel buttonsOuterPanel = new JPanel();
		inputArea.add(buttonsOuterPanel, BorderLayout.EAST);
		buttonsOuterPanel.setLayout(new BorderLayout(0, 0));
		
		Component topMargin_bop = Box.createRigidArea(new Dimension(275, 3));
		topMargin_bop.setPreferredSize(new Dimension(60, 2));
		buttonsOuterPanel.add(topMargin_bop, BorderLayout.NORTH);
		
		Component bottomMargin_bop = Box.createRigidArea(new Dimension(275, 3));
		bottomMargin_bop.setPreferredSize(new Dimension(60, 2));
		buttonsOuterPanel.add(bottomMargin_bop, BorderLayout.SOUTH);
		
		JPanel buttonsInnerPanel = new JPanel();
		buttonsOuterPanel.add(buttonsInnerPanel, BorderLayout.CENTER);
		buttonsInnerPanel.setLayout(new GridLayout(2, 1));
		
		JButton sendButton = new JButton("Send");
		sendButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonsInnerPanel.add(sendButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonsInnerPanel.add(clearButton);
		
		Component rightMargin_bop = Box.createRigidArea(new Dimension(275, 3));
		rightMargin_bop.setPreferredSize(new Dimension(3, 76));
		buttonsOuterPanel.add(rightMargin_bop, BorderLayout.EAST);

	}

}

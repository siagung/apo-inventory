package com.apo.gui.components.navigation;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Font;

public class NavTitleBar extends JPanel {

	private JButton backButton;
	private JLabel firstTierPlaceLabel;
	private JLabel secondTierPlaceLabel;
	private JLabel thirdTierPlaceLabel;
	private JLabel chevron1;
	private JLabel chevron2;
	
	/**
	 * Create the panel.
	 */
	public NavTitleBar() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel navigationPanel = new JPanel();
		add(navigationPanel);
		navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
		
		Component preBackGap = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(preBackGap);
		
		backButton = new JButton("Back");
		backButton.setIcon(new ImageIcon(NavTitleBar.class.getResource("/com/apo/res/lowquality/back.png")));
		navigationPanel.add(backButton);
		
		Component backGap = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(backGap);
		backGap.setMaximumSize(new Dimension(35, 20));
		backGap.setPreferredSize(new Dimension(35, 20));
		
		firstTierPlaceLabel = new JLabel("New label");
		firstTierPlaceLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		navigationPanel.add(firstTierPlaceLabel);
		firstTierPlaceLabel.setIcon(new ImageIcon(NavTitleBar.class.getResource("/com/apo/res/lowquality/icon.png")));
		
		Component subTreeGap = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(subTreeGap);
		subTreeGap.setPreferredSize(new Dimension(9, 20));
		
		chevron1 = new JLabel(">>");
		chevron1.setVisible(false);
		navigationPanel.add(chevron1);
		
		Component subTreeGap2 = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(subTreeGap2);
		subTreeGap2.setPreferredSize(new Dimension(9, 20));
		
		secondTierPlaceLabel = new JLabel("New label");
		secondTierPlaceLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		secondTierPlaceLabel.setVisible(false);
		navigationPanel.add(secondTierPlaceLabel);
		secondTierPlaceLabel.setIcon(new ImageIcon(NavTitleBar.class.getResource("/com/apo/res/lowquality/icon.png")));
		
		Component subTreeGap3 = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(subTreeGap3);
		subTreeGap3.setPreferredSize(new Dimension(9, 20));
		
		chevron2 = new JLabel(">>");
		chevron2.setVisible(false);
		navigationPanel.add(chevron2);
		
		Component subTreeGap4 = Box.createRigidArea(new Dimension(20, 20));
		navigationPanel.add(subTreeGap4);
		subTreeGap4.setPreferredSize(new Dimension(9, 20));
		
		thirdTierPlaceLabel = new JLabel("New label");
		thirdTierPlaceLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		thirdTierPlaceLabel.setVisible(false);
		navigationPanel.add(thirdTierPlaceLabel);
		thirdTierPlaceLabel.setIcon(new ImageIcon(NavTitleBar.class.getResource("/com/apo/res/lowquality/icon.png")));
		
		Component topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setPreferredSize(new Dimension(20, 15));
		add(topGap, BorderLayout.NORTH);

	}

	/**
	 * @return the backButton
	 */
	public JButton getBackButton() {
		return backButton;
	}

	/**
	 * @return the firstTierPlaceLabel
	 */
	public JLabel getFirstTierPlaceLabel() {
		return firstTierPlaceLabel;
	}

	/**
	 * @return the secondTierPlaceLabel
	 */
	public JLabel getSecondTierPlaceLabel() {
		return secondTierPlaceLabel;
	}

	/**
	 * @return the thirdTierPlaceLabel
	 */
	public JLabel getThirdTierPlaceLabel() {
		return thirdTierPlaceLabel;
	}
	
	/**Displays a specific place label on the nav bar hierarchy
	 * 
	 * @param place Where is the user now?
	 * @param icon What is the icon representation of the user's location?
	 * @param placeLabel Which tier of label should display this information (use getFirstTierPlaceLabel(), getSecondTierPlaceLabel() or getThirdTierPlaceLabel()
	 */
	public void displayPlaceLabel (String place, Icon icon, JLabel placeLabel) {
		if (placeLabel == firstTierPlaceLabel) {
			firstTierPlaceLabel.setText(place);
			firstTierPlaceLabel.setIcon(icon);
		}
		else if (placeLabel == secondTierPlaceLabel) {
			secondTierPlaceLabel.setText(place);
			secondTierPlaceLabel.setIcon(icon);
			chevron1.setVisible(true);
			secondTierPlaceLabel.setVisible(true);
		}
		else if (placeLabel == thirdTierPlaceLabel) {
			thirdTierPlaceLabel.setText(place);
			thirdTierPlaceLabel.setIcon(icon);
			chevron2.setVisible(true);
			thirdTierPlaceLabel.setVisible(true);
		}
	}

}

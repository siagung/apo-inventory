package com.apo.gui.components.user;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;

public class UserPrompt extends JPanel {

	JLabel headerLabel;
	JLabel descriptionLabel;
	
	
	/**
	 * @return the headerLabel
	 */
	public JLabel getHeaderLabel() {
		return headerLabel;
	}

	/**
	 * @return the descriptionLabel
	 */
	public JLabel getDescriptionLabel() {
		return descriptionLabel;
	}

	
	/**
	 * Create the panel.
	 */
	public UserPrompt() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel userIcon = new JLabel("");
		userIcon.setIcon(new ImageIcon(UserPrompt.class.getResource("/com/apo/res/goodquality/user.png")));
		add(userIcon);
		
		JPanel textPanel = new JPanel();
		add(textPanel);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		
		headerLabel = new JLabel("Header");
		headerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		textPanel.add(headerLabel);
		
		descriptionLabel = new JLabel("Description.");
		descriptionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textPanel.add(descriptionLabel);

	}

}

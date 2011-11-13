package com.apo.gui.components.user;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class LoginForm extends JPanel {
	private JTextField passwordField;
	private JTextField userNameField;

	/**
	 * Create the panel.
	 */
	public LoginForm() {
		setLayout(new BorderLayout(0, 0));
		
		Component westGap = Box.createRigidArea(new Dimension(20, 20));
		add(westGap, BorderLayout.WEST);
		
		Component northGap = Box.createRigidArea(new Dimension(20, 20));
		add(northGap, BorderLayout.NORTH);
		
		Component eastGap = Box.createRigidArea(new Dimension(20, 20));
		add(eastGap, BorderLayout.EAST);
		
		Component southGap = Box.createRigidArea(new Dimension(20, 20));
		add(southGap, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel userNamePanel = new JPanel();
		userNamePanel.setMaximumSize(new Dimension(16383, 16383));
		panel.add(userNamePanel);
		userNamePanel.setLayout(new BoxLayout(userNamePanel, BoxLayout.X_AXIS));
		
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		userNamePanel.add(userNameLabel);
		
		userNameField = new JTextField();
		userNameField.setMinimumSize(new Dimension(135, 28));
		userNameField.setPreferredSize(new Dimension(129, 28));
		userNameField.setMaximumSize(new Dimension(2147, 2147));
		userNamePanel.add(userNameField);
		userNameField.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setMaximumSize(new Dimension(16383, 16383));
		panel.add(passwordPanel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		passwordPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordPanel.add(passwordField);
		passwordField.setColumns(10);
		
		Component buttonSeparate = Box.createRigidArea(new Dimension(20, 20));
		panel.add(buttonSeparate);
		
		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(5, 20));
		buttonPanel.add(leftGap);
		
		JButton loginButton = new JButton("Log In");
		buttonPanel.add(loginButton);
		
		Component loginGap = Box.createRigidArea(new Dimension(20, 20));
		loginGap.setPreferredSize(new Dimension(10, 20));
		buttonPanel.add(loginGap);
		
		JButton forgotPasswordButton = new JButton("Forgot Password?");
		buttonPanel.add(forgotPasswordButton);
		
		Component forgotPasswordGap = Box.createRigidArea(new Dimension(20, 20));
		forgotPasswordGap.setPreferredSize(new Dimension(10, 20));
		buttonPanel.add(forgotPasswordGap);
		
		JButton exitButton = new JButton("Exit");
		buttonPanel.add(exitButton);
		
		Component exitGap = Box.createRigidArea(new Dimension(20, 20));
		exitGap.setPreferredSize(new Dimension(5, 20));
		buttonPanel.add(exitGap);

	}

}

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
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
	private JPasswordField passwordField;
	private JTextField userNameField;
	private JButton loginButton;
	private JButton forgotPasswordButton;
	private JButton exitButton;
		
	public void addLoginButtonListener (ActionListener listener) {
		loginButton.addActionListener(listener);
	}
	
	public void addForgotPasswordListener (ActionListener listener) {
		forgotPasswordButton.addActionListener(listener);
	}
	
	public void addExitListener (ActionListener listener) {
		exitButton.addActionListener(listener);
	}
	
	public void setForgotPasswordButtonVisible (boolean visibility) {
		forgotPasswordButton.setVisible(visibility);
	}
	
	public String getUserNameText () {
		return userNameField.getText();
	}
	
	public char[] getPassword () {
		return passwordField.getPassword();
	}
	
	/**
	 * @return the passwordField
	 */
	protected JPasswordField getPasswordField() {
		return passwordField;
	}



	/**
	 * @return the userNameField
	 */
	protected JTextField getUserNameField() {
		return userNameField;
	}



	/**
	 * @return the loginButton
	 */
	protected JButton getLoginButton() {
		return loginButton;
	}



	/**
	 * @return the forgotPasswordButton
	 */
	protected JButton getForgotPasswordButton() {
		return forgotPasswordButton;
	}



	/**
	 * @return the exitButton
	 */
	protected JButton getExitButton() {
		return exitButton;
	}



	/**
	 * Create the panel.
	 */
	public LoginForm() {
		setMaximumSize(new Dimension(32767, 4095));
		setLayout(new BorderLayout(0, 0));
		
		Component westGap = Box.createRigidArea(new Dimension(20, 20));
		add(westGap, BorderLayout.WEST);
		
		Component northGap = Box.createRigidArea(new Dimension(20, 20));
		add(northGap, BorderLayout.NORTH);
		
		Component eastGap = Box.createRigidArea(new Dimension(20, 260));
		add(eastGap, BorderLayout.EAST);
		
		Component southGap = Box.createRigidArea(new Dimension(20, 20));
		add(southGap, BorderLayout.SOUTH);
		
		JPanel loginPanel = new JPanel();
		add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		
		Component topGlue = Box.createGlue();
		topGlue.setMaximumSize(new Dimension(32767, 8191));
		loginPanel.add(topGlue);
		
		JPanel userNamePanel = new JPanel();
		loginPanel.add(userNamePanel);
		userNamePanel.setLayout(new BoxLayout(userNamePanel, BoxLayout.X_AXIS));
		
		Component userNameLeftGap = Box.createRigidArea(new Dimension(20, 20));
		userNamePanel.add(userNameLeftGap);
		
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setMaximumSize(new Dimension(90, 16));
		userNameLabel.setPreferredSize(new Dimension(80, 16));
		userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userNamePanel.add(userNameLabel);
		userNameLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		userNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		Component userLabelFieldGap = Box.createRigidArea(new Dimension(20, 20));
		userNamePanel.add(userLabelFieldGap);
		
		userNameField = new JTextField();
		userNameField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		userNameField.setPreferredSize(new Dimension(167, 28));
		userNameField.setMaximumSize(new Dimension(204, 300));
		userNamePanel.add(userNameField);
		
		Component userNameRightGap = Box.createRigidArea(new Dimension(20, 20));
		userNamePanel.add(userNameRightGap);
		
		Component userNameGap = Box.createRigidArea(new Dimension(20, 20));
		userNameGap.setPreferredSize(new Dimension(20, 10));
		loginPanel.add(userNameGap);
		
		JPanel passwordPanel = new JPanel();
		loginPanel.add(passwordPanel);
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		
		Component passwordLeftGap = Box.createRigidArea(new Dimension(20, 20));
		passwordPanel.add(passwordLeftGap);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setMaximumSize(new Dimension(90, 16));
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setPreferredSize(new Dimension(80, 16));
		passwordLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		passwordPanel.add(passwordLabel);
		passwordLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		Component passwordLabelFieldGap = Box.createRigidArea(new Dimension(20, 20));
		passwordPanel.add(passwordLabelFieldGap);
		
		passwordField = new JPasswordField();
		passwordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		passwordPanel.add(passwordField);
		passwordField.setPreferredSize(new Dimension(167, 28));
		passwordField.setMaximumSize(new Dimension(204, 300));
		
		Component passwordRightGap = Box.createRigidArea(new Dimension(20, 20));
		passwordPanel.add(passwordRightGap);
		
		Component passwordGap = Box.createRigidArea(new Dimension(20, 20));
		loginPanel.add(passwordGap);
		
		JPanel actionButtonPanel = new JPanel();
		loginPanel.add(actionButtonPanel);
		actionButtonPanel.setLayout(new BoxLayout(actionButtonPanel, BoxLayout.X_AXIS));
		
		Component leftGap = Box.createRigidArea(new Dimension(20, 20));
		leftGap.setPreferredSize(new Dimension(5, 20));
		actionButtonPanel.add(leftGap);
		
		loginButton = new JButton("Log In");
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		actionButtonPanel.add(loginButton);
		
		Component logInGap = Box.createRigidArea(new Dimension(20, 20));
		logInGap.setPreferredSize(new Dimension(10, 20));
		actionButtonPanel.add(logInGap);
		
		forgotPasswordButton = new JButton("Forgot Password?");
		forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		actionButtonPanel.add(forgotPasswordButton);
		
		Component forgotPasswordGap = Box.createRigidArea(new Dimension(20, 20));
		forgotPasswordGap.setPreferredSize(new Dimension(10, 20));
		actionButtonPanel.add(forgotPasswordGap);
		
		exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		actionButtonPanel.add(exitButton);
		
		Component exitGap = Box.createRigidArea(new Dimension(20, 20));
		exitGap.setPreferredSize(new Dimension(5, 20));
		actionButtonPanel.add(exitGap);
		
		Component bottomGlue = Box.createGlue();
		bottomGlue.setMaximumSize(new Dimension(32767, 8191));
		loginPanel.add(bottomGlue);

	}

}

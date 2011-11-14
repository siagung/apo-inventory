package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import com.apo.gui.components.user.LoginForm;
import com.apo.gui.components.user.UserPrompt;
import java.awt.Component;

public class YouPanel extends JPanel {

	private UserPrompt userPrompt;
	private LoginForm loginForm;
	
	/**
	 * Create the panel.
	 */
	public YouPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		userPrompt = new UserPrompt();
		userPrompt.setHeaderLabelText("You are not logged in.");
		userPrompt.setDescriptionLabelText("Please put in your user name and password to log in.");
		loginForm = new LoginForm();
		add(userPrompt);
		add(loginForm);
	}

}

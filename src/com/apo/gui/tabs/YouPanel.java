package com.apo.gui.tabs;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import com.apo.gui.components.LargeButtonTrio;
import com.apo.gui.components.user.LoginForm;
import com.apo.gui.components.user.UserPrompt;
import java.awt.Component;
import java.awt.CardLayout;

public class YouPanel extends JPanel {
	
	private boolean admin;

	static final String LOG_IN = "LOG IN";
	static final String MAIN_BUTTONS = "MAIN BUTTONS";
	
	private UserPrompt userPrompt;
	private LoginForm loginForm;
	private JPanel youPanels;
	
	/**
	 * Create the panel.
	 */
	public YouPanel(boolean adminAccess) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		userPrompt = new UserPrompt();
		userPrompt.setHeaderLabelText("You are not logged in.");
		userPrompt.setDescriptionLabelText("Please put in your user name and password to log in.");
		add(userPrompt);
		
		youPanels = new JPanel();
		add(youPanels);
		youPanels.setLayout(new CardLayout(0, 0));
		loginForm = new LoginForm();
		youPanels.add(loginForm, LOG_IN);
		
		this.admin = adminAccess;
		
		/**Load buttons and their names**/
		ImageIcon userManagement = new ImageIcon(YouPanel.class.getResource("com/apo/res/medquality/usermultiple.png"));
		String userMgmtButtonName;
		
		if(adminAccess) {
			userMgmtButtonName = "User Management";
		}
		else {
			userMgmtButtonName = "View Employee List";
		}
		
		ImageIcon announcements = new ImageIcon(YouPanel.class.getResource("com/apo/res/medquality/announcements.png"));
		String announcementsName = "Announcements";
		
		ImageIcon logOut = new ImageIcon(YouPanel.class.getResource("com/apo/res/medquality/logoff.png"));
		String logOutName = "Log Out";
		
		LargeButtonTrio buttons = new LargeButtonTrio(userMgmtButtonName, userManagement, announcementsName, announcements, logOutName, logOut);
		youPanels.add(buttons, MAIN_BUTTONS);
	}
	
	public void setActiveView (String panelConstant) {
		CardLayout cl = (CardLayout)youPanels.getLayout();
		cl.show(youPanels, panelConstant);
	}

}

package com.apo.gui.tabs;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.apo.gui.MainWindow;
import com.apo.gui.components.LargeButtonTrio;
import com.apo.gui.components.user.LoginForm;
import com.apo.gui.components.user.UserPrompt;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YouPanel extends JPanel {
	
	private boolean admin;

	static final String LOG_IN = "LOG IN";
	static final String MAIN_BUTTONS = "MAIN BUTTONS";
	
	private UserPrompt userPrompt;
	private LoginForm loginForm;
	private final JPanel youPanels;
	
	private CardLayout panelLayout;
	
	private LargeButtonTrio largeButtonTrio;
	
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
		panelLayout = (CardLayout)youPanels.getLayout();
		loginForm = new LoginForm();
		youPanels.add(loginForm, LOG_IN);
		loginForm.getUserNameField().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				loginForm.getLoginButton().setEnabled(true);
				loginForm.getForgotPasswordButton().setEnabled(true);
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (arg0.getLength() == 0) {
					loginForm.getLoginButton().setEnabled(false);
					loginForm.getForgotPasswordButton().setEnabled(false);
				}
				
			}
			
		});
		loginForm.addLoginButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
							
				if (loginForm.getUserNameText().equalsIgnoreCase("kevin")) {
					userPrompt.setHeaderLabelText("Welcome, Kevin!");
					userPrompt.setDescriptionLabelText("What would you like to do?");
					panelLayout.show(youPanels, YouPanel.MAIN_BUTTONS);
				}
				else if (loginForm.getUserNameText().equalsIgnoreCase("jerwin")) {
					userPrompt.setHeaderLabelText("Welcome, Jerwin!");
					userPrompt.setDescriptionLabelText("What would you like to do?");
					panelLayout.show(youPanels, YouPanel.MAIN_BUTTONS);
				}
				else if (loginForm.getUserNameText().equalsIgnoreCase("vincent")) {
					userPrompt.setHeaderLabelText("Welcome, Vincent!");
					userPrompt.setDescriptionLabelText("What would you like to do?");
					panelLayout.show(youPanels, YouPanel.MAIN_BUTTONS);
				}
				else if (loginForm.getUserNameText().equalsIgnoreCase("daniel")) {
					userPrompt.setHeaderLabelText("Welcome, Daniel!");
					userPrompt.setDescriptionLabelText("What would you like to do?");
					panelLayout.show(youPanels, YouPanel.MAIN_BUTTONS);
				}
				else {
					userPrompt.setDescriptionLabelText("Sorry, the username and/or password seems to be wrong.");
				}
				loginForm.getUserNameField().setText("");
				loginForm.getPasswordField().setText("");
			}
			
		});
		
		this.admin = adminAccess;
		
		/**Load buttons and their names**/
		String userMgmtButtonName;
		
		if(adminAccess) {
			userMgmtButtonName = "User Management";
		}
		else {
			userMgmtButtonName = "View Employee List";
		}
		
		String announcementsName = "Announcements";
		
		String logOutName = "Log Out";
		
		largeButtonTrio = new LargeButtonTrio(userMgmtButtonName, announcementsName, logOutName);
		largeButtonTrio.getButton3().setIcon(new ImageIcon(YouPanel.class.getResource("/com/apo/res/medquality/logoff.png")));
		largeButtonTrio.getButton1().setIcon(new ImageIcon(YouPanel.class.getResource("/com/apo/res/medquality/usermultiple.png")));
		largeButtonTrio.getButton2().setIcon(new ImageIcon(YouPanel.class.getResource("/com/apo/res/medquality/announcements.png")));
		youPanels.add(largeButtonTrio, MAIN_BUTTONS);
		largeButtonTrio.addButton3ActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelLayout.show(youPanels, YouPanel.LOG_IN);

				userPrompt.setHeaderLabelText("You are not logged in.");
				userPrompt.setDescriptionLabelText("Please put in your user name and password to log in.");
			}
			
		});
		
		largeButtonTrio.addButton1ActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)youPanels.getParent().getLayout();
				cl.show(getParent(), MainWindow.EMPLOYEE_TAB);
			}
			
		});
		
	}
	
	public void setActiveView (String panelConstant) {
		CardLayout cl = (CardLayout)youPanels.getLayout();
		cl.show(youPanels, panelConstant);
	}

}

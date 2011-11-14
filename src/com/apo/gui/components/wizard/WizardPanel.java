package com.apo.gui.components.wizard;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class WizardPanel extends JPanel {

	private JLabel pictureLabel;
	private JButton previousButton;
	private JButton nextButton;
	private JButton cancelButton;
	private JLabel curStepLabel;
	private JLabel totalStepLabel;
	private JLabel stepDescriptionLabel;
	private JPanel contentPanel;
	
	private boolean finishStep;
	
	/**
	 * Create the panel.
	 */
	public WizardPanel() {
		
		initComponents();

	}

	private void initComponents () {
		setLayout(new BorderLayout(0, 0));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(125, 10));
		imagePanel.setMinimumSize(new Dimension(105, 10));
		add(imagePanel, BorderLayout.WEST);
		imagePanel.setLayout(new BorderLayout(0, 0));
		
		pictureLabel = new JLabel("picture");
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imagePanel.add(pictureLabel, BorderLayout.CENTER);
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		add(navigationPanel, BorderLayout.SOUTH);
		navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
		
		previousButton = new JButton("Previous");
		previousButton.setActionCommand("");
		navigationPanel.add(previousButton);
		
		Component buttonGlue = Box.createGlue();
		buttonGlue.setPreferredSize(new Dimension(0, 50));
		buttonGlue.setMinimumSize(new Dimension(0, 120));
		navigationPanel.add(buttonGlue);
		
		nextButton = new JButton("Next");
		navigationPanel.add(nextButton);
		
		cancelButton = new JButton("Cancel");
		navigationPanel.add(cancelButton);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		infoPanel.setBackground(UIManager.getColor("CheckBox.background"));
		add(infoPanel, BorderLayout.NORTH);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		
		Component infoPanelGap = Box.createRigidArea(new Dimension(20, 20));
		infoPanelGap.setMaximumSize(new Dimension(10, 20));
		infoPanelGap.setMinimumSize(new Dimension(10, 20));
		infoPanelGap.setPreferredSize(new Dimension(10, 50));
		infoPanel.add(infoPanelGap);
		
		JLabel stepLabel = new JLabel("Step ");
		stepLabel.setPreferredSize(new Dimension(29, 16));
		stepLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		stepLabel.setMinimumSize(new Dimension(27, 30));
		stepLabel.setMaximumSize(new Dimension(47, 30));
		stepLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		stepLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoPanel.add(stepLabel);
		
		curStepLabel = new JLabel("x");
		curStepLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		infoPanel.add(curStepLabel);
		
		JLabel ofLabel = new JLabel(" of ");
		ofLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		infoPanel.add(ofLabel);
		
		totalStepLabel = new JLabel("y");
		totalStepLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		infoPanel.add(totalStepLabel);
		
		JLabel colonLabel = new JLabel(":");
		colonLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		infoPanel.add(colonLabel);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_4.setMinimumSize(new Dimension(10, 20));
		rigidArea_4.setMaximumSize(new Dimension(10, 20));
		rigidArea_4.setPreferredSize(new Dimension(1, 20));
		infoPanel.add(rigidArea_4);
		
		stepDescriptionLabel = new JLabel("Step description");
		stepDescriptionLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 18));
		infoPanel.add(stepDescriptionLabel);
		
		JPanel contentHolderPanel = new JPanel();
		contentHolderPanel.setBackground(Color.WHITE);
		add(contentHolderPanel, BorderLayout.CENTER);
		contentHolderPanel.setLayout(new BorderLayout(0, 0));
		
		Component topContentGap = Box.createRigidArea(new Dimension(496, 30));
		contentHolderPanel.add(topContentGap, BorderLayout.NORTH);
		
		Component bottomContentGap = Box.createRigidArea(new Dimension(496, 34));
		contentHolderPanel.add(bottomContentGap, BorderLayout.SOUTH);
		
		Component leftContentGap = Box.createRigidArea(new Dimension(46, 337));
		contentHolderPanel.add(leftContentGap, BorderLayout.WEST);
		
		Component rightContentGap = Box.createRigidArea(new Dimension(42, 337));
		contentHolderPanel.add(rightContentGap, BorderLayout.EAST);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentHolderPanel.add(contentPanel, BorderLayout.CENTER);
	}
	
}

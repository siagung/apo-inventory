package com.apo.gui.wizard;

import javax.swing.JComponent;

public class WizardPanel extends JComponent {
	
	private String id;
	
	public WizardPanel(String id) {
		super();
		this.id = id;
	}
	
	String getId () {
		return id;
	}
	
}

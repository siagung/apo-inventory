package com.apo.gui.components.wizard;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.apo.gui.components.details.DetailsOptionButtons;

public class InputIcon extends JLabel {

	public static int COMPLETED_INPUT = 0;
	public static int INCOMPLETE_INPUT = 1;
	
	private ImageIcon complete;
	private ImageIcon incomplete;
	
	/**
	 * Create the panel.
	 */
	public InputIcon() {
		this.complete = new ImageIcon(InputIcon.class.getResource("/com/apo/res/lowquality/greenlight.png"));
		this.incomplete = new ImageIcon(InputIcon.class.getResource("/com/apo/res/lowquality/redlight.png"));
		this.setIcon(incomplete);
	}
	
	public void setCurrentIcon (int iconCode) {
		if (iconCode == COMPLETED_INPUT) {
			this.setIcon(complete);
		}
		else {
			this.setIcon(incomplete);
		}
	}

}

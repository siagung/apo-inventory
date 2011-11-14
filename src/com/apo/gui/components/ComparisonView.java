package com.apo.gui.components;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.apo.gui.components.details.DetailsChooserButtons;
import com.apo.gui.components.details.DetailsComboView;
import com.apo.gui.components.details.DetailsView;
import java.awt.BorderLayout;

public class ComparisonView extends JPanel {

	private DetailsComboView revisions;
	private DetailsView original;
	private DetailsChooserButtons chooserButtons;
	
	/**
	 * Create the panel.
	 */
	public ComparisonView() {
		setLayout(new BorderLayout(0, 0));
		chooserButtons = new DetailsChooserButtons();
		add(chooserButtons, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		revisions = new DetailsComboView();
		splitPane.setLeftComponent(revisions);
		original = new DetailsView();
		splitPane.setRightComponent(original);
	}

}

package com.apo.gui.components.details;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class DetailsOptionButtons extends JPanel {

	JButton addButton;
	JButton editButton;
	JButton deleteButton;
	JButton revertButton;
	JButton trashCanButton;
	JButton moreButton;
	private Component leftGap;
	private Component rightGap;
	private Component addEditGap;
	private Component editDeleteGap;
	private Component deleteRevertGap;
	private Component revertTrashGap;
	private Component trashMoreGap;
	private JPanel buttonPanel;
	private Component topGap;
	private Component bottomGap;
	
	/**
	 * Create the panel.
	 */
	public DetailsOptionButtons() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		topGap = Box.createRigidArea(new Dimension(20, 20));
		topGap.setPreferredSize(new Dimension(20, 10));
		topGap.setFocusable(false);
		add(topGap);
		
		buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		leftGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(leftGap);
		leftGap.setMinimumSize(new Dimension(10, 20));
		leftGap.setMaximumSize(new Dimension(5, 20));
		leftGap.setFocusable(false);
		leftGap.setPreferredSize(new Dimension(2, 0));
		
		addButton = new JButton("Add");
		buttonPanel.add(addButton);
		addButton.setPreferredSize(new Dimension(107, 29));
		addButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		addButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/add.png")));
		
		addEditGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(addEditGap);
		addEditGap.setMinimumSize(new Dimension(2, 0));
		addEditGap.setMaximumSize(new Dimension(5, 20));
		addEditGap.setFocusable(false);
		addEditGap.setPreferredSize(new Dimension(2, 0));
		
		editButton = new JButton("Edit");
		buttonPanel.add(editButton);
		editButton.setPreferredSize(new Dimension(107, 29));
		editButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/edit.png")));
		editButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		editButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		editDeleteGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(editDeleteGap);
		editDeleteGap.setMinimumSize(new Dimension(2, 0));
		editDeleteGap.setMaximumSize(new Dimension(5, 20));
		editDeleteGap.setFocusable(false);
		editDeleteGap.setPreferredSize(new Dimension(2, 0));
		
		deleteButton = new JButton("Delete");
		buttonPanel.add(deleteButton);
		deleteButton.setPreferredSize(new Dimension(107, 29));
		deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/delete.png")));
		
		deleteRevertGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(deleteRevertGap);
		deleteRevertGap.setMinimumSize(new Dimension(2, 0));
		deleteRevertGap.setMaximumSize(new Dimension(5, 20));
		deleteRevertGap.setFocusable(false);
		deleteRevertGap.setPreferredSize(new Dimension(2, 0));
		
		revertButton = new JButton("Revert");
		buttonPanel.add(revertButton);
		revertButton.setPreferredSize(new Dimension(107, 29));
		revertButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/restore.png")));
		revertButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		revertButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		revertTrashGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(revertTrashGap);
		revertTrashGap.setMinimumSize(new Dimension(2, 0));
		revertTrashGap.setMaximumSize(new Dimension(5, 20));
		revertTrashGap.setFocusable(false);
		revertTrashGap.setPreferredSize(new Dimension(2, 0));
		
		trashCanButton = new JButton("Trash Can");
		buttonPanel.add(trashCanButton);
		trashCanButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		trashCanButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/trashcan.png")));
		trashCanButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		trashMoreGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(trashMoreGap);
		trashMoreGap.setMinimumSize(new Dimension(2, 0));
		trashMoreGap.setMaximumSize(new Dimension(5, 20));
		trashMoreGap.setFocusable(false);
		trashMoreGap.setPreferredSize(new Dimension(2, 0));
		
		moreButton = new JButton("More...");
		buttonPanel.add(moreButton);
		moreButton.setPreferredSize(new Dimension(107, 29));
		moreButton.setHorizontalTextPosition(SwingConstants.CENTER);
		moreButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		rightGap = Box.createRigidArea(new Dimension(20, 20));
		buttonPanel.add(rightGap);
		rightGap.setMinimumSize(new Dimension(10, 20));
		rightGap.setFocusable(false);
		rightGap.setPreferredSize(new Dimension(2, 0));
		
		bottomGap = Box.createRigidArea(new Dimension(20, 20));
		bottomGap.setPreferredSize(new Dimension(20, 10));
		bottomGap.setFocusable(false);
		add(bottomGap);

	}

	/**Get the Add Button
	 * @return the addButton
	 */
	public JButton getAddButton() {
		return addButton;
	}

	/**Get the Edit Button
	 * @return the editButton
	 */
	public JButton getEditButton() {
		return editButton;
	}

	/**Get the Delete Button
	 * @return the deleteButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}

	/**Get the Revert Button
	 * @return the revertButton
	 */
	public JButton getRevertButton() {
		return revertButton;
	}

	/**Get the Trash Can Button
	 * @return the trashCanButton
	 */
	public JButton getTrashCanButton() {
		return trashCanButton;
	}

	/**Get the More Button
	 * @return the moreButton
	 */
	public JButton getMoreButton() {
		return moreButton;
	}

}

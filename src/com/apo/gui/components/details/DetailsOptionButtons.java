package com.apo.gui.components.details;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;

public class DetailsOptionButtons extends JPanel {

	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton revertButton;
	private JButton trashCanButton;
	private JButton moreButton;
	
	private JPopupMenu popupMenu;
	
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
		moreButton.setIcon(new ImageIcon(DetailsOptionButtons.class.getResource("/com/apo/res/medquality/more.png")));
		buttonPanel.add(moreButton);
		moreButton.setPreferredSize(new Dimension(107, 29));
		moreButton.setHorizontalTextPosition(SwingConstants.CENTER);
		moreButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		moreButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				popupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
				
			}
			
		});
		
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
	protected JButton getAddButton() {
		return addButton;
	}
	
	public void addAddButtonListener (ActionListener listener) {
		addButton.addActionListener(listener);
	}

	/**Get the Edit Button
	 * @return the editButton
	 */
	protected JButton getEditButton() {
		return editButton;
	}
	
	public void addEditButtonListener (ActionListener listener) {
		editButton.addActionListener(listener);
	}

	/**Get the Delete Button
	 * @return the deleteButton
	 */
	protected JButton getDeleteButton() {
		return deleteButton;
	}
	
	public void addDeleteButtonListener (ActionListener listener) {
		deleteButton.addActionListener (listener);
	}

	/**Get the Revert Button
	 * @return the revertButton
	 */
	protected JButton getRevertButton() {
		return revertButton;
	}
	
	public void addRevertButtonListener(ActionListener listener) {
		revertButton.addActionListener(listener);
	}

	/**Get the Trash Can Button
	 * @return the trashCanButton
	 */
	protected JButton getTrashCanButton() {
		return trashCanButton;
	}
	
	public void addTrashCanButtonListener(ActionListener listener) {
		trashCanButton.addActionListener(listener);
	}

	/**Get the More Button
	 * @return the moreButton
	 */
	protected JButton getMoreButton() {
		return moreButton;
	}
	
	public void addMoreButtonMenuItem(JMenuItem menuItem) {
		popupMenu.add(menuItem);
	}
	
	public void addMoreButtonMenuItem(Action menuAction) {
		popupMenu.add(menuAction);
	}
	
	public void addMoreButtonMenuItem(String menuItemName, Icon icon, ActionListener listener) {
		JMenuItem newMenuItem;
		
		if (icon != null) {
			newMenuItem = new JMenuItem(menuItemName, icon);
		}
		else {
			newMenuItem = new JMenuItem(menuItemName);
		}
		newMenuItem.addActionListener(listener);
		popupMenu.add(newMenuItem);
	}

}

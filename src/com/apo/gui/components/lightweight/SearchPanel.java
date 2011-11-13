package com.apo.gui.components.lightweight;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ListModel;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;

public class SearchPanel extends JPanel {
	private JTextField searchField;
	private JButton searchButton;
	JList resultList;
	JLabel itemCountLabel;

	/**
	 * Creates the search panel.
	 */
	public SearchPanel() {
		initComponents();
		searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchField.setText("");				
			}
			
		});
	}
	
	public JTextField getSearchField() {
		return searchField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButtonListener(ActionListener searchButtonListener) {
		this.searchButton.addActionListener(searchButtonListener);
	}

	public JList getResultList() {
		return resultList;
	}

	public void setResultListModel(ListModel listModel) {
		this.resultList.setModel(listModel);
	}

	public JLabel getItemCountLabel() {
		return itemCountLabel;
	}

	public void setItemCountLabelText(String countText) {
		this.itemCountLabel.setText(countText);
	}

	/**Initializes the components
	 * 
	 */
	private void initComponents () {
		setPreferredSize(new Dimension(277, 564));
		setMinimumSize(new Dimension(230, 277));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 10));
		add(rigidArea);
		
		JPanel searchInputPanel = new JPanel();
		searchInputPanel.setMaximumSize(new Dimension(32767, 682));
		searchInputPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		searchInputPanel.setBorder(null);
		searchInputPanel.setLayout(new BoxLayout(searchInputPanel, BoxLayout.X_AXIS));
		
		Component preFieldGap = Box.createHorizontalStrut(20);
		preFieldGap.setPreferredSize(new Dimension(5, 0));
		searchInputPanel.add(preFieldGap);
		
		searchField = new JTextField();
		searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
		searchInputPanel.add(searchField);
		searchField.setToolTipText("Type a term here to narrow down the list.");
		searchField.setText("Search...");
		searchField.setColumns(12);
		
		Component fieldButtonGap = Box.createHorizontalGlue();
		fieldButtonGap.setPreferredSize(new Dimension(5, 0));
		fieldButtonGap.setMinimumSize(new Dimension(5, 0));
		searchInputPanel.add(fieldButtonGap);
		
		searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		searchButton.setToolTipText("Filters the list below.");
		searchButton.setIcon(new ImageIcon(SearchPanel.class.getResource("/com/apo/res/lowestsize/Search-icon.png")));
		searchInputPanel.add(searchButton);
		add(searchInputPanel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		searchInputPanel.add(horizontalStrut);
		
		Component inputOutputGap = Box.createRigidArea(new Dimension(20, 20));
		inputOutputGap.setPreferredSize(new Dimension(20, 15));
		add(inputOutputGap);
		
		JPanel searchOutputPanel = new JPanel();
		searchOutputPanel.setLayout(new BoxLayout(searchOutputPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		searchOutputPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component listLeftGap = Box.createRigidArea(new Dimension(20, 20));
		listLeftGap.setPreferredSize(new Dimension(10, 20));
		panel.add(listLeftGap);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(85, 160));
		scrollPane.setMinimumSize(new Dimension(85, 160));
		scrollPane.setToolTipText("Click on an item and see its details!");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		resultList = new JList();
		resultList.setVisibleRowCount(20);
		resultList.setMaximumSize(new Dimension(32767, 32767));
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(resultList);
		
		Component rightListGap = Box.createRigidArea(new Dimension(20, 20));
		rightListGap.setPreferredSize(new Dimension(10, 20));
		panel.add(rightListGap);
		
		Component listTotalGap = Box.createRigidArea(new Dimension(20, 20));
		listTotalGap.setPreferredSize(new Dimension(20, 15));
		searchOutputPanel.add(listTotalGap);
		
		
		itemCountLabel = new JLabel("total.");
		itemCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchOutputPanel.add(itemCountLabel);
		add(searchOutputPanel);
		
		Component endGap = Box.createRigidArea(new Dimension(20, 20));
		endGap.setPreferredSize(new Dimension(20, 15));
		searchOutputPanel.add(endGap);
	}

}

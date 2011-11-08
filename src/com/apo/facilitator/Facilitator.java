package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

public abstract class Facilitator {
	protected RevisableDBOperator operator;
	
	/**Create a new Facilitator object by giving it a RevisableDBOperator object
	 * 
	 * @param operator A revisable DB operator instance connected to an SQL server
	 */
	public Facilitator (RevisableDBOperator operator) {
		this.operator = operator;
	}
	
	public RevisableDBOperator getOperator () {
		return operator;
	}
	
	public void assign (JLabel[] labels, Object[] content) {
		for (int ctr = 0; ctr < labels.length; ctr++) {
			labels[ctr].setText(content[ctr].toString());
		}
	}
	
	public void assign (JTextField[] textFields, Object[] content) {
		for (int ctr = 0; ctr < textFields.length; ctr++) {
			textFields[ctr].setText(content[ctr].toString());
		}
	}
	
	public abstract ListModel getListModel (String filter);
	public abstract TableModel getTableModel (String filter);
	public abstract ComboBoxModel getComboBoxModel (String filter);
	
	
}

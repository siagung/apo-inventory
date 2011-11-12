package com.apo.facilitator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.debug.Log;
import com.apo.operator.RevisableDBOperator;

public abstract class Facilitator {
	protected RevisableDBOperator operator;
	protected ArrayList<String> columns;
	protected String mainTable;
	
	/**Create a new Facilitator object by giving it a RevisableDBOperator object
	 * 
	 * @param operator A revisable DB operator instance connected to an SQL server
	 */
	public Facilitator (RevisableDBOperator operator) {
		this.operator = operator;
	}
	
	public Facilitator (RevisableDBOperator operator, String tableName) {
		this.operator = operator;
		this.mainTable = tableName;
		columns = this.getTableColumns(tableName);
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
	
	protected ArrayList<String> getTableColumns (String tableName) {
		ArrayList<String> columns = new ArrayList<String>();
		try {
			ResultSet genericQuery = operator.query(false, null, tableName, null, null, null, null);
			int columnCount = genericQuery.getMetaData().getColumnCount();
			for (int ctr = 0; ctr < columnCount; ctr++) {
				columns.add(genericQuery.getMetaData().getColumnName(ctr));
			}
		}
		catch(SQLException ex) {
			Log.errorMsg(this, "Potentially faulty connection to SQL server is present.");
			ex.printStackTrace();
		}
		return columns;
	}
	
	public String getMainTableName () {
		return this.mainTable;
	}
	
	public void setMainTableName (String tableName) {
		this.mainTable = tableName;
	}
	
	public abstract ListModel getListModel (String filter);
	public abstract TableModel getTableModel (String filter);
	public abstract ComboBoxModel getComboBoxModel (String filter);
	
	
}

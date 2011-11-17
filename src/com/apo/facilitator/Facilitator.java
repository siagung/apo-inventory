package com.apo.facilitator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.debug.Log;
import com.apo.operator.RevisableDBOperator;

public abstract class Facilitator <Value> {
	/**The RevisableDBOperator to be passed into the Facilitator**/
	protected RevisableDBOperator operator;
	
	/**The data to be manipulated during data operations**/
	protected HashMap<String, Value> data;
	
	/**Create a new Facilitator object by giving it a RevisableDBOperator object
	 * 
	 * @param operator A revisable DB operator instance connected to an SQL server
	 */
	public Facilitator (RevisableDBOperator operator) {
		this.operator = operator;
		this.data = new HashMap<String, Value>();
	}
	
	/**Get the RevisableDBOperator object that is attached**/
	public RevisableDBOperator getOperator () {
		return operator;
	}
	
	/**Assign content to labels; match each label index to each content index
	 * 
	 * @param labels The JLabel objects to be assigned content
	 * @param content Objects that will be extracted of their String values via toString()
	 */
	public void assign (JLabel[] labels, Value[] content) {
		for (int ctr = 0; ctr < labels.length; ctr++) {
			labels[ctr].setText(content[ctr].toString());
		}
	}
	
	/**Assign content to text fields; match each text field index to each content index
	 * 
	 * @param textFields The JTextField objects to be assigned with content
	 * @param content Objects that will be extract of their String values via toString()
	 */
	public void assign (JTextField[] textFields, Value[] content) {
		for (int ctr = 0; ctr < textFields.length; ctr++) {
			textFields[ctr].setText(content[ctr].toString());
		}
	}
	
	/**Get the data HashMap object that contains the data
	 * 
	 * @return data map containing mappings from each column to a value
	 */
	public HashMap<String, Value> getData () {
		return data;
	}
	
	/**Convenience method used to map columns to values
	 * 
	 * @param key The unique index that will identify a value
	 * @param value The value matching an index (that corresponds to a column)
	 * @return The value that was mapped to a key
	 */
	public Value put (String key, Value value) {
		return data.put(key, value);
	}
	
	/**Get the name of the main table**/
	public abstract String getMainTableName ();
	
	/**Get the ListModel for JList based on a filter
	 * 
	 * @param filter search string used to filter the ListModel
	 * @return The ListModel that corresponds to a certain ResultSet
	 */
	public abstract ListModel getListModel (String filter);
	
	/**Get the TableModel for JTable based on a filter and columns
	 * 
	 * @param filter search string used to filter the TableModel
	 * @param columns columns that are required by the TableModel
	 * @return The TableModel that corresponds to a certain ResultSet
	 */
	public abstract TableModel getTableModel (String filter, String[] columns);
	
	/**Get the ComboBoxModel for a JComboBox based on a filter
	 * 
	 * @param filter search string used to filter the ComboBoxModel
	 * @return The ComboBoxModel that corresponds to a certain ResultSet
	 */
	public abstract ComboBoxModel getComboBoxModel (String filter);
	
	/**Must contain table dependent implementation of a new item
	 * 
	 * @param data The map of data to be placed for a new item in the table
	 * @return The new item id number
	 */
	public abstract int newItem (HashMap<String, Value> data);
	
	/**Must contain table dependent implementation of a new revision
	 * 
	 * @param id The id that will be given a new revision
	 * @param data The data corresponding to the new revision
	 * @return The new revision id number
	 */
	public abstract int newRevision (int id, HashMap<String, Value> data);
	
	/**Must contain table dependent implementation of delete marking
	 * 
	 * @param id The id of the item to be marked for deletion
	 * 
	 */
	public abstract void markDelete(int id);
	
	/**Must contain table dependent implementation of permanent deletion
	 * 
	 * @param id The id of the item to be marked for deletion
	 */
	public abstract void permanentlyDelete(int id);
	
	/**Must contain table dependent implementation of item reversion
	 * 
	 * @param id The id of the item to be reverted
	 * @param newHeadRevision the new head id that will be marked
	 * 
	 */
	public abstract void revertItem(int id, int newHeadRevision);
	
	/**Must contain table dependent implementation of item restoration (unmark for deletion)
	 * 
	 * @param id The id of the item to be restored
	 */
	public abstract void restoreItem(int id);
	
	/**Must contain table dependent implementation of deletion of previous revisions
	 * 
	 * @param id The id of the item whose previous revisions will be deleted (leaving only the head revision)
	 */
	public abstract void deletePreviousRevisions(int id);
	
}

package com.apo.facilitator.table;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.debug.Log;
import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;
import com.apo.operator.exception.NoIDExistsException;

public class AnnouncementFacilitator extends Facilitator {

	public static final String TABLE_NAME = "announcement";
	
	public static enum Types {
		CUSTOM(1),
		ADDED(2),
		EDITED(3),
		REVERTED(4),
		DELETED(5);
		
		private final int columnIndex;
		Types (int columnIndex) {
			this.columnIndex = columnIndex;
		}
		
		public int getColumnIndex () {
			return columnIndex;
		}
		
	}
	
	public static enum Columns {
		ANNOUNCE_ID("announce_id", 0, "Announcement ID"),
		ANNOUNCE_TYPE_ID("announce_type_id", 1, "Announcement Type"),
		MESSAGE("message", 2, "Message"),
		EMPLOYEE_ID("employee_id", 3, "Employee ID"),
		ANNOUNCE_DATE("announce_date", 4, "Announcement Date");
		
		private final String columnName;
		private final int columnIndex;
		private final String normalName;
		
		Columns(String columnName, int columnIndex, String normalName) {
			this.columnIndex = columnIndex;
			this.columnName = columnName;
			this.normalName = normalName;
		}
		
		public String getColumnName () {
			return this.columnName;
		}
		
		public int getColumnIndex () {
			return this.columnIndex;
		}
		
		public String getNormalName () {
			return this.normalName;
		}
		
	}
	
	public AnnouncementFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}

	/**Posts a new announcement to the Announcements module.
	 * 
	 * @param data HashMap of data corresponding to most of the columns (except for id, although even if this does exist, id is removed.)
	 * @throws SQLException the SQL Server may be dysfunctional
	 * @throws NOIDExistsException the ID column specified doesn't exist (SHOULD NOT HAPPEN)
	 * @return The new announcement id; -1 if it didn't work
	 */
	@Override
	public int newItem(HashMap data) {
		int nextId = -1;
		
		try {
			nextId = operator.getMaxId(TABLE_NAME, Columns.ANNOUNCE_ID.getColumnName()) + 1;
			if (data.containsKey(Columns.ANNOUNCE_ID)) {
				data.remove(Columns.ANNOUNCE_ID);
			}
			data.put(Columns.ANNOUNCE_ID, nextId);
			this.operator.insert(TABLE_NAME, data);
		} catch (SQLException e) {
			Log.debugMsg(this, "New announcement cannot be added.");
			e.printStackTrace();
		} catch (NoIDExistsException e) {
			Log.debugMsg(this, "This ID column does not exist.");
			e.printStackTrace();
		}
		return nextId;
	}

	/**This is not available for Announcements
	 * 
	 * @param id The id parameter
	 * @param data The parameter for data
	 * @return new Revision Number
	 * 
	 */
	@Deprecated
	@Override
	public int newRevision(int id, HashMap data) {
		Log.debugMsg(this, "This is not available.");
		
		return -1;
	}

	/**This is not available for Announcements
	 * 
	 * @param id The id parameter
	 */
	@Deprecated
	@Override
	public void markDelete(int id) {
		Log.debugMsg(this, "This is not available");
		
		
	}

	/**Permanently deletes an announcement from the database
	 * 
	 * @param id The id parameter
	 */
	@Override
	public void permanentlyDelete(int id) {
		Log.debugMsg(this, "Commencing deletion...");
		try {
			this.operator.delete(TABLE_NAME, Columns.ANNOUNCE_ID.getColumnName() + " = " + id);
		} catch (SQLException e) {
			Log.debugMsg(this, "Deletion failed.");
			e.printStackTrace();
		}
		Log.debugMsg(this, "Deletion success!");
	}

	/**This is not available for Announcements
	 * 
	 * @param id The id parameter
	 * @param newHeadRevision The new head revision id number
	 */
	@Deprecated
	@Override
	public void revertItem(int id, int newHeadRevision) {
		Log.debugMsg(this, "This is not available");
		return;
	}

	/**This is not available for Announcements
	 * 
	 * @param id The id parameter
	 * 
	 */
	@Deprecated
	@Override
	public void restoreItem(int id) {
		return;
		
	}

	/**This is not available for Announcements
	 * 
	 */
	@Deprecated
	@Override
	public void deletePreviousRevisions(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ListModel getListModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**This is not available for Announcements
	 * 
	 */
	@Deprecated
	@Override
	public TableModel getTableModel(String filter, String[] columns) {
		// TODO Auto-generated method stub
		return null;
	}

	/**This is not available for Announcements
	 * 
	 */
	@Deprecated
	@Override
	public ComboBoxModel getComboBoxModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**Get a combo box model for Announcement Types
	 * 
	 */
	public ComboBoxModel getComboBoxModel() {
		//announcement types
		return null;
	}

	@Override
	public String getMainTableName() {
		return AnnouncementFacilitator.TABLE_NAME;
	}

}

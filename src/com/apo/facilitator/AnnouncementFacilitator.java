package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

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
		ANNOUNCE_ID("announce_id", 0),
		ANNOUNCE_TYPE_ID("announce_type_id", 1),
		MESSAGE("message", 2),
		EMPLOYEE_ID("employee_id", 3),
		ANNOUNCE_DATE("announce_date", 4);
		
		private final String columnName;
		private final int columnIndex;
		
		Columns(String columnName, int columnIndex) {
			this.columnIndex = columnIndex;
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return this.columnName;
		}
		
		public int getColumnIndex () {
			return this.columnIndex;
		}
	}
	
	public AnnouncementFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ListModel getListModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableModel getTableModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComboBoxModel getComboBoxModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMainTableName() {
		return AnnouncementFacilitator.TABLE_NAME;
	}

}

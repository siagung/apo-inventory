package com.apo.facilitator.table;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class EmployeeContactFacilitator extends Facilitator {

	public static final String TABLE_NAME = "employee_contact";
	
	public static enum Columns {
		EMPLOYEE_ID("employee_id", 0, "Employee ID"),
		REVISION_ID("revision_id", 1, "Revision ID"),
		KIND("kind", 2, "Kind"),
		DETAIL("detail", 3, "Detail");
		
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
	
	public EmployeeContactFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMainTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListModel getListModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableModel getTableModel(String filter, String[] columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComboBoxModel getComboBoxModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int newItem(HashMap data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int newRevision(int id, HashMap data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markDelete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void permanentlyDelete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revertItem(int id, int newHeadRevision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restoreItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePreviousRevisions(int id) {
		// TODO Auto-generated method stub
		
	}

}

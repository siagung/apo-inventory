package com.apo.facilitator.table;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class EmployeeFacilitator extends Facilitator {

	public static final String TABLE_NAME = "employee";
			
	public static enum Columns {
		EMPLOYEE_ID("employee_id", 0, "Employee ID"),
		REVISION_ID("revision_id", 1, "Revision ID"),
		HEAD("head", 2, "Head Marker"),
		DELETED("deleted", 3, "Deleted Marker"),
		USERNAME("username", 4, "User Name"),
		PASSWORD("password", 5, "Password"),
		USER_TYPE("user_type", 6, "User Level"),
		LAST_NAME("last_name", 7, "Last Name"),
		FIRST_NAME("first_name", 8, "First Name"),
		MIDDLE_NAME("middle_name", 9, "Middle Name"),
		SSS_ID("sss_id", 10, "SSS ID"),
		EMPLOYED_STATUS("employed_status", 11, "Employment Status"),
		GENDER("gender", 12, "Gender"),
		DATE_HIRED("date_hired", 13, "Date Hired"),
		DATE_FIRED("date_fired", 14, "Date Fired"),
		SECRET_ID("secret_id", 15, "Security Question"),
		SECRET_ANSWER("secret_answer", 16, "Answer");		
		
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
	
	public EmployeeFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
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
	public String getMainTableName() {
		return EmployeeFacilitator.TABLE_NAME;
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

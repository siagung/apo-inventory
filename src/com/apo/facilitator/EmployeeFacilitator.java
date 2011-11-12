package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

public class EmployeeFacilitator extends Facilitator {

	public static final String TABLE_NAME = "employee";
			
	public static enum Columns {
		EMPLOYEE_ID("product_id", 0),
		REVISION_ID("revision_id", 1),
		HEAD("head", 2),
		DELETED("deleted", 3),
		USERNAME("username", 4),
		PASSWORD("password", 5),
		USER_TYPE("user_type", 6),
		LAST_NAME("last_name", 7),
		FIRST_NAME("first_name", 8),
		MIDDLE_NAME("middle_name", 9),
		SSS_ID("sss_id", 10),
		EMPLOYED_STATUS("employed_status", 11),
		GENDER("gender", 12),
		DATE_HIRED("date_hired", 13),
		DATE_FIRED("date_fired", 14),
		SECRET_ID("secret_id", 15),
		SECRET_ANSWER("secret_answer", 16);		
		
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
		return EmployeeFacilitator.TABLE_NAME;
	}

}

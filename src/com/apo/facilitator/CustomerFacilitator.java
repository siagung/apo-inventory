package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

public class CustomerFacilitator extends Facilitator {

	public static final String TABLE_NAME = "customer";
	
	public static enum Columns {
		CUSTOMER_ID("customer_id", 0),
		REVISION_ID("revision_id", 1),
		HEAD("head", 2),
		DELETED("deleted", 3),
		NAME("name", 4),
		REPRESENTATIVE("representative", 5);
		
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
	
	public CustomerFacilitator(RevisableDBOperator operator) {
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
		return CustomerFacilitator.TABLE_NAME;
	}

}

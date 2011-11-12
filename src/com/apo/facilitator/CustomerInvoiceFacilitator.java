package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

public class CustomerInvoiceFacilitator extends Facilitator {

	public static final String TABLE_NAME = "customer_invoice";
	
	public static enum Columns {
		INVOICE_ID("invoice_id", 0),
		REVISION_ID("revision_id", 1),
		HEAD("head", 2),
		DELETED("deleted", 3),
		CURRENCY("currency", 4),
		EXCHANGE_RATE("exchange_rate", 5),
		ORDER_DATE("order_date", 6),
		EMPLOYEE_ID("employee_id", 7),
		ORDER_TYPE("order_type", 8),
		PAYMENT_TERMS("payment_terms", 9);
		
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
	
	public CustomerInvoiceFacilitator(RevisableDBOperator operator) {
		super(operator);
	}

	@Override
	public String getMainTableName() {
		return CustomerInvoiceFacilitator.TABLE_NAME;
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

}

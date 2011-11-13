package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class CustomerInvoiceFacilitator extends Facilitator {

	public static final String TABLE_NAME = "customer_invoice";
	
	public static enum Columns {
		INVOICE_ID("invoice_id", 0, "Invoice ID"),
		REVISION_ID("revision_id", 1, "Revision ID"),
		HEAD("head", 2, "Head Marker"),
		DELETED("deleted", 3, "Delete Marker"),
		CURRENCY("currency", 4, "Currency"),
		EXCHANGE_RATE("exchange_rate", 5, "Exchange Rate"),
		ORDER_DATE("order_date", 6, "Order Date"),
		EMPLOYEE_ID("employee_id", 7, "Employee ID"),
		ORDER_TYPE("order_type", 8, "Order Type"),
		PAYMENT_TERMS("payment_terms", 9, "Payment Terms");
		
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
	public TableModel getTableModel(String filter, String[] columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComboBoxModel getComboBoxModel(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}

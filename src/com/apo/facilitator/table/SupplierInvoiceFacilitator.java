package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class SupplierInvoiceFacilitator extends Facilitator {

	public static final String TABLE_NAME = "supplier";
	
	public static enum Columns {
		INVOICE_ID("invoice_id", 0, "Invoice ID"),
		REVISION_ID("revision_id", 1, "Revision ID"),
		HEAD("head", 2, "Head"),
		DELETED("deleted", 3, "Deleted"),
		SUPPLIER_ID("supplier_id", 4, "Supplier ID"),
		INVOICE_NUM("invoice_num", 5, "Invoice Number"),
		CURRENCY("currency", 6, "Currency"),
		EXCHANGE_RATE("exchange_rate", 7, "Exchange Rate"),
		ORDER_DATE("order_date", 8, "Order Date");
		
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
	
	public SupplierInvoiceFacilitator(RevisableDBOperator operator) {
		super(operator);
	}

	@Override
	public String getMainTableName() {
		return SupplierInvoiceFacilitator.TABLE_NAME;
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

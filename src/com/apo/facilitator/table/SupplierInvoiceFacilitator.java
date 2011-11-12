package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class SupplierInvoiceFacilitator extends Facilitator {

	public static final String TABLE_NAME = "supplier";
	
	public static enum Columns {
		INVOICE_ID("invoice_id", 0),
		REVISION_ID("revision_id", 1),
		HEAD("head", 2),
		DELETED("deleted", 3),
		SUPPLIER_ID("supplier_id", 4),
		INVOICE_NUM("invoice_num", 5),
		CURRENCY("currency", 6),
		EXCHANGE_RATE("product_desc", 7),
		ORDER_DATE("order_date", 8);
		
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

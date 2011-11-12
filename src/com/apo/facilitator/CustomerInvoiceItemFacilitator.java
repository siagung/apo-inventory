package com.apo.facilitator;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.operator.RevisableDBOperator;

public class CustomerInvoiceItemFacilitator extends Facilitator {

	public static final String TABLE_NAME = "customer_invoice_item";
	
	public static enum Columns {
		ORDER_ID("order_id", 0),
		REVISION_ID("revision_id", 1),
		PRODUCT_ID("product_id", 2),
		QUANTITY("quantity", 3),
		SOLD_PRICE("soldPrice", 4);
		
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
	
	public CustomerInvoiceItemFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMainTableName() {
		return CustomerInvoiceItemFacilitator.TABLE_NAME;
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

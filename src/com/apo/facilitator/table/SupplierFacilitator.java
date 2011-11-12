package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class SupplierFacilitator extends Facilitator {

	public static final String TABLE_NAME = "supplier";
	
	public static enum Columns {
		PRODUCT_ID("product_id", 0),
		REVISION_ID("revision_id", 1),
		HEAD("head", 2),
		DELETED("deleted", 3),
		PRODUCT_NAME("product_name", 4),
		MODEL("model", 5),
		BRAND("brand", 6),
		PRODUCT_DESC("product_desc", 7),
		CATEGORY_ID("category_id", 8),
		PRICE("srprice", 9),
		STOCK("stock", 10),
		UNIT("unit", 11);
		
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
	
	public SupplierFacilitator(RevisableDBOperator operator) {
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
		return SupplierFacilitator.TABLE_NAME;
	}

}

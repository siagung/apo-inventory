package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class ProductFacilitator extends Facilitator {

	public static final String TABLE_NAME = "product";
	
	public static enum Columns {
		PRODUCT_ID("product_id", 0, "Product ID"),
		REVISION_ID("revision_id", 1, "Revision ID"),
		HEAD("head", 2, "Head Marker"),
		DELETED("deleted", 3, "Delete Marker"),
		PRODUCT_NAME("product_name", 4, "Product Name"),
		MODEL("model", 5, "Model"),
		BRAND("brand", 6, "Brand"),
		PRODUCT_DESC("product_desc", 7, "Description"),
		CATEGORY_ID("category_id", 8, "Category"),
		PRICE("srprice", 9, "Unit Price"),
		STOCK("stock", 10, "Stock"),
		UNIT("unit", 11, "Unit");
		
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

	public ProductFacilitator(RevisableDBOperator operator) {
		super(operator);
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
		return ProductFacilitator.TABLE_NAME;
	}

}

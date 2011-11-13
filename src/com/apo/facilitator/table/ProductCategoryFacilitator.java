package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class ProductCategoryFacilitator extends Facilitator {

	public static final String TABLE_NAME = "product_category";
	
	public static enum Columns {
		CATEGORY_ID("category_id", 0, "Category ID"),
		CATEGORY_NAME("category_name", 1, "Category Name"),
		CATEGORY_DESC("category_desc", 2, "Description");
		
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
	
	public ProductCategoryFacilitator(RevisableDBOperator operator) {
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
		return ProductCategoryFacilitator.TABLE_NAME;
	}

}

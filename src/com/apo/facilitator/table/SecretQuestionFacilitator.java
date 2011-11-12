package com.apo.facilitator.table;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class SecretQuestionFacilitator extends Facilitator {

	public static final String TABLE_NAME = "secret_question";
	
	public static enum Columns {
		SECRET_ID("secret_id", 0),
		QUESTION("question", 1);
		
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
	
	public SecretQuestionFacilitator(RevisableDBOperator operator) {
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
		return SecretQuestionFacilitator.TABLE_NAME;
	}

}

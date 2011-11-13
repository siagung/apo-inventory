package com.apo.facilitator.table;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class CustomerPaymentFacilitator extends Facilitator {

	public static final String TABLE_NAME = "customer_order_payment";
	
	public static enum Columns {
		PAYMENT_ID("payment_id", 0, "Payment ID"),
		ORDER_ID("order_id", 1, "Order ID"),
		CLEARING_CODE("clearing_code", 2, "Clearing Code"),
		MODE_OF_PAYMENT("mode_of_payment", 3, "Payment Method"),
		AMOUNT_PAID("amount_paid", 4, "Amount Paid"),
		CHECK_BANK_CODE("check_bank_code", 5, "Check Bank Code"),
		CHECK_NUMBER("check_number", 6, "Check Number"),
		CHECK_DATE("check_date", 7, "Check Date"),
		DATE_PAID("date_paid", 8, "Date Paid");
		
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
	
	public CustomerPaymentFacilitator(RevisableDBOperator operator) {
		super(operator);
	}

	@Override
	public String getMainTableName() {
		return CustomerPaymentFacilitator.TABLE_NAME;
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
	public int newItem(HashMap data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int newRevision(int id, HashMap data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markDelete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void permanentlyDelete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revertItem(int id, int newHeadRevision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restoreItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePreviousRevisions(int id) {
		// TODO Auto-generated method stub
		
	}

}

package com.apo.facilitator.table;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import com.apo.facilitator.Facilitator;
import com.apo.operator.RevisableDBOperator;

public class StockReportFacilitator extends Facilitator {

	public StockReportFacilitator(RevisableDBOperator operator) {
		super(operator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMainTableName() {
		// TODO Auto-generated method stub
		return null;
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

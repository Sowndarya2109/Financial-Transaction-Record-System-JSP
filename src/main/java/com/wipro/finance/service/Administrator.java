package com.wipro.finance.service;

import java.sql.Date;
import java.util.List;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import com.wipro.finance.bean.TransactionBean;
import com.wipro.finance.dao.TransactionDAO;

public class Administrator {
	TransactionDAO dao = new TransactionDAO();
	
	public String addRecord(TransactionBean bean) throws InvalidInputException {
		if(bean == null || bean.getAccountHolder() == null || bean.getTransType() == null || bean.getTransDate() == null) {
			throw new InvalidInputException();
		}
		if(bean.getAccountHolder().length() < 2) {
			return "INVALID ACCOUNT HOLDER";
		}
		if(bean.getAmount() <= 0) {
			return "INVALID AMOUNT";
		}
		if(!bean.getTransType().equals("Credit") && !bean.getTransType().equals("Debit")) {
			return "INVALID TRANSACTION TYPE";
		}
		if(dao.recordExists(bean.getAccountHolder(), bean.getTransDate())) {
			return "ALREADY EXISTS";
		}
		String transID = dao.generateTransID(bean.getAccountHolder(), bean.getTransDate());
		bean.setTransId(transID);
		String status = dao.createRecord(bean);
		
		return status;
	}
	public TransactionBean viewRecord(String accountHolder, Date transDate) {
		return dao.fetchRecord(accountHolder, transDate);	
	}
	public List<TransactionBean> viewAllRecords(){
		return dao.fetchAllRecords();
	}
}

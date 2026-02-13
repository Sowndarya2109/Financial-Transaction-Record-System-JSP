package com.wipro.finance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.finance.bean.TransactionBean;
import com.wipro.finance.util.DBUtil;

public class TransactionDAO {
	public String createRecord(TransactionBean bean) {
		Connection con = DBUtil.getDBConnection();
		String query = "INSERT INTO TRANSACTION_TB VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bean.getTransId());
			ps.setString(2, bean.getAccountHolder());
			ps.setDouble(3, bean.getAmount());
			ps.setDate(4, bean.getTransDate());
			ps.setString(5, bean.getTransType());
			ps.setString(6, bean.getRemarks());
			
			if(ps.executeUpdate() > 0)
				return bean.getTransId();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "FAIL";
	}
	public TransactionBean fetchRecord(String accountHolder, Date transDate) {
		Connection con = DBUtil.getDBConnection();
		String query = "SELECT * FROM TRANSACTION_TB WHERE accountHolder = ? AND transDate = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, accountHolder);
			ps.setDate(2, transDate);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				TransactionBean bean = new TransactionBean();
				bean.setTransId(rs.getString(1));
				bean.setAccountHolder(rs.getString(2));
				bean.setAmount(rs.getDouble(3));
				bean.setTransDate(rs.getDate(4));
				bean.setTransType(rs.getString(5));
				bean.setRemarks(rs.getString(6));
				
				return bean;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String generateTransID(String accountHolder, Date transDate) {
		Connection con = DBUtil.getDBConnection();
		DateFormat f = new SimpleDateFormat("yyyyMMdd");
		String date = f.format(transDate);
		String name = accountHolder.substring(0,2).toUpperCase();
		String query = "SELECT TRANS_SEQ.NEXTVAL from dual";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				long seq = rs.getLong(1);
				
				return date + name + seq;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean recordExists(String accountHolder, Date transDate) {
		Connection con = DBUtil.getDBConnection();
		String query = "SELECT * FROM TRANSACTION_TB WHERE accountHolder = ? AND transDate = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, accountHolder);
			ps.setDate(2, transDate);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<TransactionBean> fetchAllRecords(){
		Connection con = DBUtil.getDBConnection();
		List<TransactionBean> list = new ArrayList<>();
		String query = "SELECT * FROM TRANSACTION_TB";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TransactionBean bean = new TransactionBean();
				bean.setTransId(rs.getString(1));
				bean.setAccountHolder(rs.getString(2));
				bean.setAmount(rs.getDouble(3));
				bean.setTransDate(rs.getDate(4));
				bean.setTransType(rs.getString(5));
				bean.setRemarks(rs.getString(6));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
 	}
}

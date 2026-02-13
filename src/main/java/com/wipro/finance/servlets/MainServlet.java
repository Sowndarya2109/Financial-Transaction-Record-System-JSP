package com.wipro.finance.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import com.wipro.finance.bean.TransactionBean;
import com.wipro.finance.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if(operation.equals("newRecord")) {
			String result = addRecord(request);
			if(result.equals("FAIL") || result.equals("INVALID INPUT"))
				response.sendRedirect("error.html");
			else
				response.sendRedirect("success.html");
		}
		else if(operation.equals("viewRecord")) {
			TransactionBean bean = viewRecord(request);
			if(bean == null) {
				request.setAttribute("message", "No matching records exists!");
			}
			else {
				request.setAttribute("bean", bean);
			}		
			RequestDispatcher rd = request.getRequestDispatcher("displayTransaction.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("viewAllRecords")) {
			List<TransactionBean> list = viewAllRecords(request);
			if(list.isEmpty()) {
				request.setAttribute("message", "No records available!");
			}
			else {
				request.setAttribute("list", list);
			}
			RequestDispatcher rd = request.getRequestDispatcher("displayAllTransactions.jsp");
			rd.forward(request, response);			
		}
	}
	public String addRecord(HttpServletRequest request) {
		try {
		String transId = request.getParameter("transId");
		String accountHolder = request.getParameter("accountHolder");
		String amount = request.getParameter("amount");
		String transDate = request.getParameter("transDate");
		String transType = request.getParameter("transType");
		String remarks = request.getParameter("remarks");
		
		if(accountHolder == null || accountHolder.isEmpty() ||
		   amount == null || amount.isEmpty() ||
		   transDate == null || transDate.isEmpty() ||
		   transType == null || transType.isEmpty()) {

		    return "FAIL";
		 }
	 
		TransactionBean bean = new TransactionBean();
		bean.setTransId(transId);
		bean.setAccountHolder(accountHolder);
		bean.setAmount(Double.parseDouble(amount));
		bean.setTransDate(Date.valueOf(transDate));
		bean.setTransType(transType);
		bean.setRemarks(remarks);
		
		return new Administrator().addRecord(bean);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL";
		}
	}
	public TransactionBean viewRecord(HttpServletRequest request) {
		String accountHolder = request.getParameter("accountHolder");
		String transDate = request.getParameter("transDate");
		
		Date date = Date.valueOf(transDate);
		return new Administrator().viewRecord(accountHolder, date);
	}
	public List<TransactionBean> viewAllRecords(HttpServletRequest request){
		return new Administrator().viewAllRecords();
	}
	
}

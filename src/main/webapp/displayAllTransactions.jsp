<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wipro.finance.bean.TransactionBean" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>All Transaction Details</h1>
<%
List<TransactionBean> list = (List<TransactionBean>) request.getAttribute("list");
String message = (String) request.getAttribute("message");

if(list != null){
%>

<table border="1">
<tr>
    <th>Transaction ID</th>
    <th>Account Holder</th>
    <th>Amount</th>
    <th>Transaction Date</th>
    <th>Transaction Type</th>
    <th>Remarks</th>
</tr>

<%
for(TransactionBean bean : list){
%>

<tr>
    <td><%= bean.getTransId() %></td>
    <td><%= bean.getAccountHolder() %></td>
    <td><%= bean.getAmount() %></td>
    <td><%= bean.getTransDate() %></td>
    <td><%= bean.getTransType() %></td>
    <td><%= bean.getRemarks() %></td>
</tr>

<%
}
%>

</table>

<%
}else if(message != null){
%>
 
<h3 style="color:red;"><%= message %></h3>

<% } %>
</body>
</html>
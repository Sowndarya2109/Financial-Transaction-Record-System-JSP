<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.wipro.finance.bean.TransactionBean" %>  
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Transaction Details<h1>
<%
TransactionBean bean = (TransactionBean) request.getAttribute("bean");
String message = (String) request.getAttribute("message");
if(bean != null){
%>

Transaction ID: <%= bean.getTransId() %><br><br>
Account Holder: <%= bean.getAccountHolder() %><br><br>
Amount: <%= bean.getAmount() %><br><br>
Transaction Date: <%= bean.getTransDate() %><br><br>
Transaction Type: <%= bean.getTransType() %><br><br>
Remarks: <%= bean.getRemarks() %><br><br>

<%
}else if(message != null){
%>
 
<h3 style="color:red;"><%= message %></h3>

<% } %>
</body>
</html>
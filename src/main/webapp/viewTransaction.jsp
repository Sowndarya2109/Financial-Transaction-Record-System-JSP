<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MainServlet" method="post">
<h1>View Transaction Record</h1>
Account Holder:
<input type="text" name="accountHolder"><br><br>
Transaction Date:
<input type="date" name="transDate"><br><br>
<input type="hidden" name="operation" value="viewRecord"><br><br>
<input type="submit" value="View Record">
</form>
</body>
</html>
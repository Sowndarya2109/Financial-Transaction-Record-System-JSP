<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "MainServlet" method = "post">
<h1>Add Transaction Record</h1>
Transaction ID:
<input type="text" name="transID"><br><br>
Account Holder:
<input type="text" name="accountHolder"><br><br>
Amount:
<input type="text" name="amount"><br><br>
Transaction Date:
<input type="date" name="transDate"><br><br>
Transaction Type:
<select name="transType">
<option value="Credit">Credit</option>
<option value="Debit">Debit</option>
</select><br><br>
Remarks:
<input type="text" name="remarks"><br><br>

<input type="hidden" name="operation" value="newRecord"><br><br>
<input type = "submit" value = "Add Record">
</form>
</body>
</html>
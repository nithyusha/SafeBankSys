<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HR Transactions</title>
</head>
<body>

<h3> HR Transactions</h3>
<form>
<table>
<tr>
	<th>Transaction id</th>
	<th>Transaction Name</th>
	<th>Transaction Description</th>
	<th>Status</th>
	<th>Created by</th>
	<th>Approver 1 (Department Manager)</th>
	<th>Approver 2 Corporate Manager</th>
	
	

</tr>
<c:forEach items="${transactionList}" var="transaction">
	
    <tr>
        <td>${transaction.tId}</td>
        <td>${transaction.transcName}</td>
         <td>${transaction.transDesc}</td>
        <td>${transaction.status}</td>
         <td>${transaction.createdBy}</td>
        <td>${transaction.approver1}</td>
         <td>${transaction.approver2}</td>
        
     </tr>
</c:forEach>
</table>

</form>

</body>
</html>
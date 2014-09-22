<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRANSACTION LIST</title>
</head>
<body>

<%@ include file="individual_user.jsp" %>
<table>
<tr>
	<th>Transaction ID</th>
	<th>Status</th>
	<th>Date and Time</th>
	<th>Involved Party</th>
	<th>Transaction Type</th>
	<th>Available Balance</th>
</tr>
<br/>
<c:forEach items="${transactionList}" var="transaction">
	<tr>
		<td>${transaction.transactionId }</td>
		<td>${transaction.status }</td>
		<td>${transaction.date }</td>
		<td>${transaction.involvedParty }</td>
		<td>${transaction.transactionType }</td>
		<td>${transaction.amt }</td>
		
	</tr>
	
</c:forEach>
</table>
</body>
</html>
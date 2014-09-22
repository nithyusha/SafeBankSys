<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requests for Authorizations</title>
</head>
<body>
<body>

<h3> Sales Transactions</h3>
<form>
<table>
<tr>
	<th>Transaction id</th>
	<th>Account No</th>
	<th>Type</th>
	<th>Amount</th>
	<th>Status</th>
	<th>Created by</th>
	<th>Approver 1 (Department Manager)</th>
	<th>Approver 2 Corporate Manager</th>
</tr>
<c:forEach items="${transactionList}" var="transaction">
	
    <tr>
       <td>${transaction.tId}</td>
        <td>${transaction.accNo}</td>
         <td>${transaction.type}</td>
         <td>${transaction.amount}</td>
        <td>${transaction.status}</td>
         <td>${transaction.createdBy}</td>
        <td>${transaction.approver1}</td>
         <td>${transaction.approver2}</td>
         
        <td> <a href="${pageContext.request.contextPath}/TM/authorizeTransByCorpMgr/${transaction.tId}">Authorize</a></td>

     </tr>
</c:forEach>
</table>

</form>

</body>

</body>
</html>
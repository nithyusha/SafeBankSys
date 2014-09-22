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
	<th>Transaction Name</th>
	<th>Transaction Description</th>
	<th>Created by</th>
	<th>Created Date</th>
</tr>
<c:forEach items="${transactionList}" var="transaction">
	
    <tr>
        <td>${transaction.tId}</td>
        <td>${transaction.transcName}</td>
         <td>${transaction.transDesc}</td>
         <td>${transaction.createdBy}</td>
        <td>${transaction.creationDate}</td>
         
        <td> <a href="${pageContext.request.contextPath}/sales/authorizeTransByDMgr/${transaction.tId}">Authorize</a></td>

     </tr>
</c:forEach>

</table>
<h3>${errormsg}</h3>
</form>

</body>

</body>
</html>
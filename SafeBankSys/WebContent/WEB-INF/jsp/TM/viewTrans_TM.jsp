<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transaction form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/TM/viewTransaction" method="POST" >
	<h1>View Transaction </h1></br></br>
	
	<table>
    <tr>
        <td><p> Enter the Transaction Id :</p></td>
        <td><input type="text" name="tid"></td> 
    </tr>
    
    <tr>
       	<td> <input type="submit" value="View" /></td>
    </tr>
</table>

<c:if test="${not empty tmTransaction}">
    <div>
        <form:form method="post" commandName="tmTransaction">
		<table>
		<tr>
			<td>Transaction id:</td>
			<td>${tmTransaction.tId}</td>
		</tr>
		<tr>
			<td>Transaction Type:</td>
			<td>${tmTransaction.type}</td>
		</tr>
		<tr>
			<td>Acc No:</td>
			<td>${tmTransaction.accNo}</td>
		</tr>
		<tr>
			<td>A:</td>
			<td>${tmTransaction.amount}</td>
		</tr>
		<tr>
			<td>Status:</td>
			<td>${tmTransaction.status}</td>
		</tr>
		<tr>
			<td>Created by:</td>
			<td>${tmTransaction.createdBy }</td>
		</tr>
		<tr>
			<td>Approver 1 : (Department Manager):</td>
			<td>${tmTransaction.approver1}</td>
		</tr>
		<tr>
			<td>Approver 2 : (Corporate Manager):</td>
			<td>${tmTransaction.approver2 }</td>
		</tr>
		
		<tr>
			<td> ${msg}</td>
		</tr>
		<tr>
			<td> ${errormsg}</td>
		</tr>
		
		</table>
        </form:form>
    </div>
</c:if>
<table>


</form>

</body>
</html>
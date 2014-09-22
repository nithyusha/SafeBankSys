<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corporate Manager</title>
</head>
<body>

	<table>
		<tr>
			<td><a href="${pageContext.request.contextPath}/getAddEmployeeForm" >Add Employee</a></td>
			
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/getDeleteEmployeeForm" >Delete Employee</a></td>
			
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/getTransferEmployeeForm" >Transfer Employee</a></td>
			
		</tr>
		
		<tr>
			<td><a href="${pageContext.request.contextPath}/CM/viewAllPendingTransForCorpMgr" >Authorize Transactions</a></td>
			
		</tr>
		
	</table>
<c:url var="url" value="/j_spring_security_logout"></c:url>  
    <b> <a href="${url}">Logout</a> </b>  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAFE BANK SYS</title>
</head>
<body>

<%@ include file="merchant.jsp" %>

<table>
<c:forEach items="${list}" var="lis">

	<tr>
		<td>Card Number</td>
		<td>${lis.cardNo } </td>
	</tr>
	
	<tr>
		<td>Expiry</td>
		<td>${lis.expiry }</td>
	</tr>
	
	<tr>
		<td>SSV Number</td>
		<td>${lis.ssv_No }</td>
	</tr>
	
</c:forEach>
</table>


</body>
</html>
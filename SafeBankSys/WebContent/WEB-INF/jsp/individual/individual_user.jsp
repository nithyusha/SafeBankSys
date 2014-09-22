<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INDIVIDUAL USER</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/individual/viewTrans">View All</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/debit">Debit/Withdrawl</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/credit">Credit/Deposit</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/generateCertificate">Generate Certificate</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/enterMessageDetail2">Verify Certificate to Start Transfer</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/viewAllMerchants">View All Merchants</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/viewAccDetails">Account Details</a>
<br>
	<a href="${pageContext.request.contextPath}/individual/viewCardDetails">Card Details</a>
<br>
<c:url var="url" value="/j_spring_security_logout"></c:url>  
    <b> <a href="${url}">Logout</a> </b>  
<br>
<br>
	
</body>
</html>
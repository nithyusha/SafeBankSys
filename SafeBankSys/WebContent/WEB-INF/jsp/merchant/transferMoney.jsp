<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file="merchant.jsp" %>
<form action="${pageContext.request.contextPath}/merchant/transfer" method="POST">
	Enter Account Information (Checking Account) of the User whom the transfer has to be made:
	
	<br>
	Name: <input type="text" name="name">
	<br>
	Account Number: <input type="text" name="accNo">
	<br>
	<input type="submit">
	
</form>
</body>
</html>
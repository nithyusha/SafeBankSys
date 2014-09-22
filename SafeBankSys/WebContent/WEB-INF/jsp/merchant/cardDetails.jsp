<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%@ include file="merchant.jsp" %>
	<form action="${pageContext.request.contextPath}/card/validate" method="POST" >
		<span>${certificateStatusMessage }</span></br></br>
		ENTER YOUR CARD DETAILS:

		<table>
			<tr>
				<td>Card Number</td>
				<td><input type="text" name="cardNo"></td>
			</tr>

			<tr>
				<td>Expiry</td>
				<td><input type="text" name="expiry" /></td>
			</tr>

			<tr>
				<td>SSV Number</td>
				<td><input type="text" name="ssv_No"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit"></td>
			</tr>
		</table>

	</form>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Validation of Card</title>
</head>
<body>

<%@ include file="individual_user.jsp" %>
	<form action="${pageContext.request.contextPath}/individual/transferMoney1" method="POST" >
	
	ENTER THE CARD DETAILS:
	
	<table>
		<tr>
			<td>Card Number</td>
			<td><input type="text" name="cardNo"></td>
		</tr>
		
		<tr>
			<td>Expiry</td>
			<td><input type="text" name="expiry"/></td>
		</tr>
		
		<tr>
			<td>SSV Number</td>
			<td><input type="text" name="ssv_No"></td>
		</tr>
		
		<tr>
			<td>Account Number of Person whom transfer has to be made</td>
			<td><input type="text" name="accNo"></td>
		</tr>
		
		<tr>
			<td>Amount</td>
			<td><input type="text" name="amt"></td>

		<tr>
			<td></td>
			<td><input type="submit"></td>
		</tr>
	</table>




	</form>

</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Validation of Card</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/card/validate" method="POST" >
	
	ENTER YOUR CARD DETAILS:
	
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
			<td>Name</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit"></td>
		</tr>
	</table>

	<c:if test="${not empty cardVal}">
		<form:form method="POST" commandName="cardVal">
				
		
		</form:form>
	</c:if>

	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Manager</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/transferEmployee" method="POST" >
	<table>
		<tr>
			<td><p>Enter the customer Id:</p></td>
			<td><p><input type="text" name="empId"/></p></td>
		</tr>
		<tr>
			<td><p>Enter the department Id:</p></td>
			<td><p><input type="text" name="deptId"/></p></td>
		</tr>
		<tr>
			<td><p>Transfer Employee</p></td>
			<td><p><input type="submit" value="Transfer Employee"/></p></td>
		</tr>

	</table>
<p> ${msg}</p>
<p> ${errormsg}</p>
</form>
</body>
</html>
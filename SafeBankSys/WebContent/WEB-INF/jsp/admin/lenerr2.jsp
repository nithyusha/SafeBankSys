<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAFE BANK SYS</title>
</head>
<body>
	PASSWORD RULES:
	
	<br><br>
		# Start of group	
<br>		#   must contains one digit from 0-9
<br>		#   must contains one lowercase characters
<br>		#   must contains one uppercase characters
<br>		#   must contains one special symbols in the list ".@#$%"
<br>		#   match anything with previous condition checking
<br>		#   length at least 8 characters and maximum of 32	
	
	<br><br/>
	
	Click <a href="${pageContext.request.contextPath}/admin/getRegistrationForm">here</a> to go back. 
	
	
</body>
</html>
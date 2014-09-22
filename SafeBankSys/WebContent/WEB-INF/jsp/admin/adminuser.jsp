<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Admin User</title>
</head>
<body>
<h3>Admin Users</h3>
<a href="${pageContext.request.contextPath}/admin/getRegistrationForm"> User Registration</a> </br>
<a href="${pageContext.request.contextPath}/admin/transactionLogs"> Transaction Logs</a> </br>

<p>${errormsg}</p>
</br>
<c:url var="url" value="/j_spring_security_logout"></c:url>  
    <b> <a href="${url}">Logout</a> </b>

<%-- <a href="${pageContext.request.contextPath}/login" >Logout</a> --%>
<%-- <a href="${logoutUrl}" > Logout</a> --%>
<%-- <a href="<c:url value='/j_spring_security_logout' />">Logout </a> --%>
</body>

</html>

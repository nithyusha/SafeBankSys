<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>${msg}</h1>

<a href="${pageContext.request.contextPath}/It/getCreateTransForm"> Create</a> </br>
<a href="${pageContext.request.contextPath}/It/getViewTransForm"> View</a></br>
<a href="${pageContext.request.contextPath}/It/getQueryModifyTransForm"> Modify</a></br>
<a href="${pageContext.request.contextPath}/It/getQueryDeleteTransForm"> Delete</a></br>
<a href="${pageContext.request.contextPath}/It/viewAllTransactions">View Department Transactions</a></br>
</br>
<p>${successmsg}</p>
<p>${errormsg}</p>

</br></br>

		
<a href="${pageContext.request.contextPath}/login" >Logout</a>
	
		<c:url var="url" value="/j_spring_security_logout"></c:url>  
    <b> <a href="${url}">Logout</a> </b>  		
</body>
</html>

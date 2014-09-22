<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Sales Transaction </title>
</head>
<body>

<form:form method="post" action="${pageContext.request.contextPath}/sales/createTransaction" modelAttribute="createTransSalesForm"  >
 
    <table>
    <tr>
        <td><form:label path="transcName">Transaction Name</form:label></td>
        <td><form:input path="transcName" /></td> 
    </tr>
    
    <tr>
        <td><form:label path="transDesc">Transaction Description</form:label></td>
       	<td> <form:textarea path="transDesc" rows="5" cols="30" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Create"/>
        </td>
    </tr>
</table>  
     
</form:form>


</body>
</html>
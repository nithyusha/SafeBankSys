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

<form:form method="post" action="${pageContext.request.contextPath}/TM/modifyTransaction" modelAttribute="modifyTransTMForm"  >
 
    <table>
    <tr>
        <td><form:label path="tId">Transaction Id :</form:label></td>
        <td><form:input path="tId" /></td> 
    </tr>
    
    <tr>
        <td><form:label path="AccNo">Account No</form:label></td>
        <td><form:input path="AccNo" /></td> 
    </tr>
    <tr>
        <td><form:label path="amount">Amount</form:label></td>
        <td><form:input path="amount" /></td> 
    </tr>
    <td><form:label path="type"> Type </form:label></td>
			<td><form:select path="type">
					<form:option value="NONE" label="--- Select ---" />
					<form:option value="DEBIT" label="DEBIT" />
					<form:option value="CREDIT" label="CREDIT" />
    		</form:select></td>
    <tr>
        <td colspan="2">
            <input type="submit" value="Create"/>
        </td>
    </tr>
</table>  
     
</form:form>


</body>
</html>
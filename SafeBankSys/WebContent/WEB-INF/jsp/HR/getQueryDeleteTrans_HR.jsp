<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/HR/deleteTrans" method="POST" >
	<h1>View Transaction </h1></br></br>
	
	<table>
    <tr>
        <td><p> Enter the Transaction Id :</p></td>
        <td><input type="text" name="tid"></td> 
    </tr>
    
    <tr>
       	<td> <input type="submit" value="Delete" /></td>
    </tr>
</table>
</form>
 <p>${message}</p>
</body>
</html>
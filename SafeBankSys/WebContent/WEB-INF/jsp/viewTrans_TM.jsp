<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function getDetails(form){
form.action ="/TM/getTransToView";
form.submit();
}
</script>
<form:form method="POST" commandName="viewTrans">
		
		<table>
			<tr>
				<td>Account No :</td>
				<td><form:input path="AccNo" />
				</td>
				<td><form:errors path="AcctNo" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Date :</td>
				<td><form:input path="date" />
				</td>
				<td><form:errors path="date" cssClass="error" />
				</td>
			</tr>
			
			
			<tr>
				<td colspan="3"><input type="submit" onclick="javascript:getDetails(this.form);" /></td>
			</tr>
		</table>
	</form:form>
 

</body>
</html>
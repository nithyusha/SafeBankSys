<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Individual User - Credit Form</title>
</head>
<body>
	<form:form action="/user/debit" method="POST"
		modelAttrribute="debitform">
		<table>

			<tr>
				<td><form:label path="accountType"> Account Type </form:label></td>
				<td><form:select path="accountType">
						<form:option value="NONE" label="--- Select ---" />
						<form:option value="CheckingAccount" label="Checking Account" />
						<form:option value="SavingsAccount" label="Savings Account" />
					</form:select></td>
			</tr>
			<tr>
			<tr>
				<td><form:label path="amount">Amount </form:label></td>
				<td><form:input path="amount" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Debit" /></td>
			</tr>

		</table>

	</form:form>

</body>
</html>
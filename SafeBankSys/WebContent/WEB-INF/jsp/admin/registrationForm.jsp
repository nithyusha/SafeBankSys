<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
	<form:form
		action="${pageContext.request.contextPath}/admin/registeruser"
		method="POST" modelAttribute="registrationform">
		<table>
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
			<td><form:label path="role"> Role </form:label></td>
			<td><form:select path="role">
					<form:option value="NONE" label="--- Select ---" />
					<form:option value="ROLE_REGULAR_EMP" label="ROLE_REGULAR_EMP" />
					<form:option value="ROLE_DEPARTMENT_MGR" label="ROLE_DEPARTMENT_MGR" />
					<form:option value="ROLE_CORPORATE_MGR" label="ROLE_CORPORATE_MGR" />
					<form:option value="ROLE_INDIVIDUAL_USER"
						label="ROLE_INDIVIDUAL_USER" />
					<form:option value="ROLE_MERCHANT" label="ROLE_MERCHANT" />
				</form:select>
				</td>
				</tr>
				<tr>
			<td><label > Department </label></td>
			<td><select name="dept">
					<option value="NONE" label="--- Select ---" />
					<option value="0" label="Not applicable" />
					<option value="1" label="Sales" />
					<option value="2" label="HR" />
					<option value="3" label="IT & Tech Support" />
					<option value="4"
						label="Company Management" />
					<option value="5" label="TRANSACTION MONITORING" />
				</select>
				</td>
				</tr>
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" /></td>
			</tr>

			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:textarea path="address" rows="5" cols="30" /></td>
			</tr>
			<tr>
				<td><form:label path="emailId">Email Id</form:label></td>
				<td><form:input path="emailId" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfBirth">Date of Birth</form:label></td>
				<td><form:input path="dateOfBirth" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>


		</table>
	</form:form>


</body>
</html>
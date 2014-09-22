<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Safe Bank Sys</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.js"></script>
	
	

</head>


<body oncontextmenu="disableRightClick()">
	<div class="container">
		
		<hr></hr>
		<div class="row">
			 
			<div class="span9">
				<form:form method="GET" commandName="certificateFormBean"
					action="${pageContext.request.contextPath}/pki/verifySignature">

					

					<table>
					
					<tr>
							<td><form:label path="">Sender's User Name :  </form:label></td>
							<td><form:input path="senderUserName"></form:input></td>
							<%-- <td><label>${ certificateFormBean.serialNumber}</label></td>
						 --%></tr>
						<tr>
							<td><form:label path="">Enter the exact message received in e-mail:  </form:label></td>
							<td><form:input path="message"></form:input></td>
							<%-- <td><label>${ certificateFormBean.serialNumber}</label></td>
						 --%></tr>
						 <tr>
							<td><form:label path="">Enter the exact signature of sender received in e-mail:  </form:label></td>
							<td><form:input path="signature"></form:input></td>
							<%-- <td><label>${ certificateFormBean.serialNumber}</label></td>
						 --%></tr>
						
					</table>
					<div class="control-group" align="center">
						<input type="submit" value="Decrypt Message Using Sender's Public Key" name="DecryptMessage" class="btn btn-success btn-sm" />
					</div>
				</form:form>
			</div>
		</div>

	</div>		
</body>
</html>
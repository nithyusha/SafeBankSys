<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Secure Banking System</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.js"></script>
</head>


<%@ include file="individual_user.jsp" %>
<body oncontextmenu="disableRightClick()">
	<div class="container">
		
		<hr></hr>
		<div class="row">
			 
			<div class="span9">
				<form:form method="GET" commandName="certificateFormBean"
					action="${pageContext.request.contextPath}/individual/enterMessageDetails">
					<c:if test="${ errorMessage != null}">
				<div style="font-size: 15px; color: black;">
					<div id="status_message">${errorMessage}</div> 
				</div>
					 		
				</c:if>
				<c:if test="${ certificateStatusMessage != null}">
				<div style="font-size: 15px; color: black;">
					<div id="status_message">${certificateStatusMessage}</div> 
				</div>
					 		
				</c:if> 

					<%-- <div align = "left"><h4>User Certificate</h4></div>
					<table>
						
						<tr>
							<td><form:label path="">Certificate Issuer Name:  </form:label></td>
							<td><form:input disabled="true"  path="issuerName"></form:input></td>
							<td><label>${ certificateFormBean.issuerName}</label></td>
						</tr>
						<tr>
							<td><form:label path="">Certificate Valid From:  </form:label></td>
							<td><form:input disabled="true"  path="validFrom"></form:input></td>
							<td><label>${ certificateFormBean.validFrom}</label></td>
						</tr>
						<tr>
							<td><form:label path="">Certificate Valid To:  </form:label></td>
							<td><form:input disabled="true"  path="validTo"></form:input></td>
							<td><label>${ certificateFormBean.validTo}</label></td>
							</tr>
						<tr>
							<td><form:label path="">Your Public Key (Scroll Down):  </form:label></td>
							<td><form:textarea disabled="true"  path="userPublicKey"></form:textarea></td>
							<td><label>${ certificateFormBean.userPublicKey}</label></td>
						</tr>
						<tr>
							<td><form:label path="">X509 Certificate Version:  </form:label></td>
							<td><form:input disabled="true"  path="version"></form:input></td>
							<td><label>${ certificateFormBean.version}</label></td>
						</tr>
						<tr>
							<td><form:label path="">Bank CA Signature:  </form:label></td>
							<td><form:input disabled="true"  path="caSignature"></form:input></td>
							<td><label>${ certificateFormBeancaSignature}</label></td>
						</tr>
						 
					</table> --%>
					<div class="control-group" align="left">
						<input type="submit" value="Send To User" name="Send Certificate" class="btn btn-success btn-sm" />
					</div>
				</form:form>
			
		
	</div>		
</body>
</html>
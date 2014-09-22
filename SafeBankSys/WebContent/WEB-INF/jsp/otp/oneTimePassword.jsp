<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Safe Bank Sys</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>

</head>

<body oncontextmenu="disableRightClick()">
	<div class="container">
		<div class="masthead">
       		<div>       			 
	       	 	<h3 class="muted">Safe Bank Sys</h3>
	        </div>
		</div>
		<c:if test="${otpErrorMessage != null}">
			<div style="font-size: 15px; color: red;" align="center">
				<div id="status_message">${otpErrorMessage}</div> 
			</div>					 		
		</c:if>
		<div class="container">
		<h5>Enter The Below Details</h5>
		<form class="form-horizontal" method="POST" id="changePasswordFormBean" commandName="changePasswordFormBean" action="${pageContext.request.contextPath}/OTP/changePassword">			
			<div class="control-group">
				<label class="control-label"  for="middleName">User Name</label>
				<div class="controls">
					<input id="userName" name="userName" placeholder="Enter User Name" class="input-xlarge" value="${changePasswordFormBean.userName}"/>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label"  for="middleName" >Registered Email Id</label>
				<div class="controls">
					<input id="emailId" name="emailId" placeholder="Enter Email ID Registered" class="input-xlarge" value="${changePasswordFormBean.emailId}"/>
				</div>
			</div>
			
			<div class="control-group" align="justify">
				<div class="controls">
       	 			<button class="btn btn-sm btn-primary btn-success">Generate OTP</button>
				</div>
			</div>
		</form>
		</div>
		<script>
			$.validator.addMethod('specialChars', function( value, element ) {
			    var regex = new RegExp("^[a-zA-Z0-9 _.]+$");
			    var key = value;
			
			    if (!regex.test(key)) {
			       return false;
			    }
			    return true;
			}, "please use only alphanumeric or '.' and '_' characters only");
		
		// validate userFormBean form on keyup and submit
			$("#changePasswordFormBean").validate({
				rules: {
					userName: {
						required: true,
						minlength: 2,
						maxlength: 50,
						specialChars:true,
					},
					emailId: {
						required: true,
						email: true,
						maxlength: 50,
					},		
				},
				messages: {
					userName: {
						required: "Please enter a userName"
					},
					email: "Please enter a valid email address",
					minlength: "Your username must consist of at least 2 characters"
				}
			});
		</script>
		
	</div>
</body>
</html>
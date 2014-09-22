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


</head>

<body oncontextmenu="disableRightClick()">
	<div class="container">
		<div class="masthead">
		</div>
		<c:if test="${otpGenerationErrorMessage != null}">
			<div style="font-size: 15px; color: red;" align="center">
				<div id="status_message">${otpGenerationErrorMessage}</div> 
			</div>					 		
		</c:if> 
		<div class="container">
		<h5>Enter The Below Details</h5>
		<form class="form-horizontal" method="POST" id="changePasswordFormBean" commandName="changePasswordFormBean" action="${pageContext.request.contextPath}/OTP/newPassword">
			<div class="control-group">
				<label class="control-label"  for="oneTimePassword">One Time Password</label>
				<div class="controls">
					<input type="text" id="oneTimePassword" name="oneTimePassword" placeholder="Enter OTP that was sent in Email" class="input-xlarge" value="${changePasswordFormBean.oneTimePassword}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"  for="userName">User Name</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" placeholder="Enter User Name" class="input-xlarge" value="${changePasswordFormBean.userName}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"  for="newPassword">New Password</label>
				<div class="controls">
					<input type="password" id="newPassword" name="newPassword" placeholder="Enter New Password" class="input-xlarge" value="${changePasswordFormBean.newPassword}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label"  for="password_confirm">Password (Confirm)</label>
				<div class="controls">
					<input type="password" id="password_confirm" name="password_confirm" placeholder="" class="input-xlarge" value="${userFormBean.password}">
				</div>
			</div>
    
			<div class="control-group" align="justify">
				<div class="controls">
					<button class="btn btn-sm btn-primary btn-success">Reset Password</button>
				</div>
			</div>
		</form>
		</div>
	<!-- <script>
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
				oneTimePassword: {
					required: true,
					number: true,
					specialChars:true,
				},newPassword: {
					required: true,
					minlength: 5,
					maxlength: 100,
					specialChars:true,
				},
				password_confirm: {
					required: true,
					minlength: 5,
					equalTo: "#newPassword"
				},
	
			},
			messages: {
				oneTimePassword:"Please enter OTP",
				userName: {
					required: "Please enter a username",
					minlength: "Your username must consist of at least 2 characters"
				},
				newPassword: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				password_confirm: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
			}
		});
	</script> -->
		
	</div>
</body>
</html>
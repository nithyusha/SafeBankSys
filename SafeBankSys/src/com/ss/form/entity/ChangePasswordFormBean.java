package com.ss.form.entity;


public class ChangePasswordFormBean {
	
	private String oneTimePassword;
	private String newPassword;
	private String emailId;
	private String userName;
	private String otpGenerationStatusMessage;
	
	
	public String getOtpGenerationStatusMessage() {
		return otpGenerationStatusMessage;
	}
	public void setOtpGenerationStatusMessage(String otpGenerationStatusMessage) {
		this.otpGenerationStatusMessage = otpGenerationStatusMessage;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOneTimePassword() {
		return oneTimePassword;
	}
	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}

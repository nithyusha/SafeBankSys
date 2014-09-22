package com.ss.service;



public interface OTPService {
	
	public int generateAndPersistOTP(String userName, String emailId) throws Exception;
	public boolean validateOTPAndPersistNewPwd(String userName, String newPassword, String enteredOTP) throws Exception;
}

package com.ss.db;

import java.util.List;


public interface OTPDAO {
	public int persistOTP(String generatedOTP, String userName, String emailId) throws Exception;
	
	public String getPersistedOTP(String userName) throws Exception;
	
	public int persistNewPassword(String userName,String encodedPW) throws Exception;
	
	/*public UserDTO getUserDTOFor(String destinationUserName, String emailId);
	
	public UserDTO getUserDTOFor(String senderUserName);*/
	
}

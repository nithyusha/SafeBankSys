package com.ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.db.OTPDAOImpl;
import com.ss.entity.SBSUsers;
import com.ss.entity.User;



@Service
public class OTPServiceImpl implements OTPService {
	/*@Autowired
	private OTPDAO otpdao;*/
	@Autowired
	UserManager userManager;

	
	public int generateAndPersistOTP(String userName, String emailId) throws Exception{
		
		OTPDAOImpl otpdao = new OTPDAOImpl();
		OneTimePasswordGenerator oneTimePasswordGenerator = null;
		String generatedOTP = null;
		int createdEntries = 1;
		//int id = userManager.getIdByname(userName);
		SBSUsers s = userManager.getUserByname(userName);
		try
		{
			oneTimePasswordGenerator = new OneTimePasswordGenerator();
			generatedOTP = oneTimePasswordGenerator.generateOTP();
			s.setOneTimePassword(generatedOTP);
			userManager.editUserDetails(s);
			//createdEntries =  otpdao.persistOTP(generatedOTP, id, emailId);
			//if(createdEntries > 0){
				oneTimePasswordGenerator.emailOTP(emailId, generatedOTP);
			//}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return 1;
	}
	
	
	public boolean validateOTPAndPersistNewPwd(String userName, String newPassword, String enteredOTP) throws Exception{
		OTPDAOImpl otpdao = new OTPDAOImpl();
		String persistedOTP = null;
		int createdEntries = 1;
		BCryptPasswordEncoder passwordEncoder = null;
		String encodedPW = null;
		int id = userManager.getIdByname(userName);
		SBSUsers s = userManager.getUserByname(userName);
		try
		{
			persistedOTP = otpdao.getPersistedOTP(userName);
			
			//createdEntries = 0;
			if(persistedOTP.equals(enteredOTP)){
				//passwordEncoder = new  BCryptPasswordEncoder();
				//encodedPW = passwordEncoder.encode(newPassword);
				//createdEntries = otpdao.persistNewPassword(id,newPassword);
				s.setPassword(newPassword);
				userManager.editUserDetails(s);
				User u = userManager.getUser(id);
				u.setPassword(newPassword);
				userManager.editUser(u);
				
				
			}
			
		}
			
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if(createdEntries>0){
			return true;
		}
		else
		{
			return false;
		}
}

}

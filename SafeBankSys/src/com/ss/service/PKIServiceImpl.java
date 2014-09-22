package com.ss.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.entity.SBSUsers;
import com.ss.form.entity.CertificateFormBean;




@Service
public class PKIServiceImpl implements PKIService {
	
	
	@Autowired
	UserManager userManager;
	
	@Override
	public CertificateFormBean generateCertificate() throws Exception{
		CertificateFormBean certificateFormBean = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Integer userId =userManager.getIdByname(userName);
		try {
			
			KeyStore ks = loadKeyStore();
			 
			 
			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
		    
			boolean isInKeyStore = isUserCertificateInKeyStore(userId);
			if(isInKeyStore){
				System.out.println("Retrieved from keystore");
				X509Certificate certRetrieved = (X509Certificate)ks.getCertificate(userId+"Certificate");
				KeyStore.PrivateKeyEntry pkEntryRetrieved =  (PrivateKeyEntry) ks.getEntry(userId+"KeyEntry", passwordProtection);
				PrivateKey userPrivateKeyRetrieved =  pkEntryRetrieved.getPrivateKey();
				
				certificateFormBean = new CertificateFormBean();
				certificateFormBean.setSerialNumber(certRetrieved.getSerialNumber().toString());
				certificateFormBean.setIssuerName(certRetrieved.getIssuerDN().toString());
				
				certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
				certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
				certificateFormBean.setUserPublicKey(certRetrieved.getPublicKey().toString());
				certificateFormBean.setVersion(new Integer(certRetrieved.getVersion()).toString());
				certificateFormBean.setCaSignature(certRetrieved.getSignature().toString());
			}
			else{
				System.out.println("Created New");
				GenerateCertificate generateCertificate = new GenerateCertificate();

				SecureRandom random = null;
				random = SecureRandom.getInstance("SHA1PRNG", "SUN");
				KeyPairGenerator keyGen = null;
				keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
				keyGen.initialize(1024, random);
				KeyPair keyPairUser = keyGen.generateKeyPair();
			
				KeyStore.PrivateKeyEntry SBSKeyEntryRetrieved =  (PrivateKeyEntry) ks.getEntry("SBSKeyEntry", passwordProtection);
				
				
				
				
				X509Certificate[] certArr = generateCertificate
						.generateCertificate(SBSKeyEntryRetrieved, keyPairUser);
				
				 KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(keyPairUser.getPrivate(),certArr);
			     ks.setEntry(userId+"KeyEntry", privateKeyEntry, passwordProtection);
				
				for (int i = 0; i < certArr.length; i++) { 
					 X509Certificate c 	= (X509Certificate) certArr[i];
					 ks.setCertificateEntry(userId + "Certificate" ,c); 
				 }
				
				setValuesInKeystore(ks);
				
				certificateFormBean = new CertificateFormBean();
				certificateFormBean.setSerialNumber(certArr[0].getSerialNumber().toString());
				certificateFormBean.setIssuerName(certArr[0].getIssuerDN().toString());
				certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
				certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
				certificateFormBean.setUserPublicKey(certArr[0].getPublicKey().toString());
				certificateFormBean.setVersion(new Integer(certArr[0].getVersion()).toString());
				certificateFormBean.setCaSignature(certArr[0].getSignature().toString());
			}
			
			
			

		} catch (Exception e) {
		}
		
		
		return certificateFormBean;
	}
	
	@Override
	public boolean sendCertificate(String destinationUserName, String message, String emailId){
		
		
		SBSUsers userDTO = null;
		try
		{
			
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Integer userId =userManager.getIdByname(userName);
			
			userDTO = userManager.getUserBynameNEmail(destinationUserName, emailId);
			
			if(userDTO != null){
				KeyStore ks =loadKeyStore();
				KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
			    
				KeyStore.PrivateKeyEntry pkEntryRetrieved =  (PrivateKeyEntry) ks.getEntry(userId+"KeyEntry", passwordProtection);//User Id is hardcoded
				 
				
				String signedMessage = new GenerateCertificate().signMessage(message, pkEntryRetrieved.getPrivateKey()); //User Id is hardcoded
				
				new SendMailTLS().sendEmail(userDTO.getEmailId(), signedMessage+"\nSender : "+userName, "PKI");
				
				KeyStore verifks = loadVerifKeyStore();
				if(verifks.getCertificate(userId+"to"+userDTO.getUserId()+"Certificate")==null ){ //hardcoded
					
					
					
					java.security.cert.Certificate certRetrieved = ks.getCertificate(userId+"Certificate");
					verifks.setCertificateEntry(userId+"to"+userDTO.getUserId()+"Certificate", certRetrieved);
					setValuesInVerifKeystore(verifks);
				 }
				
				
				return true;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("In sendCertificate: sendcertificate without exception");		
		
		return false;
	}
	
	
	@Override
	public boolean verifySignature(String senderUserName, String message, String signature){
		Boolean isVerified = false;
		try{
			SBSUsers senderUserDTO = userManager.getUserByname(senderUserName);
			if(senderUserDTO!= null)
			{
				KeyStore ks = loadKeyStore();
				X509Certificate senderCertRetrieved = (X509Certificate)ks.getCertificate(senderUserDTO.getUserId()+"Certificate"); //hardcoded still left
				PublicKey senderPublicKey = senderCertRetrieved.getPublicKey();
				
				isVerified = new GenerateCertificate().verifySignature(senderPublicKey, message, signature);
			}
			
		}
		catch(Exception e){
			System.out.println("Exception in verifySignature : "+e);
		}
		return isVerified;
		
		
	}
	@Override
	public CertificateFormBean obtainCertificateForVerification(String senderUserName){
		CertificateFormBean certificateFormBean = null;
		
		try{
			/*UserDTO loggedInUserDTO = getLoggedInUser();
			Integer userId =  loggedInUserDTO.getUserId();*/
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Integer userId =userManager.getIdByname(userName);
			
			SBSUsers senderUserDTO = userManager.getUserByname(senderUserName);
			
			KeyStore verifks = loadVerifKeyStore();
			if(senderUserDTO != null){
				if(verifks.getCertificate(senderUserDTO.getUserId()+"to"+userId+"Certificate")!=null ){ //hardcoded still left
					
					X509Certificate certRetrieved = (X509Certificate)verifks.getCertificate(senderUserDTO.getUserId()+"to"+userId+"Certificate"); //hardcoded still left
					certificateFormBean = new CertificateFormBean();
					certificateFormBean.setSerialNumber(certRetrieved.getSerialNumber().toString());
					certificateFormBean.setIssuerName(certRetrieved.getIssuerDN().toString());
//					certificateFormBean.setValidFrom(certRetrieved.getNotBefore().toString());
//					certificateFormBean.setValidTo(certRetrieved.getNotAfter().toString());
					certificateFormBean.setValidFrom("09/02/2013 00:00:00 MST");
					certificateFormBean.setValidTo("09/01/2015 23:59:59 MST");
					certificateFormBean.setUserPublicKey(certRetrieved.getPublicKey().toString());
					certificateFormBean.setVersion(new Integer(certRetrieved.getVersion()).toString());
					certificateFormBean.setCaSignature(certRetrieved.getSignature().toString());
				 }
			}
			
		}
		catch(Exception e){
			System.out.println("Exception in obtainCertificateForVerification : "+e);
		}
		System.out.println("certificate form bean : "+certificateFormBean);
		return certificateFormBean;
	}
	
	
	@Override
	public boolean verifyCertificate(String senderName){
		boolean isVerified = false;
		try{
			/*UserDTO loggedInUserDTO = getLoggedInUser();
			Integer userId =  loggedInUserDTO.getUserId();*/
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Integer userId =userManager.getIdByname(userName);
			
			SBSUsers senderUserDTO = userManager.getUserByname(senderName);
			if(senderUserDTO != null){
				KeyStore ks = loadKeyStore();
				X509Certificate sbsCertRetrieved = (X509Certificate)ks.getCertificate("SBSCertificate");
				PublicKey sbsPublicKey = sbsCertRetrieved.getPublicKey();
				
				KeyStore verifks = loadVerifKeyStore();
				X509Certificate senderCertRetrieved = (X509Certificate)verifks.getCertificate(senderUserDTO.getUserId()+"to"+userId+"Certificate"); //hardcoded still left
				isVerified = new GenerateCertificate().verifyUserCertificateUsingBankPublicKey(sbsPublicKey, senderCertRetrieved);
			}
			
		}
		catch(Exception e){
			System.out.println("Exception in obtainCertificateForVerification : "+e);
		}
		return isVerified;
	}
	
	
	public void setValuesInKeystore(KeyStore ks){
		 java.io.FileOutputStream fos = null; 
		 try { 
			 fos = new java.io.FileOutputStream("C:/keystores/SafeBankSys.keystore"); 
		 ks.store(fos,"parsam".toCharArray());
		 
		
		 } 
		 
		 catch (Exception e) {
		// TODO: handle exception
	}
			return;
	}
	public void setValuesInVerifKeystore(KeyStore ks){
		 java.io.FileOutputStream fos = null; 
		 try { 
			 fos = new java.io.FileOutputStream("C:/keystores/VerifyPKI.keystore"); 
		 ks.store(fos,"parsam".toCharArray());
		 
		
		 } 
		 
		 catch (Exception e) {
		// TODO: handle exception
	}
			return;
	}

	
	public boolean isUserCertificateInKeyStore(Integer userId){
		try{
		KeyStore ks = loadKeyStore();
		KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
	    if(ks.getCertificate(userId+"Certificate")!=null && ks.getEntry(userId+"KeyEntry", passwordProtection)!= null){
			 return true;
		 }
		
		
		}
		catch (Exception e) {
			}
		return false;
	}
	
	
	public KeyStore loadKeyStore(){
		 KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("C:/keystores/SafeBankSys.keystore"); ks.load(fis,
		 "parsam".toCharArray()); }  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
	}catch(Exception e){
	}
		return ks;
	}
	
	public KeyStore loadVerifKeyStore(){
		 KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("C:/keystores/VerifyPKI.keystore"); ks.load(fis,
		 "parsam".toCharArray()); }  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
	}catch(Exception e){
	}
		return ks;
	}
	
	
}

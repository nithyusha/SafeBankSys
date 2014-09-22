package com.ss.service;

import java.security.PublicKey;

import com.ss.form.entity.CertificateFormBean;





public interface PKIService {
	
	public CertificateFormBean generateCertificate() throws Exception;
	//public CertificateFormBean signCertificate(CertificateFormBean certificateFormBean) throws Exception;
	public boolean sendCertificate(String destinationUserName, String message, String emailId);
	public CertificateFormBean obtainCertificateForVerification(String senderUserName);
	public boolean verifyCertificate(String senderName);
	 boolean verifySignature(String senderUserName, String message, String signature);
}

package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ss.form.entity.CertificateFormBean;
import com.ss.service.PKIService;



@Controller
@SessionAttributes
@RequestMapping(value = "pki")
public class PKIController {

	@Autowired
	private PKIService pkiService;

	@RequestMapping(value = "/generateCertificate", method = RequestMethod.GET)
	public ModelAndView generateCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model)   {
		
		System.out.println("in generate certificate");
		
		try{
			 certificateFormBean = pkiService.generateCertificate();
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		
		model.addAttribute("certificateStatusMessage", "Your genuine certificate provided by SBS");
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/certificate",model);
		
	}
	
	@RequestMapping(value = "/obtainCertificateForVerification", method = RequestMethod.GET)
	public ModelAndView obtainCertificateForVerification(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model)   {
		
		if(certificateFormBean != null){
			if(certificateFormBean.getSenderUserName()!=null){
				System.out.println("in obtainCertificateForVerification");
				String senderUserName = certificateFormBean.getSenderUserName();
				try{
					 certificateFormBean = pkiService.obtainCertificateForVerification(senderUserName);
				}
				catch(Exception e){
					System.out.println("Exception : "+e);
				}
			}
			
		
		
			
		}
		String returnPage = "pki/certificateVerification";
		if(certificateFormBean == null){
			certificateFormBean = new CertificateFormBean();
			model.addAttribute("errorMessage", "The User You Just Mentioned Has Not Sent Any Certificate");
			returnPage = "pki/enterMessageDetails2";
		}
		
		
		
		
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView(returnPage,model);
		
	}
	
	@RequestMapping(value = "/enterMessageDetails", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails1",model);
	}
	
	@RequestMapping(value = "/enterMessageDetail2", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails2(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails2");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails2",model);
	}
/*	
	@RequestMapping(value="/enterMessageDetails", method = RequestMethod.GET)
	public String login(@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception {
		System.out.println("enterMessageDetails");
		return "pki/enterMessageDetails1";
	}*/
	
	
	
	@RequestMapping(value = "/sendCertificate", method = RequestMethod.GET)
	public ModelAndView sendCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model)   {
		boolean emailSent = false;
		System.out.println("in send certificate");
		String destinationUserName = certificateFormBean.getDestinationUserName();
		String emailId = certificateFormBean.getDestinationEmailId();
		String message = certificateFormBean.getMessage(); 
		try{
			 emailSent =  pkiService.sendCertificate(destinationUserName,message,emailId);
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		
		if(emailSent){
			model.addAttribute("certificateStatusMessage", "Certificate Sent To The Desired User. An E-Mail Is Sent To Notify The Desired User");
		}
		else{
			model.addAttribute("errorMessage", "Certificate And E-Mail Could Not Be Sent. You Might Have Entered Incorrect E-Mail-Id/User-Name Combination. Try Again");
			
		}
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails1",model);
		
	}
	
	@RequestMapping(value = "/enterMessageDetails3", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails3(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails3");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails3",model);
	}
	
	@RequestMapping(value = "/verifyCertificate", method = RequestMethod.GET)
	public ModelAndView verifyCertificate(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model)   {
		boolean isVerified = false;
		System.out.println("in verifyCertificate");
		System.out.println("Sender Name  :" +certificateFormBean.getSenderUserName());
		String senderUserName = certificateFormBean.getSenderUserName();
		try{
			certificateFormBean = pkiService.obtainCertificateForVerification(senderUserName);
			isVerified =  pkiService.verifyCertificate(senderUserName);
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
		}
		System.out.println(isVerified);
		if(isVerified){
			model.addAttribute("certificateStatusMessage", "Certificate Of The Sender You Just Mentioned Is A Genuine Certificate. You Can Now Safely Transact With The Sender.");
		}
		else{
			model.addAttribute("errorMessage", "Certificate Not Verified !! Certificate Of The Sender Not Genuine Or You Might Have Entered Incorrect User-Name For The Sender. Try Again.");
		}
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/enterMessageDetails3",model);
		
	}
	@RequestMapping(value = "/verifySignature", method = RequestMethod.GET)
	public ModelAndView decryptMessage(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("verifySignature");
		//pkiService.signCertificate(certificateFormBean);
		String senderUserName = certificateFormBean.getSenderUserName();
		String signature = certificateFormBean.getSignature();
		String message = certificateFormBean.getMessage();
		
		 boolean isVerified = pkiService.verifySignature(senderUserName, message, signature);
		 if(certificateFormBean == null){
				certificateFormBean =  new CertificateFormBean();
			}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("pki/signatureVerification",model);
	}
}
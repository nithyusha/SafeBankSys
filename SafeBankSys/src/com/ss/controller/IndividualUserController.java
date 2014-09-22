package com.ss.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;
import com.ss.exception.ApplicationException;
import com.ss.form.entity.AccountFormDTO;
import com.ss.form.entity.CertificateFormBean;
import com.ss.service.CardDetailsManager;
import com.ss.service.IndividualUsersManager;
import com.ss.service.MerchantManager;
import com.ss.service.PKIService;
import com.ss.service.UserManager;

@Controller
public class IndividualUserController {

	@Autowired
	CardDetailsManager cdm;
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	IndividualUsersManager ium;
	
	@Autowired
	MerchantManager mm;
	
	@RequestMapping(value="/user/getCreditRequestForm", method = RequestMethod.GET)
	public ModelAndView getCreditRequestForm(){
		System.out.println("In getCreditRequestForm");
		 ModelAndView model = new ModelAndView("/user/creditform");
			AccountFormDTO acntForm = new AccountFormDTO();
			model.getModelMap().put("creditForm", acntForm);
			return model;
	}
	
	@RequestMapping(value="/user/credit", method = RequestMethod.POST)
	public ModelAndView credit(@ModelAttribute("creditform") AccountFormDTO account){
		System.out.println("In getCreditRequestForm");
		
		 ModelAndView model = new ModelAndView("/user/creditform");
			AccountFormDTO acntForm = new AccountFormDTO();
			model.getModelMap().put("creditForm", acntForm);
			return model;
	}
	
	
	@RequestMapping(value="/individual/viewTrans", method=RequestMethod.GET)
	public ModelAndView getviewTransaction(Principal principal) {
		
		int userId;
		ModelAndView model = new ModelAndView("/individual/viewAll");
		String currentUser = principal.getName();
		try {
			userId = userManager.getIdByname(currentUser);
			List<CheckingTransactions> list = mm.viewTransaction(userId);
			model.getModelMap().put("transactionList", list);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}

	
	@RequestMapping(value="/individual/transferMoney", method=RequestMethod.GET)
	public ModelAndView goToTransferPage() {
		ModelAndView model = new ModelAndView("/individual/validateCard");
		return model;
	}
	
	@RequestMapping(value="/individual/transferMoney1", method=RequestMethod.POST)
	public ModelAndView validateCard1(@RequestParam("cardNo") long cardNo, @RequestParam("ssv_No") int ssv_No, @RequestParam("expiry") String expiry, @RequestParam("amt") double amt, @RequestParam("accNo") String accNo, Principal principal) {
		ModelAndView model = new ModelAndView("/individual/validate");
		
		int userId;
		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			List<CardDetails> cd = cdm.validateCardDetails1(userId, expiry, ssv_No, cardNo);
			
			boolean x = mm.checkBal(userId, amt);
			System.out.println(x);
			
			int userId1 = mm.checkAccNo(accNo);
			
			
			if(!(cd.size()==0) && !(userId1==0) && !(userId1==userId) && x) {
					mm.makeTransfer(userId, amt, userId1);
					return model;
					//model.getModelMap().put("cardNo", cardNo);
				} else {
					ModelAndView model1 = new ModelAndView("/individual/invalidate");
					return model1;
				}
		} catch (HibernateException e1) {
			e1.printStackTrace();
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}

		return model;
	}


	@RequestMapping(value="/individual/debit", method=RequestMethod.GET)
	public ModelAndView debitAmt() {
		
		ModelAndView model = new ModelAndView("/individual/debit");
		return model;
	}
	
	@RequestMapping(value="/individual/debit1", method=RequestMethod.POST)
	public ModelAndView debitAmt1(@RequestParam("amt") double amt, Principal principal) {
		
		ModelAndView model = new ModelAndView("/individual/individual_user");
		ModelAndView model1 = new ModelAndView("/individual/debit1");
		int userId;
		
		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			boolean x = mm.checkBal(userId, amt);
			if(x)
				mm.debit(amt, userId);
			else
				return model1;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value="/individual/credit", method=RequestMethod.GET)
	public ModelAndView creditAmt(Principal principal) {
	
		ModelAndView model = new ModelAndView("/individual/credit");
		return model;
	}
	
	
	@RequestMapping(value="/individual/credit1", method=RequestMethod.POST)
	public ModelAndView creditAmt1(@RequestParam("amt") double amt, Principal principal) {

		int userId;
		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			mm.credit(amt, userId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("/individual/individual_user");
		return model;
	}

	
	
	/*****************PKI IMPLEMENTATION***************/
	
	@Autowired
	private PKIService pkiService;
	
	
	@RequestMapping(value = "/individual/generateCertificate", method = RequestMethod.GET)
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
		return new ModelAndView("/individual/certificate",model);
		
	}

	
	@RequestMapping(value = "/individual/enterMessageDetails", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("/individual/enterMessageDetails1",model);
	}

	
	@RequestMapping(value = "/individual/sendCertificate", method = RequestMethod.GET)
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
		return new ModelAndView("/individual/enterMessageDetails1",model);
		
	}
	
	
	/********************************************************/
	
	@RequestMapping(value = "/individual/enterMessageDetail2", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails2(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails2");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("/individual/enterMessageDetails2",model);
	}

	
	@RequestMapping(value = "/individual/obtainCertificateForVerification", method = RequestMethod.GET)
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
		String returnPage = "/individual/certificateVerification";
		if(certificateFormBean == null){
			certificateFormBean = new CertificateFormBean();
			model.addAttribute("errorMessage", "The User You Just Mentioned Has Not Sent Any Certificate");
			returnPage = "/individual/enterMessageDetails2";
		}
		
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView(returnPage,model);
	}
	
	
	@RequestMapping(value = "/individual/enterMessageDetails3", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails3(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails3");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("/individual/enterMessageDetails3",model);
	}


	@RequestMapping(value = "/individual/verifyCertificate", method = RequestMethod.GET)
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
			if(certificateFormBean == null){
				certificateFormBean =  new CertificateFormBean();
			}
			model.put("certificateFormBean", certificateFormBean);
			return new ModelAndView("/individual/transferMoney",model);
		}
		else{
			model.addAttribute("errorMessage", "Certificate Not Verified !! Certificate Of The Sender Not Genuine Or You Might Have Entered Incorrect User-Name For The Sender. Try Again.");
			if(certificateFormBean == null){
				certificateFormBean =  new CertificateFormBean();
			}
			model.put("certificateFormBean", certificateFormBean);
			return new ModelAndView("/individual/enterMessageDetails3",model);
		}
		
		
	}

	/*******************************************************/
	
	
	@RequestMapping(value = "/individual/viewAllMerchants", method = RequestMethod.GET)
	public ModelAndView viewAllMerchants() {
		
		ModelAndView model = new ModelAndView("/individual/viewAllMerchants");
		
		List<SBSUsers> list = mm.viewAllMerchants();
		
		model.getModelMap().put("list", list);
		
		return model;
	}
	
	@RequestMapping(value = "/individual/viewAccDetails", method = RequestMethod.GET)
	public ModelAndView viewAccDetails(Principal principal) {
		
		ModelAndView model = new ModelAndView("/individual/viewAccDetails");
	
		int userId;
		
		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			List<CheckingAccDetails> list = mm.viewAccDetails(userId);
			model.getModelMap().put("list", list);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	

	@RequestMapping(value = "/individual/viewCardDetails", method = RequestMethod.GET)
	public ModelAndView viewCardDetails(Principal principal) {
		
		ModelAndView model = new ModelAndView("/individual/viewCardDetails");
	
		int userId;
		
		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			List<CardDetails> list = mm.viewCardDetails(userId);
			model.getModelMap().put("list", list);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return model;
	}





}

package com.ss.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ss.exception.ApplicationException;
import com.ss.form.entity.CertificateFormBean;
import com.ss.service.CardDetailsManager;
import com.ss.service.MerchantManager;
import com.ss.service.PKIService;
import com.ss.service.UserManager;

@Controller
public class MerchantController {

	@Autowired
	MerchantManager mm;

	@Autowired
	CardDetailsManager cdm;
	
	@Autowired
	UserManager userManager;


	@RequestMapping(value="/merchant/validateCard", method=RequestMethod.GET)
	public ModelAndView getSubmitTransaction() {
		ModelAndView model = new ModelAndView("/merchant/cardDetails");
		return model;
	}


	@RequestMapping(value="/card/validate", method=RequestMethod.POST)
	public ModelAndView submitTransaction(Principal principal, @RequestParam("expiry") String expiry, @RequestParam("ssv_No") int ssv_No, @RequestParam("cardNo") long cardNo) throws HibernateException, ApplicationException {

		ModelAndView model = new ModelAndView("/merchant/transferMoney1");

		String currentUser = principal.getName();

		int userId;
		userId = userManager.getIdByname(currentUser);

		int cd = cdm.validateCardDetails(expiry, ssv_No, cardNo);
		
		if(cd!=0 && cd!=userId) {
			model.getModelMap().put("cardNo", cardNo);
			return model;
		} else {
				ModelAndView model1 = new ModelAndView("/merchant/invalidate");
				return model1;
			}
	}


	@RequestMapping(value="/card/transfer", method=RequestMethod.POST)
	public ModelAndView authorizeTransaction(@RequestParam("amt") double amt, @RequestParam("cardNo") long cardNo,Principal principal) {
		
		int userId;
		ModelAndView model = new ModelAndView("/merchant/merchant");
		ModelAndView model1 = new ModelAndView("/merchant/insBal");

		String currentUser = principal.getName();

		try {
			userId = userManager.getIdByname(currentUser);
			boolean x = mm.authorizeTransaction(amt, cardNo, userId);
			
			if(!x)
				return model1;
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Card Authorization failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Card Authorization failed");
			e.printStackTrace();
		}
		return model;
	}


	@RequestMapping(value="/merchant/viewTrans", method=RequestMethod.GET)
	public ModelAndView getviewTransaction(Principal principal) {
		
		int userId;
		ModelAndView model = new ModelAndView("/merchant/viewAll");
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

	
/*
	@RequestMapping(value="/merchant/viewTrans", method=RequestMethod.POST)
	public ModelAndView viewTransaction(Principal principal) {
		
		int userId;
		String currentUser = principal.getName();
		ModelAndView model = new ModelAndView("/merchant/viewAll");
		try {
			userId = userManager.getIdByname(currentUser);
			List<CheckingTransactions> transactionlist = mm.viewTransaction(userId);
			model.getModelMap().put("transactionList", transactionlist);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
*/
	

	@RequestMapping(value="/merchant/transferMoney", method=RequestMethod.GET)
	public ModelAndView goToTransferPage() {
		ModelAndView model = new ModelAndView("/merchant/validateCard");
		return model;
	}
	
	@RequestMapping(value="/merchant/transferMoney1", method=RequestMethod.POST)
	public ModelAndView validateCard1(@RequestParam("cardNo") long cardNo, @RequestParam("ssv_No") int ssv_No, @RequestParam("expiry") String expiry, @RequestParam("amt") double amt, @RequestParam("accNo") String accNo, Principal principal) {
		ModelAndView model = new ModelAndView("/merchant/validate");
		
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
					ModelAndView model1 = new ModelAndView("/merchant/invalidate");
					return model1;
				}
		} catch (HibernateException e1) {
			e1.printStackTrace();
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}

		return model;
	}


	@RequestMapping(value="/merchant/debit", method=RequestMethod.GET)
	public ModelAndView debitAmt() {
		
		ModelAndView model = new ModelAndView("/merchant/debit");
		return model;
	}
	
	@RequestMapping(value="/merchant/debit1", method=RequestMethod.POST)
	public ModelAndView debitAmt1(@RequestParam("amt") double amt, Principal principal) {
		
		ModelAndView model = new ModelAndView("/merchant/merchant");
		ModelAndView model1 = new ModelAndView("/merchant/debit1");
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
	
	
	@RequestMapping(value="/merchant/credit", method=RequestMethod.GET)
	public ModelAndView creditAmt(Principal principal) {
	
		ModelAndView model = new ModelAndView("/merchant/credit");
		return model;
	}
	
	
	@RequestMapping(value="/merchant/credit1", method=RequestMethod.POST)
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

		ModelAndView model = new ModelAndView("/merchant/merchant");
		return model;
	}
	
	

	/************************PKI IMPLEMENTATION***************************/
	
	@Autowired
	private PKIService pkiService;

	
	@RequestMapping(value = "/merchant/enterMessageDetail2", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails2(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails2");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("/merchant/enterMessageDetails2",model);
	}

	
	@RequestMapping(value = "/merchant/obtainCertificateForVerification", method = RequestMethod.GET)
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
		String returnPage = "/merchant/certificateVerification";
		if(certificateFormBean == null){
			certificateFormBean = new CertificateFormBean();
			model.addAttribute("errorMessage", "The User You Just Mentioned Has Not Sent Any Certificate");
			returnPage = "/merchant/enterMessageDetails2";
		}
		
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView(returnPage,model);
	}
	
	
	@RequestMapping(value = "/merchant/enterMessageDetails3", method = RequestMethod.GET)
	public ModelAndView enterMessageDetails3(
			@ModelAttribute("certificateFormBean") CertificateFormBean certificateFormBean,
			BindingResult result, ModelMap model) throws Exception  {
		System.out.println("enterMessageDetails3");
		//pkiService.signCertificate(certificateFormBean);
		if(certificateFormBean == null){
			certificateFormBean =  new CertificateFormBean();
		}
		model.put("certificateFormBean", certificateFormBean);
		return new ModelAndView("/merchant/enterMessageDetails3",model);
	}


	@RequestMapping(value = "/merchant/verifyCertificate", method = RequestMethod.GET)
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
			return new ModelAndView("/merchant/validateCard",model);
		}
		else{
			model.addAttribute("errorMessage", "Certificate Not Verified !! Certificate Of The Sender Not Genuine Or You Might Have Entered Incorrect User-Name For The Sender. Try Again.");
			if(certificateFormBean == null){
				certificateFormBean =  new CertificateFormBean();
			}
			model.put("certificateFormBean", certificateFormBean);
			return new ModelAndView("/merchant/enterMessageDetails3",model);
		}
		
		
	}
	
	
	
	/***********************************************************/
	
	
	@RequestMapping(value = "/merchant/viewAccDetails", method = RequestMethod.GET)
	public ModelAndView viewAccDetails(Principal principal) {
		
		ModelAndView model = new ModelAndView("/merchant/viewAccDetails");
	
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
	

	@RequestMapping(value = "/merchant/viewCardDetails", method = RequestMethod.GET)
	public ModelAndView viewCardDetails(Principal principal) {
		
		ModelAndView model = new ModelAndView("/merchant/viewCardDetails");
	
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
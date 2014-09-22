package com.ss.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.Aspects.annotations.AccessPolicies;
import com.ss.Aspects.annotations.ElementAccessPolicy;
import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.TMTransaction;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.MerchantManager;
import com.ss.service.TranscationMonitoringManger;
import com.ss.service.UserManager;
@Controller
public class TransactionMonitoringController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionMonitoringController.class);
	@Autowired
	TranscationMonitoringManger tmTransactionManager;
	
	@Autowired
	MerchantManager merchantManager;
	@Autowired
	UserManager userManager;
	
	//private Logger logger = 
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/getCreateTransForm", method = RequestMethod.GET)
	public ModelAndView getCreateTransactionForm() throws AccessException{
		 ModelAndView model = new ModelAndView("TM/createTrans_TM");
			TMTransaction tm = new TMTransaction();
			model.getModelMap().put("createTransTMForm", tm);
			return model;
	}

	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/createTransaction", method = RequestMethod.POST)
	public ModelAndView createTMTransaction(@ModelAttribute("createTransTMForm") TMTransaction tmTrans) throws AccessException{
		ModelAndView model = new ModelAndView("TM/RegEmp_TM");
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			Date date = new Date();
			tmTrans.setCreatedOn(date);
			tmTrans.setCreatedBy(currentUser);
			tmTrans.setLastModifyOn(date);
			tmTrans.setLastModifyBy(currentUser);
			if(tmTrans.getAmount() > 1000){
			tmTrans.setStatus("PENDING_BY_MANAGER");
			}
			else {
				tmTrans.setStatus("COMPLETED");
			if(tmTrans.getType().equalsIgnoreCase("CREDIT")){
			CheckingAccDetails card	= merchantManager.credit(tmTrans.getAmount(), tmTrans.getAccNo());
			if(card != null){
				int userId = card.getUserId();
				merchantManager.credit(tmTrans.getAmount(),userId );
			}
			}else if(tmTrans.getType().equalsIgnoreCase("DEBIT")){
				CheckingAccDetails card	= merchantManager.credit(tmTrans.getAmount(), tmTrans.getAccNo());
				if(card!=null){
				int userId = card.getUserId();
				merchantManager.debit(tmTrans.getAmount(), userId);
				}
			 }
			}
			tmTransactionManager.createTransaction(tmTrans);
			model.getModelMap().put("successmsg", " Transaction Created Succesfully");
		
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/getViewTransForm", method = RequestMethod.GET)
	public ModelAndView getViewTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("TM/viewTrans_TM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewTMTransaction(@RequestParam("tid") int tId) throws AccessException{
		ModelAndView model = new ModelAndView("TM/viewTrans_TM");
		try {
			
			TMTransaction t =  tmTransactionManager.getTransactionById(tId);
			if(t == null){
				model.getModelMap().put("errormsg", "Transcation not found with id:"+tId);
			}
			else
			model.getModelMap().put("tmTransaction", t);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_TM","ROLE_DEPT_MGR_TM","ROLE_CORP_MGR_TM"},deptId = 5)})
	@RequestMapping(value="/TM/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView getAllTransactions() throws AccessException{
		System.out.println("In getAllTransactions");
		 ModelAndView model = new ModelAndView("TM/viewAllTrans_TM");
			try {
				List<TMTransaction> list = tmTransactionManager.getAllTransactions();
				if(list == null){
					model.getModelMap().put("errormsg", "No results Found");
				}
				model.getModelMap().put("transactionList", list);
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "No results Found");
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "No results Found");
				e.printStackTrace();
			}
			return model;
	}
	
	
	@RequestMapping(value="/TM/getQueryModifyTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryModifyTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("TM/getQueryModifyTrans_TM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/getModifyTransForm", method = RequestMethod.POST)
	public ModelAndView getModifyTransactionForm(@RequestParam("tid") int tId) throws AccessException {
		 ModelAndView model = new ModelAndView("TM/modifyTrans_TM");
			try {
				TMTransaction s =  tmTransactionManager.getTransactionById(tId);
				if(s== null){
					model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				}
				else{
				model.getModelMap().put("modifyTransTMForm", s);
				}
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/getQueryDeleteTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryDeleteTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("TM/getQueryDeleteTrans_TM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/deleteTrans", method = RequestMethod.POST)
	public ModelAndView getDeleteTransaction(@RequestParam("tid") int tId) throws AccessException {
		System.out.println("In getModifyTransactionForm");
		 ModelAndView model = new ModelAndView("TM/getQueryDeleteTrans_TM");
			
			try {
				if(tmTransactionManager.getTransactionById(tId) != null){
				tmTransactionManager.deleteTransaction(tId);
				model.getModelMap().put("successmsg", "Transaction Deleted with transaction id:"+tId);
				}
				else{
					model.getModelMap().put("errormsg", "Transaction not found");
				}
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "Transaction Failed");
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "Transaction Failed");
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_TM",deptId = 5)})
	@RequestMapping(value="/TM/modifyTransaction", method = RequestMethod.POST)
	public ModelAndView modifyITTransaction(@ModelAttribute("modifyTransTMForm") TMTransaction tmTrans) throws AccessException{
		ModelAndView model = new ModelAndView("TM/RegEmp_TM");
		
		
		try {
			TMTransaction t =  tmTransactionManager.getTransactionById(tmTrans.gettId());
			
			if(t!=null){
			
			
			
			t.setLastModifyOn(new Date());
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			t.setLastModifyBy(currentUser);
			t.setLastModifyOn(new Date());
			
			
			if(tmTrans.getAmount() > 1000){
				tmTrans.setStatus("PENDING_BY_MANAGER");
				}
				else {
					tmTrans.setStatus("COMPLETED");
				if(tmTrans.getType().equalsIgnoreCase("CREDIT")){
				CheckingAccDetails card	= merchantManager.credit(tmTrans.getAmount(), tmTrans.getAccNo());
				if(card != null){
					int userId = card.getUserId();
					merchantManager.credit(tmTrans.getAmount(),userId );
				}
				}else if(tmTrans.getType().equalsIgnoreCase("DEBIT")){
					CheckingAccDetails card	= merchantManager.credit(tmTrans.getAmount(), tmTrans.getAccNo());
					if(card!=null){
					int userId = card.getUserId();
					merchantManager.debit(tmTrans.getAmount(), userId);
					}
				 }
				}
			
			
			tmTransactionManager.modifyTransaction(t);
			t.setParentTId(tmTrans.gettId());
			t.settId(null);
			t.setAccNo((tmTrans.getAccNo()));
			t.setAmount(tmTrans.getAmount());
			t.setType(tmTrans.getType());
			t.setCreatedOn(new Date());
			
			tmTransactionManager.createTransaction(t);
			model.getModelMap().put("message", "Transaction modified with id:"+tmTrans.gettId());
			}
			else{
				model.getModelMap().put("errormsg", "Transaction failed");
			}
				
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction failed");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_DEPT_MGR_TM",deptId = 5)})
	@RequestMapping(value="/TM/authorizeTransByDMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByDManager(@PathVariable("tId") int tID) throws AccessException{
		
		ModelAndView model = new ModelAndView("TM/deptmanager_TM");
		
		
		try {
			TMTransaction t =  tmTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			if(t.getAmount() > 5000){
				t.setApprover1(currentUser);
				t.setStatus("PENDING_BY_CORPMGR");
				}
				else {
					t.setStatus("COMPLETED");
					
				if(t.getType().equalsIgnoreCase("CREDIT")){
					CheckingAccDetails card	= merchantManager.credit(t.getAmount(), t.getAccNo());
					if(card != null){
						int userId = card.getUserId();
						merchantManager.credit(t.getAmount(),userId );
					}
				}else if(t.getType().equalsIgnoreCase("DEBIT")){
					CheckingAccDetails card	= merchantManager.credit(t.getAmount(), t.getAccNo());
					if(card != null){
						int userId = card.getUserId();
						merchantManager.debit(t.getAmount(),userId );
					}
				}
				}
			/*if(t.getType().equalsIgnoreCase("DEBIT")){
				merchantManager.debit(t.getAmount(), t.getAccNo());
			}else if(t.getType().equalsIgnoreCase("CREDIT")){
				merchantManager.credit(t.getAmount(), t.getAccNo());
			}*/
			/*t.setApprover1(currentUser);
			t.setStatus("PENDING_BY_CORPMGR");*/
			tmTransactionManager.modifyTransaction(t);
			
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_CORP_MGR_TM",deptId = 5)})
	@RequestMapping(value="/TM/authorizeTransByCorpMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByCorpManager(@PathVariable("tId") int tID) throws AccessException{
		ModelAndView model = new ModelAndView("TM/corpmanager_TM");
			try {
			TMTransaction s =  tmTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover2(currentUser);
			s.setStatus("COMPLETED");
			if(s.getType().equalsIgnoreCase("CREDIT")){
				CheckingAccDetails card	= merchantManager.credit(s.getAmount(), s.getAccNo());
				if(card != null){
					int userId = card.getUserId();
					merchantManager.debit(s.getAmount(),userId );
				}
			}else if(s.getType().equalsIgnoreCase("DEBIT")){
				CheckingAccDetails card	= merchantManager.credit(s.getAmount(), s.getAccNo());
				if(card != null){
					int userId = card.getUserId();
					merchantManager.debit(s.getAmount(),userId );
				}
			}
			tmTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_DEPT_MGR_TM",deptId = 5)})
	@RequestMapping(value="/TM/viewAllPendingTransForDeptMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForDeptMgr() throws AccessException{
		System.out.println("In getAllPendingTransForDeptMgr");
		 ModelAndView model = new ModelAndView("TM/authorizeTransByMgr");
			try {
				List<TMTransaction> list = tmTransactionManager.getAuthTransByDeptMgr();
				if(list == null){
					model.getModelMap().put("errormsg", "No Pending transactions ");
				}else
				model.getModelMap().put("transactionList", list);
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "No Results found ");
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "No Results found ");
				e.printStackTrace();
			}
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_CORP_MGR_TM",deptId = 5)})
	@RequestMapping(value="/TM/viewAllPendingTransForCorpMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForCorpMgr() throws AccessException{
		System.out.println("In getAllPendingTransForCorpMgr");
		 ModelAndView model = new ModelAndView("TM/authorizeTransByCMgr");
			try {
				List<TMTransaction> list = tmTransactionManager.getAuthTransByCorpMgr();
				if(list == null){
					model.getModelMap().put("errormsg", "No pending transactions");
				}else
				model.getModelMap().put("transactionList", list);
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "No Results");
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "No Resulst");
				e.printStackTrace();
			}
			return model;
	}
	

}

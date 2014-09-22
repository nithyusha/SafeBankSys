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
import com.ss.entity.CMTransaction;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.CMTransactionManager;

@Controller
public class CMTransactionController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(CMTransactionController.class);
	@Autowired
	CMTransactionManager CMTransactionManager;
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/getCreateTransForm", method = RequestMethod.GET)
	public ModelAndView getCreateTransactionForm() throws AccessException{
		 ModelAndView model = new ModelAndView("CM/createTrans_CM");
			CMTransaction tm = new CMTransaction();
			model.getModelMap().put("createTransCMForm", tm);
			return model;
	}

	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/createTransaction", method = RequestMethod.POST)
	public ModelAndView createCMTransaction(@ModelAttribute("createTransCMForm") CMTransaction CMTrans) throws AccessException{
		ModelAndView model = new ModelAndView("CM/RegEmp_CM");
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Date date = new Date();
			CMTrans.setCreationDate(date);
			CMTrans.setStatus("PENDING_BY_MANAGER");
			CMTrans.setCreatedBy(currentUser);
			CMTrans.setLastModifyOn(date);
			CMTrans.setLastModifyBy(currentUser);
			CMTransactionManager.createTransaction(CMTrans);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/getViewTransForm", method = RequestMethod.GET)
	public ModelAndView getViewTransactionForm() throws AccessException {
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("CM/viewTrans_CM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewCMTransaction(@RequestParam("tid") int tId) throws AccessException{
		ModelAndView model = new ModelAndView("CM/viewTrans_CM");
		try {
			
			CMTransaction s =  CMTransactionManager.getTransactionById(tId);
			if(s == null){
				model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
			}
			else
			model.getModelMap().put("CMTransaction", s);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM","ROLE_CORP_MGR_CM","ROLE_DEPT_MGR_CM"},deptId = 4)})
	@RequestMapping(value="/CM/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView getAllTransactions() throws AccessException{
		System.out.println("In getAllTransactions");
		 ModelAndView model = new ModelAndView("CM/viewAllTrans_CM");
			try {
				List<CMTransaction> list = CMTransactionManager.getAllTransactions();
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
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/getQueryModifyTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryModifyTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("CM/getQueryModifyTrans_CM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/getModifyTransForm", method = RequestMethod.POST)
	public ModelAndView getModifyTransactionForm(@RequestParam("tid") int tId) throws AccessException{
		 ModelAndView model = new ModelAndView("CM/modifyTrans_CM");
			try {
				CMTransaction s =  CMTransactionManager.getTransactionById(tId);
				if(s== null){
					model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				}
				else{
				model.getModelMap().put("modifyTransCMForm", s);
				}
			} catch (HibernateException e) {
				model.getModelMap().put("errormsg", "Transcation not found with id:"+tId);
				e.printStackTrace();
			} catch (ApplicationException e) {
				model.getModelMap().put("errormsg", "Transcation not found with id:"+tId);
				e.printStackTrace();
			}
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/getQueryDeleteTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryDeleteTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("CM/getQueryDeleteTrans_CM");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/deleteTrans", method = RequestMethod.POST)
	public ModelAndView getDeleteTransaction(@RequestParam("tid") int tId) {
		System.out.println("In getModifyTransactionForm");
		 ModelAndView model = new ModelAndView("CM/getQueryDeleteTrans_CM");
			
			try {
				
				CMTransactionManager.deleteTransaction(tId);
				model.getModelMap().put("message", "Transaction Deleted with transaction id:"+tId);
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_CM"},deptId = 4)})
	@RequestMapping(value="/CM/modifyTransaction", method = RequestMethod.POST)
	public ModelAndView modifyCMTransaction(@ModelAttribute("modifyTransCMForm") CMTransaction CMTrans) throws AccessException{
		System.out.println("In modifyCMTransaction");
		ModelAndView model = new ModelAndView("CM/RegEmp_CM");
		
		
		try {
			CMTransaction s =  CMTransactionManager.getTransactionById(CMTrans.gettId());
			
			s.setLastModifyOn(new Date());
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setLastModifyBy(currentUser);
			s.setLastModifyOn(new Date());
			CMTransactionManager.modifyTransaction(s);
			s.setParentTId(CMTrans.gettId());
			s.settId(null);
			s.setTranscName(CMTrans.getTranscName());
			s.setTransDesc(CMTrans.getTransDesc());
			s.setCreationDate(new Date());
			
			CMTransactionManager.createTransaction(s);
			model.getModelMap().put("message", "Transaction modified with id:"+CMTrans.gettId());
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_CM"},deptId = 4)})
	@RequestMapping(value="/CM/authorizeTransByDMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByDManager(@PathVariable("tId") int tID) throws AccessException{
		
		ModelAndView model = new ModelAndView("CM/deptmanager_CM");
		
		
		try {
			CMTransaction s =  CMTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover1(currentUser);
			s.setStatus("PENDING_BY_CORPMGR");
			CMTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_CM"},deptId = 4)})
	@RequestMapping(value="/CM/authorizeTransByCorpMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByCorpManager(@PathVariable("tId") int tID) throws AccessException{
		System.out.println("In modifyCMTransaction");
		ModelAndView model = new ModelAndView("CM/corpmanager_CM");
			try {
			CMTransaction s =  CMTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover2(currentUser);
			s.setStatus("COMPLETED");
			CMTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_CM"},deptId = 4)})
	@RequestMapping(value="/CM/viewAllPendingTransForDeptMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForDeptMgr() throws AccessException{
		System.out.println("In getAllPendingTransForDeptMgr");
		 ModelAndView model = new ModelAndView("CM/authorizeTransByMgr");
			try {
				List<CMTransaction> list = CMTransactionManager.getAuthTransByDeptMgr();
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
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_CM"},deptId =4)})
	@RequestMapping(value="/CM/viewAllPendingTransForCorpMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForCorpMgr() throws AccessException{
		System.out.println("In getAllPendingTransForCorpMgr");
		 ModelAndView model = new ModelAndView("CM/authorizeTransByCMgr");
			try {
				List<CMTransaction> list = CMTransactionManager.getAuthTransByCorpMgr();
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

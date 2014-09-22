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
import com.ss.entity.HRTransaction;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.HRTransactionManager;

@Controller
public class HRTransactionController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(HRTransactionController.class);
	@Autowired
	HRTransactionManager HRTransactionManager;
	
	//private Logger logger = 
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/getCreateTransForm", method = RequestMethod.GET)
	public ModelAndView getCreateTransactionForm() throws AccessException {
		 ModelAndView model = new ModelAndView("HR/createTrans_HR");
			HRTransaction tm = new HRTransaction();
			model.getModelMap().put("createTransHRForm", tm);
			return model;
	}

	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/createTransaction", method = RequestMethod.POST)
	public ModelAndView createHRTransaction(@ModelAttribute("createTransHRForm") HRTransaction HRTrans) throws AccessException{
		ModelAndView model = new ModelAndView("HR/RegEmp_HR");
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Date date = new Date();
			HRTrans.setCreationDate(date);
			HRTrans.setStatus("PENDING_BY_MANAGER");
			HRTrans.setCreatedBy(currentUser);
			HRTrans.setLastModifyOn(date);
			HRTrans.setLastModifyBy(currentUser);
			HRTransactionManager.createTransaction(HRTrans);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/getViewTransForm", method = RequestMethod.GET)
	public ModelAndView getViewTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("HR/viewTrans_HR");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewHRTransaction(@RequestParam("tid") int tId) throws AccessException{
		ModelAndView model = new ModelAndView("HR/viewTrans_HR");
		try {
			
			HRTransaction s =  HRTransactionManager.getTransactionById(tId);
			if(s == null){
				model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
			}
			else
			model.getModelMap().put("HRTransaction", s);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR","ROLE_CORP_MGR_HR","ROLE_DEPT_MGR_HR"},deptId = 2)})
	@RequestMapping(value="/HR/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView getAllTransactions() throws AccessException {
		System.out.println("In getAllTransactions");
		 ModelAndView model = new ModelAndView("HR/viewAllTrans_HR");
			try {
				List<HRTransaction> list = HRTransactionManager.getAllTransactions();
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
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/getQueryModifyTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryModifyTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("HR/getQueryModifyTrans_HR");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/getModifyTransForm", method = RequestMethod.POST)
	public ModelAndView getModifyTransactionForm(@RequestParam("tid") int tId) throws AccessException {
		 ModelAndView model = new ModelAndView("HR/modifyTrans_HR");
			try {
				HRTransaction s =  HRTransactionManager.getTransactionById(tId);
				if(s== null){
					model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				}
				else{
				model.getModelMap().put("modifyTransHRForm", s);
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
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/getQueryDeleteTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryDeleteTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("HR/getQueryDeleteTrans_HR");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/deleteTrans", method = RequestMethod.POST)
	public ModelAndView getDeleteTransaction(@RequestParam("tid") int tId) throws AccessException {
		System.out.println("In getModifyTransactionForm");
		 ModelAndView model = new ModelAndView("HR/getQueryDeleteTrans_HR");
			
			try {
				
				HRTransactionManager.deleteTransaction(tId);
				model.getModelMap().put("message", "Transaction Deleted with transaction id:"+tId);
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_HR"},deptId = 2)})
	@RequestMapping(value="/HR/modifyTransaction", method = RequestMethod.POST)
	public ModelAndView modifyHRTransaction(@ModelAttribute("modifyTransHRForm") HRTransaction HRTrans) throws AccessException{
		System.out.println("In modifyHRTransaction");
		ModelAndView model = new ModelAndView("HR/RegEmp_HR");
		
		
		try {
			HRTransaction s =  HRTransactionManager.getTransactionById(HRTrans.gettId());
			
			s.setLastModifyOn(new Date());
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setLastModifyBy(currentUser);
			s.setLastModifyOn(new Date());
			HRTransactionManager.modifyTransaction(s);
			s.setParentTId(HRTrans.gettId());
			s.settId(null);
			s.setTranscName(HRTrans.getTranscName());
			s.setTransDesc(HRTrans.getTransDesc());
			s.setCreationDate(new Date());
			
			HRTransactionManager.createTransaction(s);
			model.getModelMap().put("message", "Transaction modified with id:"+HRTrans.gettId());
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_HR"},deptId = 2)})
	@RequestMapping(value="/HR/authorizeTransByDMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByDManager(@PathVariable("tId") int tID) throws AccessException{
		
		ModelAndView model = new ModelAndView("HR/deptmanager_HR");
		
		
		try {
			HRTransaction s =  HRTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover1(currentUser);
			s.setStatus("PENDING_BY_CORPMGR");
			HRTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_HR"},deptId = 2)})
	@RequestMapping(value="/HR/authorizeTransByCorpMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByCorpManager(@PathVariable("tId") int tID) throws AccessException{
		System.out.println("In modifyHRTransaction");
		ModelAndView model = new ModelAndView("HR/corpmanager_HR");
			try {
			HRTransaction s =  HRTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover2(currentUser);
			s.setStatus("COMPLETED");
			HRTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_HR"},deptId = 2)})
	@RequestMapping(value="/HR/viewAllPendingTransForDeptMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForDeptMgr() throws AccessException{
		System.out.println("In getAllPendingTransForDeptMgr");
		 ModelAndView model = new ModelAndView("HR/authorizeTransByMgr");
			try {
				List<HRTransaction> list = HRTransactionManager.getAuthTransByDeptMgr();
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
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_HR"},deptId = 2)})
	@RequestMapping(value="/HR/viewAllPendingTransForCorpMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForCorpMgr() throws AccessException{
		System.out.println("In getAllPendingTransForCorpMgr");
		 ModelAndView model = new ModelAndView("HR/authorizeTransByCMgr");
			try {
				List<HRTransaction> list = HRTransactionManager.getAuthTransByCorpMgr();
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

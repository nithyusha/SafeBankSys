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
import com.ss.entity.SalesTransaction;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.SalesTransactionManager;

@Controller
public class SalesTransactionController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(SalesTransactionController.class);
	@Autowired
	SalesTransactionManager salesTransactionManager;
	
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/getCreateTransForm", method = RequestMethod.GET)
	public ModelAndView getCreateTransactionForm() throws AccessException{
		 ModelAndView model = new ModelAndView("sales/createTrans_sales");
			SalesTransaction tm = new SalesTransaction();
			model.getModelMap().put("createTransSalesForm", tm);
			return model;
	}

	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/createTransaction", method = RequestMethod.POST)
	public ModelAndView createSalesTransaction(@ModelAttribute("createTransSalesForm") SalesTransaction salesTrans){
		ModelAndView model = new ModelAndView("sales/RegEmp_sales");
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Date date = new Date();
			salesTrans.setCreationDate(date);
			salesTrans.setStatus("PENDING_BY_MANAGER");
			salesTrans.setCreatedBy(currentUser);
			salesTrans.setLastModifyOn(date);
			salesTrans.setLastModifyBy(currentUser);
			salesTransactionManager.createTransaction(salesTrans);
			model.getModelMap().put("successmsg", "Transaction Created ");
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/getViewTransForm", method = RequestMethod.GET)
	public ModelAndView getViewTransactionForm() {
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("sales/viewTrans_sales");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewSalesTransaction(@RequestParam("tid") int tId){
		ModelAndView model = new ModelAndView("sales/viewTrans_sales");
		try {
			
			SalesTransaction s =  salesTransactionManager.getTransactionById(tId);
			if(s == null){
				model.getModelMap().put("errormsg", "Transcation not found with id:"+tId);
			}
			else
			model.getModelMap().put("salesTransaction", s);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES","ROLE_DEPT_MGR_SALES","ROLE_CORP_MGR_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView getAllTransactions() {
		System.out.println("In getAllTransactions");
		 ModelAndView model = new ModelAndView("sales/viewAllTrans_sales");
			try {
				List<SalesTransaction> list = salesTransactionManager.getAllTransactions();
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
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/getQueryModifyTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryModifyTransactionForm() {
		 ModelAndView model = new ModelAndView("sales/getQueryModifyTrans_sales");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/getModifyTransForm", method = RequestMethod.POST)
	public ModelAndView getModifyTransactionForm(@RequestParam("tid") int tId) {
		 ModelAndView model = new ModelAndView("sales/modifyTrans_sales");
			try {
				SalesTransaction s =  salesTransactionManager.getTransactionById(tId);
				if(s== null){
					model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				}
				else{
				model.getModelMap().put("modifyTransSalesForm", s);
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
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/getQueryDeleteTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryDeleteTransactionForm() {
		 ModelAndView model = new ModelAndView("sales/getQueryDeleteTrans_sales");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/deleteTrans", method = RequestMethod.POST)
	public ModelAndView getDeleteTransaction(@RequestParam("tid") int tId) {
		 ModelAndView model = new ModelAndView("sales/getQueryDeleteTrans_sales");
			
			try {
				
				salesTransactionManager.deleteTransaction(tId);
				model.getModelMap().put("successmsg", "Transaction Deleted with transaction id:"+tId);
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_REG_EMP_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/modifyTransaction", method = RequestMethod.POST)
	public ModelAndView modifySalesTransaction(@ModelAttribute("modifyTransSalesForm") SalesTransaction salesTrans){
		System.out.println("In modifySalesTransaction");
		ModelAndView model = new ModelAndView("sales/RegEmp_sales");
		
		
		try {
			SalesTransaction s =  salesTransactionManager.getTransactionById(salesTrans.gettId());
			
			s.setLastModifyOn(new Date());
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setLastModifyBy(currentUser);
			s.setLastModifyOn(new Date());
			salesTransactionManager.modifyTransaction(s);
			s.setParentTId(salesTrans.gettId());
			s.settId(null);
			s.setTranscName(salesTrans.getTranscName());
			s.setTransDesc(salesTrans.getTransDesc());
			s.setCreationDate(new Date());
			
			salesTransactionManager.createTransaction(s);
			model.getModelMap().put("successmsg", "Transaction modified with id:"+salesTrans.gettId());
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/authorizeTransByDMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByDManager(@PathVariable("tId") int tID){
		
		ModelAndView model = new ModelAndView("sales/deptmanager_sales");
		
		
		try {
			SalesTransaction s =  salesTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover1(currentUser);
			s.setStatus("PENDING_BY_CORPMGR");
			salesTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/authorizeTransByCorpMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByCorpManager(@PathVariable("tId") int tID){
		System.out.println("In modifySalesTransaction");
		ModelAndView model = new ModelAndView("sales/corpmanager_sales");
			try {
			SalesTransaction s =  salesTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover2(currentUser);
			s.setStatus("COMPLETED");
			salesTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_DEPT_MGR_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/viewAllPendingTransForDeptMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForDeptMgr() {
		System.out.println("In getAllPendingTransForDeptMgr");
		 ModelAndView model = new ModelAndView("sales/authorizeTransByMgr");
			try {
				List<SalesTransaction> list = salesTransactionManager.getAuthTransByDeptMgr();
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
	@AccessPolicies({@ElementAccessPolicy(role={"ROLE_CORP_MGR_SALES"},deptId = 1)})
	@RequestMapping(value="/sales/viewAllPendingTransForCorpMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForCorpMgr() {
		System.out.println("In getAllPendingTransForCorpMgr");
		 ModelAndView model = new ModelAndView("sales/authorizeTransByCMgr");
			try {
				List<SalesTransaction> list = salesTransactionManager.getAuthTransByCorpMgr();
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

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
import com.ss.entity.ItTransaction;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.ItTransactionManager;

@Controller
public class ItTransactionController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(ItTransactionController.class);
	@Autowired
	ItTransactionManager ItTransactionManager;
	
	//private Logger logger = 
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/getCreateTransForm", method = RequestMethod.GET)
	public ModelAndView getCreateTransactionForm() throws AccessException {
		 ModelAndView model = new ModelAndView("It/createTrans_It");
			ItTransaction tm = new ItTransaction();
			model.getModelMap().put("createTransItForm", tm);
			return model;
	}

	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/createTransaction", method = RequestMethod.POST)
	public ModelAndView createItTransaction(@ModelAttribute("createTransItForm") ItTransaction ItTrans) throws AccessException{
		ModelAndView model = new ModelAndView("It/RegEmp_It");
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Date date = new Date();
			ItTrans.setCreationDate(date);
			ItTrans.setStatus("PENDING_BY_MANAGER");
			ItTrans.setCreatedBy(currentUser);
			ItTrans.setLastModifyOn(date);
			ItTrans.setLastModifyBy(currentUser);
			ItTransactionManager.createTransaction(ItTrans);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Oops !! Transaction Failed");
			e.printStackTrace();
		}
		return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/getViewTransForm", method = RequestMethod.GET)
	public ModelAndView getViewTransactionForm()throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("It/viewTrans_It");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewItTransaction(@RequestParam("tid") int tId) throws AccessException{
		ModelAndView model = new ModelAndView("It/viewTrans_It");
		try {
			
			ItTransaction s =  ItTransactionManager.getTransactionById(tId);
			if(s == null){
				model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
			}
			else
			model.getModelMap().put("ItTransaction", s);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Transaction not found");
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView getAllTransactions() throws AccessException {
		System.out.println("In getAllTransactions");
		 ModelAndView model = new ModelAndView("It/viewAllTrans_It");
			try {
				List<ItTransaction> list = ItTransactionManager.getAllTransactions();
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
	
	
	@RequestMapping(value="/It/getQueryModifyTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryModifyTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("IT/getQueryModifyTrans_It");
			
			return model;
	}
	
	@RequestMapping(value="/It/getModifyTransForm", method = RequestMethod.POST)
	public ModelAndView getModifyTransactionForm(@RequestParam("tid") int tId) throws AccessException{
		 ModelAndView model = new ModelAndView("It/modifyTrans_It");
			try {
				ItTransaction s =  ItTransactionManager.getTransactionById(tId);
				if(s== null){
					model.getModelMap().put("errormsg", "Transcation not find with id:"+tId);
				}
				else{
				model.getModelMap().put("modifyTransItForm", s);
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
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/getQueryDeleteTransForm", method = RequestMethod.GET)
	public ModelAndView getQeuryDeleteTransactionForm() throws AccessException{
		System.out.println("In getViewTransactionForm");
		 ModelAndView model = new ModelAndView("It/getQueryDeleteTrans_It");
			
			return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/deleteTrans", method = RequestMethod.POST)
	public ModelAndView getDeleteTransaction(@RequestParam("tid") int tId) throws AccessException{
		System.out.println("In getModifyTransactionForm");
		 ModelAndView model = new ModelAndView("It/getQueryDeleteTrans_It");
			
			try {
				
				ItTransactionManager.deleteTransaction(tId);
				model.getModelMap().put("message", "Transaction Deleted with transaction id:"+tId);
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
			return model;
	}
	
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/modifyTransaction", method = RequestMethod.POST)
	public ModelAndView modifyItTransaction(@ModelAttribute("modifyTransItForm") ItTransaction ItTrans) throws AccessException{
		System.out.println("In modifyItTransaction");
		ModelAndView model = new ModelAndView("It/RegEmp_It");
		
		
		try {
			ItTransaction s =  ItTransactionManager.getTransactionById(ItTrans.gettId());
			
			s.setLastModifyOn(new Date());
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setLastModifyBy(currentUser);
			s.setLastModifyOn(new Date());
			ItTransactionManager.modifyTransaction(s);
			s.setParentTId(ItTrans.gettId());
			s.settId(null);
			s.setTranscName(ItTrans.getTranscName());
			s.setTransDesc(ItTrans.getTransDesc());
			s.setCreationDate(new Date());
			
			ItTransactionManager.createTransaction(s);
			model.getModelMap().put("message", "Transaction modified with id:"+ItTrans.gettId());
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/authorizeTransByDMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByDManager(@PathVariable("tId") int tID) throws AccessException{
		
		ModelAndView model = new ModelAndView("It/deptmanager_It");
		
		
		try {
			ItTransaction s =  ItTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover1(currentUser);
			s.setStatus("PENDING_BY_CORPMGR");
			ItTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/authorizeTransByCorpMgr/{tId}", method = RequestMethod.GET)
	public ModelAndView authorizeTransactionByCorpManager(@PathVariable("tId") int tID) throws AccessException{
		System.out.println("In modifyItTransaction");
		ModelAndView model = new ModelAndView("It/corpmanager_It");
			try {
			ItTransaction s =  ItTransactionManager.getTransactionById(tID);
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			s.setApprover2(currentUser);
			s.setStatus("COMPLETED");
			ItTransactionManager.modifyTransaction(s);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/viewAllPendingTransForDeptMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForDeptMgr() throws AccessException{
		System.out.println("In getAllPendingTransForDeptMgr");
		 ModelAndView model = new ModelAndView("It/authorizeTransByMgr");
			try {
				List<ItTransaction> list = ItTransactionManager.getAuthTransByDeptMgr();
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
	@AccessPolicies({@ElementAccessPolicy(role="ROLE_REG_EMP_It",deptId = 3)})
	@RequestMapping(value="/It/viewAllPendingTransForCorpMgr", method = RequestMethod.GET)
	public ModelAndView getAllPendingTransForCorpMgr()  throws AccessException{
		System.out.println("In getAllPendingTransForCorpMgr");
		 ModelAndView model = new ModelAndView("It/authorizeTransByCMgr");
			try {
				List<ItTransaction> list = ItTransactionManager.getAuthTransByCorpMgr();
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

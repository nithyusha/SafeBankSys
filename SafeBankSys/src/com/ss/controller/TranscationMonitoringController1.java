package com.ss.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ss.entity.TMTransaction;

public class TranscationMonitoringController1 {
	
	@RequestMapping(value="/TM/createTrans", method = RequestMethod.GET)
	public ModelAndView createTransaction() {
 
		 ModelAndView model = new ModelAndView("viewTrans_TM");
			TMTransaction tm = new TMTransaction();
			model.getModelMap().put("createTrans_TM", tm);
			
			return model;
	}
	@RequestMapping(value="/TM/viewTrans", method = RequestMethod.GET)
	public ModelAndView getTransToView() {
		 ModelAndView model = new ModelAndView("viewTrans_TM");
		TMTransaction tm = new TMTransaction();
		model.getModelMap().put("viewTrans", tm);
		
		return model;
 
	}
	
	@RequestMapping(value="/TM/viewTrans", method = RequestMethod.GET)
	public ModelAndView viewTransaction() {
		 ModelAndView model = new ModelAndView("viewTrans_TM");
		TMTransaction tm = new TMTransaction();
		model.getModelMap().put("viewTrans", tm);
		
		return model;
 
	}
	
	
	@RequestMapping(value="/TM/modifyTrans", method = RequestMethod.GET)
	public String modifyTransaction(ModelAndView model) {
		 
		
		return "modifyTrans_TM";
 
	}
	@RequestMapping(value="/TM/authorizeTrans", method = RequestMethod.GET)
	public String authorizeTransaction(ModelAndView model) {
		 
		
		return "authorizeTrans_TM";
 
	}
	@RequestMapping(value="/TM/editAccounts", method = RequestMethod.GET)
	public String editUserAccounts(ModelAndView model) {
		 
		
		return "editAccounts_TM";
 
	}
	@RequestMapping(value="/TM/viewDeptTrans", method = RequestMethod.GET)
	public String viewAllDeptTransactions(ModelAndView model) {
		 
		
		return "viewDeptTrans_TM";
 
	}
	
	

}

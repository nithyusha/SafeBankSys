package com.ss.controller;

import java.util.Random;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ss.entity.CheckingAccDetails;
import com.ss.entity.SavingAccDetails;
import com.ss.service.AccountCreationManager;

public class AccountCreation {

	@Autowired
	AccountCreationManager acm;
	
	//@RequestMapping(value="", method=RequestMethod.POST)
	
	public ModelAndView createCheckingAcc(int userId) {
		
		ModelAndView model = new ModelAndView("");
		
		CheckingAccDetails cad = new CheckingAccDetails();
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			String accNo = "";
			double bal = 0;
			
			Random r = new Random();
			
			int low1 = 100000000;
			int high1 = 1000000000;
			int accNo1 = r.nextInt(high1-low1) + low1;
			accNo = "4744880" + accNo1; 

			cad.setAccNo(accNo);
			cad.setBal(bal);
			cad.setUserId(userId);
			acm.createCheckingAcc(cad);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		model.getModelMap().put("creatingCheckingAccDetails", cad);
		
		return model;
	}

	public ModelAndView createSavingAcc(int userId) {
		
		ModelAndView model = new ModelAndView("");
		SavingAccDetails sad = new SavingAccDetails();
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			String accNo = "";
			double bal = 0;
			
			Random r = new Random();
			
			int low1 = 100000000;
			int high1 = 1000000000;
			int accNo1 = r.nextInt(high1-low1) + low1;
			accNo = "4777880" + accNo1; 

			sad.setAccNo(accNo);
			sad.setBal(bal);
			
			
			acm.createSavingAcc(sad);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		model.getModelMap().put("creatingSavingAccDetails", sad);
		return model;
	}
}
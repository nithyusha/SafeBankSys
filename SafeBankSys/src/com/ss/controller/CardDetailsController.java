package com.ss.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.entity.CardDetails;
import com.ss.exception.ApplicationException;
import com.ss.service.CardDetailsManager;
import com.ss.service.UserManager;

@Controller
public class CardDetailsController {
	@Autowired
	UserManager userManager;
	@Autowired
	CardDetailsManager cdm;
	
	@RequestMapping(value="/card/createCardDetails", method=RequestMethod.POST) 
	public ModelAndView getCreateCard() {
		
		ModelAndView model = new ModelAndView("card/createCard");
		
		CardDetails cd = new CardDetails();
		
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			int userId = userManager.getIdByname(currentUser);
			String cardNo = "";
			int ssvNo = 0;
			String expiry = "";
			
			/***** EXPIRY DATE CREATION *****/
			int year = Calendar.getInstance().get(Calendar.YEAR) + 3;
			int month = Calendar.getInstance().get(Calendar.MONTH);
			expiry = month + "/" + year;

			/***** SSV NO CREATION *****/
			Random r = new Random();
			int low = 100;
			int high = 1000;
			ssvNo = r.nextInt(high-low) + low;

			/***** CARD NUMBER GENERATION *****/
			int low1 = 100000000;
			int high1 = 1000000000;
			int cardNo1 = r.nextInt(high1-low1) + low1;
			cardNo = "4744880" + cardNo1; 
			
			cd.setExpiry(expiry);
			cd.setSsv_No(ssvNo);
			cd.setCardNo(cardNo);

			cdm.createCardDetails(cd);
			model.getModelMap().put("creatingCardDetails", cd);
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "Card details creation failed");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "Card details creation failed");
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	
	@RequestMapping(value="/card/valCard", method=RequestMethod.GET)
	public ModelAndView validateCardDetails(Principal principal, @RequestParam("expiry") String expiry, @RequestParam("ssv_No") int ssv_No, @RequestParam("cardNo") long cardNo) {
		
		ModelAndView model = new ModelAndView("card/validateCard");
		ModelAndView model1 = new ModelAndView("card/invalidate");
		
		String currentUser = principal.getName();

		int userId;
		
		try {
			userId = userManager.getIdByname(currentUser);
			int cd = cdm.validateCardDetails(expiry, ssv_No, cardNo);
			if(cd!=0 && cd!=userId)
				return model;
			else
				return model1;
		} catch (HibernateException e) {
			e.printStackTrace();
			return model1;
		} catch (ApplicationException e) {
			e.printStackTrace();
			return model1;
		}
	}
	
	
}
package com.ss.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.checkValidations.EmailValidator;
import com.ss.checkValidations.PasswordValidator;
import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.DepartmentEmployee;
import com.ss.entity.SBSUsers;
import com.ss.entity.User;
import com.ss.entity.UserRoles;
import com.ss.exception.ApplicationException;
import com.ss.service.AccountCreationManager;
import com.ss.service.CardDetailsManager;
import com.ss.service.DeptsManager;
import com.ss.service.MerchantManager;
import com.ss.service.UserManager;

@Controller
public class UserManagementController {
	
	@Autowired
	UserManager userManager ;
	
	@Autowired
	DeptsManager deptsManager;
	
	@Autowired
	AccountCreationManager acm;
	
	@Autowired
	CardDetailsManager cdm;
	
	@Autowired
	MerchantManager mm;
	
	@RequestMapping(value="/admin/transactionLogs", method = RequestMethod.GET)
	public ModelAndView getTransactionLogs() {
		ModelAndView model = new ModelAndView("/admin/transactionLogs");
		String logs = mm.getTransactionLogs();
		model.getModelMap().put("logs", logs);
		return model;
	}
	
	
	@RequestMapping(value="/admin/getRegistrationForm", method = RequestMethod.GET)
	public ModelAndView getUserRegistrationForm(){
		  final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("user name:"+ currentUser);
		ModelAndView model = new ModelAndView("admin/registrationForm");
		SBSUsers users = new SBSUsers();
		model.getModelMap().put("registrationform", users);
		
		return model;
		
	}
	
	@RequestMapping(value="/admin/registeruser", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("registrationform") SBSUsers user,@RequestParam("dept") int deptId){
		System.out.println("In registerUser");
		ModelAndView model = new ModelAndView("/admin/adminuser");
		ModelAndView model1 = new ModelAndView("/admin/lenerr1");
		ModelAndView model2 = new ModelAndView("/admin/lenerr2");
		ModelAndView model3 = new ModelAndView("/admin/lenerr3");
		ModelAndView model4 = new ModelAndView("/admin/lenerr4");
		
		try {
			int s1 = user.getUserName().length();
			int s2 = user.getPassword().length();
			
			if(s1<=7 || s1>33)
				return model1;
			
			if(s2<=7 || s2>33)
				return model2;
			
			PasswordValidator pv = new PasswordValidator();
			boolean y = pv.validate(user.getPassword());
			
			if(!y)
				return model2;
						
			int s3 = user.getFirstName().length();
			int s4 = user.getLastName().length();
			int s5 = user.getEmailId().length();
			int s6 = user.getAddress().length();
			
			if(s3==0 || s4==0 || s5==0 || s6==0)
				return model4;
			
			EmailValidator ev = new EmailValidator();
			boolean x = ev.validate(user.getEmailId());
			
			if(!x)
				return model3;
			

			user.setCreationDate(new Date());
			user.setLastModifyOn(new Date());
			SBSUsers userDb = new SBSUsers();
			userDb = userManager.getUserByname(user.getUserName());
			if(userDb == null) {
			//userManager.getUserByname(user.getUserName()).getUserName().equalsIgnoreCase(user.getUserName())
			userManager.registerUser(user);
			int id = userManager.getIdByname(user.getUserName());
			DepartmentEmployee dEmp = new DepartmentEmployee();
			
			if(user.getRole().contains("ROLE_DEPARTMENT_MGR")){
				String deptname = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
				dEmp.setDeptId(deptId);
				dEmp.setUserId(id);
				dEmp.setDeptRole("ROLE_DEPT_MGR_"+deptname);
				deptsManager.addEmployee(dEmp);
			}else if(user.getRole().contains("ROLE_CORPORATE_MGR")){
				String deptname = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
				dEmp.setDeptId(deptId);
				dEmp.setUserId(id);
				dEmp.setDeptRole("ROLE_CORP_MGR_"+deptname);
				deptsManager.addEmployee(dEmp);
			}else if(user.getRole().contains("ROLE_INDIVIDUAL_USER")){
				
				cdm.createCardDetails(getCreateCard(id));
				acm.createCheckingAcc(createCheckingAcc(id));
				
			}else if(user.getRole().contains("ROLE_MERCHANT")){
				
				acm.createCheckingAcc(createCheckingAcc(id));
				cdm.createCardDetails(getCreateCard(id));
			}
			
			User u = new User();
			u.setUserId(id);
			u.setUserName(user.getUserName());
			u.setPassword(user.getPassword());
			u.setEnabled((short)1);
			userManager.persistUser(u);
			UserRoles ur = new UserRoles();
			ur.setUserId(id);
			ur.setAuthority(user.getRole());
			userManager.persistUserRoles(ur);
			} else {
			model.getModelMap().put("errormsg", "Registration failed. Username exists");
			}
		} catch (HibernateException e) {
			
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public CheckingAccDetails createCheckingAcc(int userId) {

		CheckingAccDetails cad = new CheckingAccDetails();

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
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return cad;
	}

	public CardDetails getCreateCard(int userId) {

		CardDetails cd = new CardDetails();

		try {
			String cardNo = "";
			int ssvNo = 0;
			String expiry = "";

			/***** EXPIRY DATE CREATION *****/
			int year = Calendar.getInstance().get(Calendar.YEAR) + 3 - 2000;
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
			cd.setUserId(userId);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println("1:"+cd);
		return cd;
	}
}
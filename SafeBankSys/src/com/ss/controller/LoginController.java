package com.ss.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ss.exception.ApplicationException;
import com.ss.service.DeptsManager;
import com.ss.service.UserManager;

@Controller
public class LoginController {
	@Autowired 
	UserManager userManager;
	@Autowired 
	DeptsManager deptsMgr ;
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public ModelAndView printWelcome() {
 
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		String jsp = getRole(name);
		ModelAndView model = new ModelAndView(jsp);
		
		model.getModelMap().put("msg", "Welcome "+name);
	
		return model;
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model,HttpServletRequest request) {
		// RuntimeAccess.getInstance().getRequest().getSession().invalidate();
		 SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
 
	}
	@RequestMapping(value="/forbidden", method = RequestMethod.GET)
	public String forbidden(ModelMap model) {
 
		return "forbidden";
 
	}
	
	/*@RequestMapping(value="/404*", method = RequestMethod.GET)
	public String forbidden(ModelMap model) {
 
		return "forbidden";
 
	}*/
	
	public String getRole(String userName){
		String jsp =null;
		int id ;
		String deptName = null;
		int deptId = 0;
		try {
			String role  = userManager.getRole(userName);
			
			if(role.contains("INDIVIDUAL_USER")){
				jsp = "individual/individual_user";
			}else if(role.contains("MERCHANT")){
				jsp = "merchant/merchant";
			}else if(role.contains("ADMIN")){
				jsp = "admin/adminuser";
			}else if(role.contains("REGULAR_EMP")){
				id = userManager.getIdByname(userName);
				deptId = deptsMgr.getDeptEmpDetailsByUserId(id).getDeptId();
				deptName = deptsMgr.getDeptDetailsByDeptId(deptId).getDeptName();
				jsp = "/"+deptName+"/RegEmp_"+deptName;
				 
			}else if(role.contains("DEPARTMENT_MGR")){
				id = userManager.getIdByname(userName);
				deptId = deptsMgr.getDeptEmpDetailsByUserId(id).getDeptId();
				deptName = deptsMgr.getDeptDetailsByDeptId(deptId).getDeptName();
				jsp = "/"+deptName+"/deptmanager_"+deptName;
			}else if(role.contains("CORPORATE_MGR")){
				id = userManager.getIdByname(userName);
				deptId = deptsMgr.getDeptEmpDetailsByUserId(id).getDeptId();
				deptName = deptsMgr.getDeptDetailsByDeptId(deptId).getDeptName();
				jsp = "/"+deptName+"/corpmanager_"+deptName;
			}
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return jsp;
	
	}
	
	
	
}
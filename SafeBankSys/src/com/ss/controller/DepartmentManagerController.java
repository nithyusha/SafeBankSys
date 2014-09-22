package com.ss.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.entity.DepartmentEmployee;
import com.ss.entity.SalesTransaction;
import com.ss.exception.ApplicationException;
import com.ss.service.DeptsManager;
import com.ss.service.UserManager;

@Controller
public class DepartmentManagerController {

	@Autowired
	DeptsManager deptsManager;
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value="/getAddEmployeeForm", method = RequestMethod.GET)
	public ModelAndView getAddEmployeeForm() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String deptName = getDeptName(name);
		 ModelAndView model = new ModelAndView(deptName+"/addemployee_"+deptName);
			return model;
	}
	@RequestMapping(value="/getDeleteEmployeeForm", method = RequestMethod.GET)
	public ModelAndView getDeleteEmployeeForm() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String deptName = getDeptName(name);
		 ModelAndView model = new ModelAndView(deptName+"/deleteemployee_"+deptName);
			return model;
	}
	@RequestMapping(value="/getTransferEmployeeForm", method = RequestMethod.GET)
	public ModelAndView getTransferEmployeeForm() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String deptName = getDeptName(name);
		 ModelAndView model = new ModelAndView(deptName+"/transferemployee_"+deptName);
			return model;
	}
	@RequestMapping(value="/addEmployee", method = RequestMethod.POST)
	public ModelAndView addEmployee(@RequestParam("empId") int userId ) {
 
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		int id;
		String deptName = null;
		ModelAndView model = null;
		
		try {
			id = userManager.getIdByname(name);
			int deptId = deptsManager.getDeptEmpDetailsByUserId(id).getDeptId();
			deptName = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
			
			if(userManager.getUser(userId) != null){
			DepartmentEmployee dEmp = new DepartmentEmployee();
			dEmp.setDeptId(deptId);
			dEmp.setUserId(userId);
			dEmp.setDeptRole("ROLE_REG_EMP_"+deptName);
			
			model = new ModelAndView(deptName+"/addemployee_"+deptName) ;
			deptsManager.addEmployee(dEmp);
			model.getModelMap().put("successmsg", "Employee Added with id: "+userId);
			}
			
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot add employee:");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot add employee:");
			e.printStackTrace();
		}
		
		return model;
 
	}
	
	@RequestMapping(value="/deleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployee( @RequestParam("empId") int userId ) {
 
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		int id;
		String deptName = null;
		ModelAndView model = null;
		try {
			id = userManager.getIdByname(name);
			int deptId = deptsManager.getDeptEmpDetailsByUserId(id).getDeptId();
			deptName = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
			if(userManager.getUser(userId) != null){
			model = new ModelAndView(deptName+"/deleteemployee_"+deptName) ;
			deptsManager.deleteEmployee(userId);
			model.getModelMap().put("msg", "Employee delete with id: "+userId);
			}
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot delete employee");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot delete employee");
			e.printStackTrace();
		}
		
		return model;
 
	}
	@RequestMapping(value="/transferEmployee", method = RequestMethod.POST)
	public ModelAndView transferEmployee( @RequestParam("empId") int userId,@RequestParam("deptId") int toDeptId) {
 
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		int id;
		String deptName = null;
		ModelAndView model = new ModelAndView();
		try {
			id = userManager.getIdByname(name);
			DepartmentEmployee dEmp = new DepartmentEmployee();
			int deptId = deptsManager.getDeptEmpDetailsByUserId(id).getDeptId();
			deptName = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
			String toDeptName = deptsManager.getDeptDetailsByDeptId(toDeptId).getDeptName();
			model = new ModelAndView(deptName+"/transferemployee_"+deptName);
			if(userManager.getUser(userId) != null && deptsManager.getDeptEmpById(userId) != null){
				
			DepartmentEmployee d = deptsManager.getDeptEmpById(userId);
			d.setDeptId(toDeptId);
			d.setDeptRole("ROLE_REG_EMP_"+toDeptName);
			deptsManager.transferEmployee(d);
			model.getModelMap().put("successmsg", "Employee transfered with id: "+userId +"to dept:"+toDeptId);
			}
		} catch (HibernateException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot transfer employee");
			e.printStackTrace();
		} catch (ApplicationException e) {
			model.getModelMap().put("errormsg", "OOps!! Cannot transfer employee");
			e.printStackTrace();
		}
		
		return model;
 
	}
	
	public String getDeptName(String userName){
		int id;
		String deptName = null;
		try {
			id = userManager.getIdByname(userName);
			int deptId = deptsManager.getDeptEmpDetailsByUserId(id).getDeptId();
			deptName = deptsManager.getDeptDetailsByDeptId(deptId).getDeptName();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return deptName;
	}
	
}

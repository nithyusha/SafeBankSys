package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.Department;
import com.ss.entity.DepartmentEmployee;
import com.ss.entity.SBSUsers;
import com.ss.exception.ApplicationException;

public interface DeptsManager {

	public Department getDeptDetailsByDeptId(int deptId) throws HibernateException, ApplicationException;
	public DepartmentEmployee getDeptEmpDetailsByUserId(int id) throws HibernateException, ApplicationException;
	public void addEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException;
	public void deleteEmployee(int empId) throws HibernateException, ApplicationException;
	public void transferEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException;
	public List<SBSUsers> getAllEmployeeDetails(int deptId) throws HibernateException, ApplicationException;
	public DepartmentEmployee getDeptEmpById(int userId) throws HibernateException, ApplicationException;
	public boolean checkRole(int userId,int deptId,String role) throws HibernateException, ApplicationException;
}

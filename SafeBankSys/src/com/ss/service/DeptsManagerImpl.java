package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.DepartmentsDAO;
import com.ss.entity.Department;
import com.ss.entity.DepartmentEmployee;
import com.ss.entity.SBSUsers;
import com.ss.exception.ApplicationException;

@Service
public class DeptsManagerImpl implements DeptsManager {

	@Override
	public Department getDeptDetailsByDeptId(int deptId) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		return d.getDeptDetailsByDeptId(deptId);
		
	}

	@Override
	public DepartmentEmployee getDeptEmpDetailsByUserId(int id) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		return d.getDeptEmpDetailsByUserId(id);
	}

	@Override
	public void addEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		d.addEmployee(dEmp);
	}

	@Override
	public void deleteEmployee(int empId) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		d.deleteEmployee(empId);
	}

	@Override
	public void transferEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		d.transferEmployee(dEmp);		
	}

	@Override
	public List<SBSUsers> getAllEmployeeDetails(int deptId) throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		return d.getAllEmployeeDetails(deptId);
	}

	@Override
	public DepartmentEmployee getDeptEmpById(int userId)
			throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		return d.getDeptEmpById(userId);
		
	}

	@Override
	public boolean checkRole(int userId, int deptId, String role)
			throws HibernateException, ApplicationException {
		DepartmentsDAO d = new DepartmentsDAO();
		return d.checkRole(userId, deptId, role);
	}

	

}

package com.ss.db;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.Department;
import com.ss.entity.DepartmentEmployee;
import com.ss.entity.SBSUsers;
import com.ss.entity.SalesTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;

public class DepartmentsDAO {
	
	public Department getDeptDetailsByDeptId(int deptId) throws HibernateException, ApplicationException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      Department dept = null;
	      try{
		    	 
		         String sql = "select * from DEPARTMENT where DEPTID = :id";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(Department.class);
			 		query.setParameter("id", deptId);
			 		List<Department> results = query.list();
			 		if(results.size()> 0)
			 		 dept = results.get(0);
		         
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		
		return dept;
		
	}
	public DepartmentEmployee getDeptEmpDetailsByUserId(int id) throws HibernateException, ApplicationException{
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      DepartmentEmployee deptEmp = null;
	      try{
		    	 
		         String sql = "select * from DEPARTMENTEMPLOYEE where DEPARTMENTEMPLOYEE.USERID = :id";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(DepartmentEmployee.class);
			 		query.setParameter("id", id);
			 		List<DepartmentEmployee>  results = query.list();
			 		if(results.size() > 0)
			 		deptEmp = results.get(0);
		         
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		
		return deptEmp;
		
	}

	
	public void addEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException{
		System.out.println("in dao createTransaction");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(dEmp);
			tx.commit();
			System.out.println("Transaction created");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteEmployee(int userId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			DepartmentEmployee deptEmp = getDeptEmpDetailsByUserId(userId);
			
			session.delete(deptEmp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	public void transferEmployee(DepartmentEmployee dEmp) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			int id = dEmp.getDeptUserId();
			//DepartmentEmployee dEmpDB = (DepartmentEmployee) session.get(DepartmentEmployee.class, id);
			
			session.update(dEmp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		
	}
	public DepartmentEmployee getDeptEmpById(int userId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      DepartmentEmployee dEmp = null;
	      try{
		    	 
		         String sql = "SELECT * FROM DEPARTMENTEMPLOYEE where userid =:id ";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(DepartmentEmployee.class);
			 		query.setParameter("id", userId);
			 		List<DepartmentEmployee> results = query.list();
			 		if(results.size() > 0)
			 		 dEmp = results.get(0);
			 		
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
	      return dEmp;
	}
	
	public List<SBSUsers> getAllEmployeeDetails(int deptId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<SalesTransaction> transactions = null;
	      List<SBSUsers> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM SBSUSERS WHERE userid IN (SELECT userid FROM DEPARTMENTEMPLOYEE WHERE deptid : id)";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(SalesTransaction.class);
		 		query.setParameter("id", deptId);
		 		 results = query.list();
		 		
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return results;
	}
	
	public boolean checkRole(int userId,int deptId,String role) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	     
	      List<DepartmentEmployee> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM DEPARTMENTEMPLOYEE WHERE userid = "+userId+" and deptid = "+deptId+" and " +
	         		"deptrole like '"+role+"'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(DepartmentEmployee.class);
		 		/*query.setParameter("userid", userId);
		 		query.setParameter("deptid", deptId);*/
		 		 results = query.list();
		 		if(results.size() == 0 || results == null){
		 			return false;
		 		}else{
		 			return true;
		 		}
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return false;
		
		
		
	}
	
}

package com.ss.db;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.SBSUsers;
import com.ss.entity.User;
import com.ss.entity.UserRoles;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;


public class UserManagementDao {
	
	public void registerUser(SBSUsers user) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			//System.out.println("Transaction created");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	public void editUserDetails(SBSUsers user) throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			int id = user.getUserId();
			//SBSUsers userFromDB = (SBSUsers) session.get(SBSUsers.class, id);
			//userFromDB = user;
			user.setLastModifyOn(new Date());
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		}
	
	public String getRole(String username) throws HibernateException, ApplicationException{
		String role  = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	       
	      try{
		    	 
		        // String sql = "SELECT * FROM USERS join USER_ROLES ON USERS.USER_ID = USER_ROLES.USER_ID where USERS.USERNAME like '"+username+"'";
		         String sql = "SELECT * FROM USER_ROLES WHERE USER_ID IN (SELECT USER_ID FROM USERS WHERE USERNAME LIKE '"+username+"')";
		         
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(UserRoles.class);
			 		//query.setParameter("name", username);
			 		List<UserRoles> results = query.list();
			 		role = results.get(0).getAuthority();
		         
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
		return role;
	}
	
	public int getIdByname(String username) throws HibernateException, ApplicationException {

		int id = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	       
	      try{
		    	 
		         String sql = "SELECT * FROM SBSUSERS where USERNAME like '"+username+"'";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(SBSUsers.class);
			 		//query.setParameter("name", username);
			 		List<SBSUsers> results = query.list();
			 		if(results.size()>0){
			 		SBSUsers user = results.get(0);
			 		id = user.getUserId();
			 		}
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
		return id;
	}
	public SBSUsers getUserByname(String username) throws HibernateException, ApplicationException {

		int id = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      SBSUsers user  = null;
	      try{
		    	 
		         String sql = "SELECT * FROM SBSUSERS where USERNAME like '"+username+"'";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(SBSUsers.class);
			 		//query.setParameter("name", username);
			 		List<SBSUsers> results = query.list();
			 		if(results.size() !=0){
			 		 user = results.get(0);
			 		}
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
		return user;
	}
	
	public SBSUsers getUserBynameNEmail(String username,String emailId) throws HibernateException, ApplicationException {

		int id = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      SBSUsers user  = null;
	      try{
		    	 
		         String sql = "SELECT * FROM SBSUSERS where USERNAME like '"+username+"' and 	emailid like '"+emailId+"'";
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(SBSUsers.class);
			 		//query.setParameter("name", username);
			 		
			 		List<SBSUsers> results = query.list();
			 		if(results.size() >0)
			 		 user = results.get(0);
			 		
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
		return user;
	}
	
	public void persistUser(User user) throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public void persistUserRoles(UserRoles user) throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void editUser(User user) throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public User getUser(int userId) throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      User user  = null;
	      try{
		    	 
		         String sql = "SELECT * FROM USERS where USER_ID = "+userId;
		         SQLQuery query = session.createSQLQuery(sql);
			 		query.addEntity(User.class);
			 		List<User> results = query.list();
			 		if(results.size()>0)
			 		 user = results.get(0);
			 		
		         
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
		return user;
	}

	
}

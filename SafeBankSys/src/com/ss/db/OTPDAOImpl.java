package com.ss.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ss.entity.SBSUsers;
import com.ss.hibernate.util.HibernateUtil;
import com.ss.service.UserManager;

;



//public class OTPDAOImpl implements OTPDAO{
	public class OTPDAOImpl {
	/*@Autowired
    private SessionFactory sessionFactory;*/
	
	
		
	
    public int persistOTP(String generatedOTP, int  id, String emailId) throws Exception{
		/*Query query = sessionFactory.getCurrentSession().createQuery("update UserDTO u set u.oneTimePassword = :generatedOTP where u.userName = :userName and u.emailId = :emailId");
		query.setParameter("generatedOTP", generatedOTP);
		query.setParameter("userName", userName);
		query.setParameter("emailId", emailId);
		int createdEntities = query.executeUpdate();
		return createdEntities;
*/	
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
		try{
	         String sql = "update SBSUsers  set ONETIMEPASSWORD = '"+generatedOTP+"' WHERE USERID = "+id+" and EMAILID = '"+emailId+"'" ;
	         SQLQuery query = session.createSQLQuery(sql);
		 	//	query.addEntity(SBSUsers.class);
		 		/*query.setParameter("generatedOTP", generatedOTP);
		 		query.setParameter("userName", userName);
		 		query.setParameter("emailId", emailId);*/
		 		System.out.println(sql);
		 		 return query.executeUpdate();
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return 0;
		
		
    }
	
	
	public String getPersistedOTP(String userName) throws Exception{
		
		
		/*Query query = sessionFactory.getCurrentSession().createQuery("select u.oneTimePassword FROM UserDTO u where u.userName = :userName");
		query.setParameter("userName", userName);

		return query.uniqueResult().toString();*/
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      String pwd = null;
		try{
	    	 
	         String sql = "select  *  FROM SBSUSERS u where u.USERNAME = :userName  " ;
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(SBSUsers.class);
		 		query.setParameter("userName", userName);
		 		List<SBSUsers>users = query.list();
		 		if(users.size()>0){
		 			pwd = users.get(0).getOneTimePassword();
		 		}
		 		 
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return pwd;
    }
	
	
	public int persistNewPassword(int id,String encodedPW) throws Exception{
		/*Query query = sessionFactory.getCurrentSession().createQuery("update UserDTO u set u.password = :newPassword where u.userName = :userName");
		query.setParameter("newPassword", encodedPW);
		query.setParameter("userName", userName);

		int createdEntities = query.executeUpdate();
		return createdEntities;*/
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
		try{
			String sql = "update SBSUsers  set PASSWORD = '"+encodedPW+"' WHERE USERID = "+id ;
	       //  String sql = "update SBSUsers  set SBSUsers.PASSWORD =:newPassword WHERE USERNAME = :userName " ;
	         SQLQuery query = session.createSQLQuery(sql);
		 	//	query.addEntity(SBSUsers.class);
		 		/*query.setParameter("newPassword", encodedPW);
		 		query.setParameter("userName", userName);*/
		 		 return query.executeUpdate();
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return 0;
	
    }
	
	/*@Transactional
	public UserDTO getUserDTOFor(String destinationUserName, String emailId){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO where userName = :userName and emailId = :emailId");
		query.setParameter("userName", destinationUserName);
		query.setParameter("emailId", emailId);
		UserDTO userDTO = (UserDTO)query.uniqueResult();
		return userDTO;
	}
	
	@Transactional
	public UserDTO getUserDTOFor(String senderUserName){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO where userName = :userName");
		query.setParameter("userName", senderUserName);
		
		UserDTO userDTO = (UserDTO)query.uniqueResult();
		return userDTO;
	}*/
}

package com.ss.db;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.HRTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;



public class HRTransactionDAO {
	
	
	
	public List<HRTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<HRTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM HRTRANSACTION";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(HRTransaction.class);
	 		
	 		 transactions = query.list();
	 		
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transactions;
		
	}
	
	public List<HRTransaction> getAllTransactionsByMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<HRTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM HRTRANSACTION WHERE STATUS like 'PENDING_BY_MANAGER'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(HRTransaction.class);
		 		//query.setParameter("status", "PENDING_BY_MANAGER");
		 		 transactions = query.list();
		 		//transactions = results;
	         System.out.println("");
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transactions;
		
	}
	
	public List<HRTransaction> getAllTransactionsByCorpMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    //  List<HRTransaction> transactions = null;
	      List<HRTransaction> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM HRTRANSACTION WHERE STATUS like 'PENDING_BY_CORPMGR'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(HRTransaction.class);
		 		//query.setParameter("status", "PENDING_BY_CORP_MANAGER");
		 		 results = query.list();
		 		System.out.println("");
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return results;
		
	}
	
	public HRTransaction getTransactionById(int transactionId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      HRTransaction transaction = null;
	      try{
	    	  
	         tx = session.beginTransaction();
	         String sql = "SELECT * FROM HRTRANSACTION WHERE TID = :tid";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(HRTransaction.class);
	 		query.setParameter("tid", transactionId);
	 		List<HRTransaction> results = query.list();
	 		if(results != null && results.size()!=0)
	 		transaction  = (HRTransaction)results.get(0);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transaction;
		
	}
	
	public void createTransaction(HRTransaction transaction)
			throws HibernateException, ApplicationException {
		System.out.println("in dao createTransaction");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(transaction);
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
	
public void modifyTransaction(HRTransaction transaction) throws HibernateException, ApplicationException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		int id = transaction.gettId();
		//HRTransaction trans = 
          //      (HRTransaction)session.get(HRTransaction.class, transaction.gettId()); 
		//transaction.setLastModifyOn(transaction.getLastModifyOn());
		//transaction.setLastModifyBy(transaction.getLastModifyBy());
		
		session.update(transaction);
		
		
		
		tx.commit();
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		e.printStackTrace();
	} finally {
		session.close();
	}

	}

public void deleteTransaction(Integer transactionId) throws HibernateException, ApplicationException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		HRTransaction trans = 
                (HRTransaction)session.get(HRTransaction.class, transactionId); 
		
		session.delete(trans);
		tx.commit();
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		e.printStackTrace();
	} finally {
		session.close();
	}

	}

	

}

package com.ss.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.ItTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;



public class ItTransactionDAO {
	
	
	
	public List<ItTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<ItTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM ItTRANSACTION";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(ItTransaction.class);
	 		
	 		 transactions = query.list();
	 		
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transactions;
		
	}
	
	public List<ItTransaction> getAllTransactionsByMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<ItTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM ItTRANSACTION WHERE STATUS like 'PENDING_BY_MANAGER'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(ItTransaction.class);
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
	
	public List<ItTransaction> getAllTransactionsByCorpMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    //  List<ItTransaction> transactions = null;
	      List<ItTransaction> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM ItTRANSACTION WHERE STATUS like 'PENDING_BY_CORPMGR'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(ItTransaction.class);
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
	
	public ItTransaction getTransactionById(int transactionId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      ItTransaction transaction = null;
	      try{
	    	  
	         tx = session.beginTransaction();
	         String sql = "SELECT * FROM ItTRANSACTION WHERE TID = :tid";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(ItTransaction.class);
	 		query.setParameter("tid", transactionId);
	 		List<ItTransaction> results = query.list();
	 		if(results != null && results.size()!=0)
	 		transaction  = (ItTransaction)results.get(0);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transaction;
		
	}
	
	public void createTransaction(ItTransaction transaction)
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
	
public void modifyTransaction(ItTransaction transaction) throws HibernateException, ApplicationException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		int id = transaction.gettId();
		//ItTransaction trans = 
          //      (ItTransaction)session.get(ItTransaction.class, transaction.gettId()); 
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
		ItTransaction trans = 
                (ItTransaction)session.get(ItTransaction.class, transactionId); 
		
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

package com.ss.db;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.CMTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;



public class CMTransactionDAO {
	
	
	
	public List<CMTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<CMTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM CMTRANSACTION";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(CMTransaction.class);
	 		
	 		 transactions = query.list();
	 		
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transactions;
		
	}
	
	public List<CMTransaction> getAllTransactionsByMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<CMTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM CMTRANSACTION WHERE STATUS like 'PENDING_BY_MANAGER'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(CMTransaction.class);
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
	
	public List<CMTransaction> getAllTransactionsByCorpMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    //  List<CMTransaction> transactions = null;
	      List<CMTransaction> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM CMTRANSACTION WHERE STATUS like 'PENDING_BY_CORPMGR'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(CMTransaction.class);
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
	
	public CMTransaction getTransactionById(int transactionId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      CMTransaction transaction = null;
	      try{
	    	  
	         tx = session.beginTransaction();
	         String sql = "SELECT * FROM CMTRANSACTION WHERE TID = :tid";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(CMTransaction.class);
	 		query.setParameter("tid", transactionId);
	 		List<CMTransaction> results = query.list();
	 		if(results != null && results.size()!=0)
	 		transaction  = (CMTransaction)results.get(0);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transaction;
		
	}
	
	public void createTransaction(CMTransaction transaction)
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
	
public void modifyTransaction(CMTransaction transaction) throws HibernateException, ApplicationException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		int id = transaction.gettId();
		//CMTransaction trans = 
          //      (CMTransaction)session.get(CMTransaction.class, transaction.gettId()); 
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
		CMTransaction trans = 
                (CMTransaction)session.get(CMTransaction.class, transactionId); 
		
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

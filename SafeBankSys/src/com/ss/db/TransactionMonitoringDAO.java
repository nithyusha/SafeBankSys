package com.ss.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.SalesTransaction;
import com.ss.entity.TMTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;



public class TransactionMonitoringDAO {
	
	
	
	public List<TMTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<TMTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM TMTRANSACTION";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(TMTransaction.class);
	 		
	 		 transactions = query.list();
	 		
	         
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transactions;
		
	}
	
	public List<TMTransaction> getAllTransactionsByMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<TMTransaction> transactions = null;
	      try{
	    	 
	         String sql = "SELECT * FROM TMTRANSACTION WHERE STATUS like 'PENDING_BY_MANAGER'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(TMTransaction.class);
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
	
	public List<TMTransaction> getAllTransactionsByCorpMgr() throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    //  List<SalesTransaction> transactions = null;
	      List<TMTransaction> results = null;
	      try{
	    	 
	         String sql = "SELECT * FROM TMTRANSACTION WHERE STATUS like 'PENDING_BY_CORPMGR'";
	         SQLQuery query = session.createSQLQuery(sql);
		 		query.addEntity(TMTransaction.class);
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
	
	public TMTransaction getTransactionById(int transactionId) throws HibernateException, ApplicationException{
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      TMTransaction transaction = null;
	      try{
	    	  
	         tx = session.beginTransaction();
	         String sql = "SELECT * FROM TMTRANSACTION WHERE TID = :tid";
	         SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(TMTransaction.class);
	 		query.setParameter("tid", transactionId);
	 		List<TMTransaction> results = query.list();
	 		if( results.size()>0)
	 		transaction  = (TMTransaction)results.get(0);
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		return transaction;
		
	}
	
	public void createTransaction(TMTransaction transaction)
			throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(transaction);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
public void modifyTransaction(TMTransaction transaction) throws HibernateException, ApplicationException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		int id = transaction.gettId();
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
		TMTransaction trans = 
                (TMTransaction)session.get(TMTransaction.class, transactionId); 
		
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

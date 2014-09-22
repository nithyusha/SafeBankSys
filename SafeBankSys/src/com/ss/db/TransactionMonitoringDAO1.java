package com.ss.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.TMTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;

public class TransactionMonitoringDAO1 {

	public List<TMTransaction> getAllTransactions() throws HibernateException,
			ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<TMTransaction> transactions = null;
		try {
			tx = session.beginTransaction();
			transactions = session.createQuery("FROM TMTRANSACTION").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return transactions;

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
		TMTransaction trans = 
                (TMTransaction)session.get(TMTransaction.class, transaction.gettId()); 
		trans = transaction;
		session.update(trans);
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

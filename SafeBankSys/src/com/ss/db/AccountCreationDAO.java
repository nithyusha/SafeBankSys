package com.ss.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.CheckingAccDetails;
import com.ss.entity.SavingAccDetails;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;

public class AccountCreationDAO {

	public void createCheckingAcc(CheckingAccDetails cad) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(cad);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
	
	}

	public void createSavingAcc(SavingAccDetails sad) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(sad);
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

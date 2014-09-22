package com.ss.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.CheckingAccount;
import com.ss.entity.SavingsAccount;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;

public class InternalUserDAO {

	public void modifyCheckingAcnt(CheckingAccount acnt)
			throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			int id = acnt.getCheckingAcntId();
			CheckingAccount chkAcnt = (CheckingAccount) session.get(
					CheckingAccount.class, id);
			session.update(chkAcnt);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void modifySavingsAcnt(SavingsAccount acnt)
			throws HibernateException, ApplicationException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			int id = acnt.getSavingAcntId();
			SavingsAccount chkAcnt = (SavingsAccount) session.get(
					SavingsAccount.class, id);
			session.update(chkAcnt);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public CheckingAccount getCheckingAccountDeatils(String username,int acntType){
		CheckingAccount acnt = new CheckingAccount();
		return acnt;
	}
	public SavingsAccount getSavingsAccountDeatils(String username,int acntType){
		SavingsAccount acnt = new SavingsAccount();
		return acnt;
	}

}

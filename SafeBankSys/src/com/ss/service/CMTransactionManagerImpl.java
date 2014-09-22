package com.ss.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.CMTransactionDAO;
import com.ss.entity.CMTransaction;
import com.ss.exception.ApplicationException;


@Service
public class CMTransactionManagerImpl implements CMTransactionManager {

	@Override
	public void createTransaction(CMTransaction transaction) throws HibernateException, ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		
		s.createTransaction(transaction);
	}

	@Override
	public CMTransaction getTransactionById(int tId) throws HibernateException,
			ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		return s.getTransactionById(tId);
		
	}
	
	@Override
	public List<CMTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		CMTransactionDAO s = new CMTransactionDAO();
		return s.getAllTransactions();
		
		
	}

	@Override
	public void modifyTransaction(CMTransaction transaction)
			throws HibernateException, ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public void deleteTransaction(int tId) throws HibernateException,
			ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		s.deleteTransaction(tId);
		
	}

	@Override
	public void authorizeTranByDeptMgr(
			CMTransaction transaction) throws HibernateException,
			ApplicationException {
		
		CMTransactionDAO s = new CMTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public List<CMTransaction> getAuthTransByDeptMgr()
			throws HibernateException, ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		return s.getAllTransactionsByMgr();
	}

	@Override
	public List<CMTransaction> getAuthTransByCorpMgr()
			throws HibernateException, ApplicationException {
		CMTransactionDAO s = new CMTransactionDAO();
		return s.getAllTransactionsByCorpMgr();
	}
	

}

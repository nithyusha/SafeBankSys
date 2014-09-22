package com.ss.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.ItTransactionDAO;
import com.ss.entity.ItTransaction;
import com.ss.exception.ApplicationException;


@Service
public class ItTransactionManagerImpl implements ItTransactionManager {

	@Override
	public void createTransaction(ItTransaction transaction) throws HibernateException, ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		
		s.createTransaction(transaction);
	}

	@Override
	public ItTransaction getTransactionById(int tId) throws HibernateException,
			ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		return s.getTransactionById(tId);
		
	}
	
	@Override
	public List<ItTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		ItTransactionDAO s = new ItTransactionDAO();
		return s.getAllTransactions();
		
		
	}

	@Override
	public void modifyTransaction(ItTransaction transaction)
			throws HibernateException, ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public void deleteTransaction(int tId) throws HibernateException,
			ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		s.deleteTransaction(tId);
		
	}

	@Override
	public void authorizeTranByDeptMgr(
			ItTransaction transaction) throws HibernateException,
			ApplicationException {
		
		ItTransactionDAO s = new ItTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public List<ItTransaction> getAuthTransByDeptMgr()
			throws HibernateException, ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		return s.getAllTransactionsByMgr();
	}

	@Override
	public List<ItTransaction> getAuthTransByCorpMgr()
			throws HibernateException, ApplicationException {
		ItTransactionDAO s = new ItTransactionDAO();
		return s.getAllTransactionsByCorpMgr();
	}
	

}

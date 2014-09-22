package com.ss.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.HRTransactionDAO;
import com.ss.entity.HRTransaction;
import com.ss.exception.ApplicationException;


@Service
public class HRTransactionManagerImpl implements HRTransactionManager {

	@Override
	public void createTransaction(HRTransaction transaction) throws HibernateException, ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		
		s.createTransaction(transaction);
	}

	@Override
	public HRTransaction getTransactionById(int tId) throws HibernateException,
			ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		return s.getTransactionById(tId);
		
	}
	
	@Override
	public List<HRTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		HRTransactionDAO s = new HRTransactionDAO();
		return s.getAllTransactions();
		
		
	}

	@Override
	public void modifyTransaction(HRTransaction transaction)
			throws HibernateException, ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public void deleteTransaction(int tId) throws HibernateException,
			ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		s.deleteTransaction(tId);
		
	}

	@Override
	public void authorizeTranByDeptMgr(
			HRTransaction transaction) throws HibernateException,
			ApplicationException {
		
		HRTransactionDAO s = new HRTransactionDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public List<HRTransaction> getAuthTransByDeptMgr()
			throws HibernateException, ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		return s.getAllTransactionsByMgr();
	}

	@Override
	public List<HRTransaction> getAuthTransByCorpMgr()
			throws HibernateException, ApplicationException {
		HRTransactionDAO s = new HRTransactionDAO();
		return s.getAllTransactionsByCorpMgr();
	}
	

}


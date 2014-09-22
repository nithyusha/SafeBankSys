package com.ss.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.SalesTranscationDAO;
import com.ss.entity.SalesTransaction;
import com.ss.exception.ApplicationException;


@Service
public class SalesTransactionManagerImpl implements SalesTransactionManager {

	@Override
	public void createTransaction(SalesTransaction transaction) throws HibernateException, ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		
		s.createTransaction(transaction);
	}

	@Override
	public SalesTransaction getTransactionById(int tId) throws HibernateException,
			ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		return s.getTransactionById(tId);
		
	}
	
	@Override
	public List<SalesTransaction> getAllTransactions() throws HibernateException, ApplicationException{
		SalesTranscationDAO s = new SalesTranscationDAO();
		return s.getAllTransactions();
		
		
	}

	@Override
	public void modifyTransaction(SalesTransaction transaction)
			throws HibernateException, ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public void deleteTransaction(int tId) throws HibernateException,
			ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		s.deleteTransaction(tId);
		
	}

	@Override
	public void authorizeTranByDeptMgr(
			SalesTransaction transaction) throws HibernateException,
			ApplicationException {
		
		SalesTranscationDAO s = new SalesTranscationDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		s.modifyTransaction(transaction);
	}

	@Override
	public List<SalesTransaction> getAuthTransByDeptMgr()
			throws HibernateException, ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		return s.getAllTransactionsByMgr();
	}

	@Override
	public List<SalesTransaction> getAuthTransByCorpMgr()
			throws HibernateException, ApplicationException {
		SalesTranscationDAO s = new SalesTranscationDAO();
		return s.getAllTransactionsByCorpMgr();
	}
	

}

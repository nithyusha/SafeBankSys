package com.ss.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.TransactionMonitoringDAO;
import com.ss.entity.DepartmentEmployee;
import com.ss.entity.InternalEmployee;
import com.ss.entity.TMTransaction;
import com.ss.exception.ApplicationException;

@Service
public class TransactionMonitoringManagerImpl implements
		TranscationMonitoringManger {

	@Override
	public void createTransaction(TMTransaction transaction)
			throws HibernateException, ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		t.createTransaction(transaction);
	}

	@Override
	public TMTransaction getTransactionById(int tId) throws HibernateException,
			ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		return t.getTransactionById(tId);
	}

	@Override
	public List<TMTransaction> getAllTransactions() throws HibernateException,
			ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		return t.getAllTransactions();
	}

	@Override
	public void modifyTransaction(TMTransaction transaction)
			throws HibernateException, ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		t.modifyTransaction(transaction);
	}

	@Override
	public void deleteTransaction(int tId) throws HibernateException,
			ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		t.deleteTransaction(tId);
	}

	@Override
	public void authorizeTranByDeptMgr(TMTransaction transaction)
			throws HibernateException, ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		Date date = new Date();		
		transaction.setLastModifyOn(date);
		t.modifyTransaction(transaction);
	}

	@Override
	public List<TMTransaction> getAuthTransByDeptMgr()
			throws HibernateException, ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		return t.getAllTransactionsByMgr();
	}

	@Override
	public List<TMTransaction> getAuthTransByCorpMgr()
			throws HibernateException, ApplicationException {
		TransactionMonitoringDAO t = new TransactionMonitoringDAO();
		return t.getAllTransactionsByCorpMgr();
	}

	
}

package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.SalesTransaction;
import com.ss.exception.ApplicationException;

public interface SalesTransactionManager {
	public void createTransaction(SalesTransaction transaction) throws HibernateException, ApplicationException;
	public SalesTransaction getTransactionById(int tId) throws HibernateException, ApplicationException;
	public List<SalesTransaction> getAllTransactions() throws HibernateException, ApplicationException;
	public void modifyTransaction(SalesTransaction salesTrans) throws HibernateException, ApplicationException;
	public void deleteTransaction(int tId) throws HibernateException, ApplicationException;
	public void authorizeTranByDeptMgr(SalesTransaction transaction) throws HibernateException, ApplicationException;
	public List<SalesTransaction> getAuthTransByDeptMgr() throws HibernateException, ApplicationException;
	public List<SalesTransaction> getAuthTransByCorpMgr() throws HibernateException, ApplicationException;
}


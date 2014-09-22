package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.ItTransaction;
import com.ss.exception.ApplicationException;

public interface ItTransactionManager {
	public void createTransaction(ItTransaction transaction) throws HibernateException, ApplicationException;
	public ItTransaction getTransactionById(int tId) throws HibernateException, ApplicationException;
	public List<ItTransaction> getAllTransactions() throws HibernateException, ApplicationException;
	public void modifyTransaction(ItTransaction ItTrans) throws HibernateException, ApplicationException;
	public void deleteTransaction(int tId) throws HibernateException, ApplicationException;
	public void authorizeTranByDeptMgr(ItTransaction transaction) throws HibernateException, ApplicationException;
	public List<ItTransaction> getAuthTransByDeptMgr() throws HibernateException, ApplicationException;
	public List<ItTransaction> getAuthTransByCorpMgr() throws HibernateException, ApplicationException;
}


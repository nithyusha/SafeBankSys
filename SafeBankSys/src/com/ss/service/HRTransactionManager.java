
package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.HRTransaction;
import com.ss.exception.ApplicationException;

public interface HRTransactionManager {
	public void createTransaction(HRTransaction transaction) throws HibernateException, ApplicationException;
	public HRTransaction getTransactionById(int tId) throws HibernateException, ApplicationException;
	public List<HRTransaction> getAllTransactions() throws HibernateException, ApplicationException;
	public void modifyTransaction(HRTransaction HRTrans) throws HibernateException, ApplicationException;
	public void deleteTransaction(int tId) throws HibernateException, ApplicationException;
	public void authorizeTranByDeptMgr(HRTransaction transaction) throws HibernateException, ApplicationException;
	public List<HRTransaction> getAuthTransByDeptMgr() throws HibernateException, ApplicationException;
	public List<HRTransaction> getAuthTransByCorpMgr() throws HibernateException, ApplicationException;
}


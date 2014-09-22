

package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.CMTransaction;
import com.ss.entity.HRTransaction;
import com.ss.exception.ApplicationException;

public interface CMTransactionManager {
	public void createTransaction(CMTransaction transaction) throws HibernateException, ApplicationException;
	public CMTransaction getTransactionById(int tId) throws HibernateException, ApplicationException;
	public List<CMTransaction> getAllTransactions() throws HibernateException, ApplicationException;
	public void modifyTransaction(CMTransaction HRTrans) throws HibernateException, ApplicationException;
	public void deleteTransaction(int tId) throws HibernateException, ApplicationException;
	public void authorizeTranByDeptMgr(CMTransaction transaction) throws HibernateException, ApplicationException;
	public List<CMTransaction> getAuthTransByDeptMgr() throws HibernateException, ApplicationException;
	public List<CMTransaction> getAuthTransByCorpMgr() throws HibernateException, ApplicationException;
}


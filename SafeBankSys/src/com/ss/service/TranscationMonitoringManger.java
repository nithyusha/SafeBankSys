package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.DepartmentEmployee;
import com.ss.entity.InternalEmployee;
import com.ss.entity.SalesTransaction;
import com.ss.entity.TMTransaction;
import com.ss.exception.ApplicationException;

public interface TranscationMonitoringManger {

	public void createTransaction(TMTransaction transaction) throws HibernateException, ApplicationException;
	public TMTransaction getTransactionById(int tId) throws HibernateException, ApplicationException;
	public List<TMTransaction> getAllTransactions() throws HibernateException, ApplicationException;
	public void modifyTransaction(TMTransaction tmTrans) throws HibernateException, ApplicationException;
	public void deleteTransaction(int tId) throws HibernateException, ApplicationException;
	public void authorizeTranByDeptMgr(TMTransaction transaction) throws HibernateException, ApplicationException;
	public List<TMTransaction> getAuthTransByDeptMgr() throws HibernateException, ApplicationException;
	public List<TMTransaction> getAuthTransByCorpMgr() throws HibernateException, ApplicationException;

}

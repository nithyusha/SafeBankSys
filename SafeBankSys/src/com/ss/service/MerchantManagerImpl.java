package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.MerchantDAO;
import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;
import com.ss.exception.ApplicationException;

@Service
public class MerchantManagerImpl implements MerchantManager {
	
	@Override
	public boolean authorizeTransaction(double amt, long cardNo, int userId) {
		
		try {
			MerchantDAO md = new MerchantDAO();
			return md.authorizeTransaction(amt, cardNo, userId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void submitTransaction() {
		
		
	}

	@Override
	public List<CheckingTransactions> viewTransaction(long userId) {
		
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.viewTransactions(userId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkBal(int userId, double amt) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.checkBal(userId, amt);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int checkAccNo(String accNo) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.checkAccNo(accNo);
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		} catch (ApplicationException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void makeTransfer(int userId, double amt, int userId1) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			md.makeTransfer(userId, amt, userId1);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void debit(double amt, int userId) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			md.debit(amt, userId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void credit(double amt, int userId) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			md.credit(amt, userId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SBSUsers> viewAllMerchants() {
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.viewAllMerchants();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CheckingAccDetails> viewAccDetails(int userId) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.viewAccDetails(userId);
		} catch (HibernateException e) {
			
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List<CardDetails> viewCardDetails(int userId) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			return md.viewCardDetails(userId);
		} catch (HibernateException e) {			
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public CheckingAccDetails debit(double amt, String accNo) {
		MerchantDAO md = new MerchantDAO();
		try {
			CheckingAccDetails cad = md.debit(amt, accNo);
			return cad;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CheckingAccDetails credit(double amt, String accNo) {
		MerchantDAO md = new MerchantDAO();
		
		try {
			CheckingAccDetails cad = md.credit(amt, accNo);
			return cad;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getTransactionLogs() {
		MerchantDAO md = new MerchantDAO();
		
		return md.getTransactionLogs();
	}






}
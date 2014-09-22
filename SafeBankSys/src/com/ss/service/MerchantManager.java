package com.ss.service;

import java.util.List;

import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;

public interface MerchantManager {

	public void submitTransaction();
	public List<CheckingTransactions> viewTransaction(long userId);
	public boolean authorizeTransaction(double amt, long cardNo, int userId);
	public boolean checkBal(int userId, double amt);
	public int checkAccNo(String accNo);
	public void makeTransfer(int userId, double amt, int userId1);
	public void debit(double amt, int userId);
	public void credit(double amt, int userId);
	public List<SBSUsers> viewAllMerchants();
	public List<CheckingAccDetails> viewAccDetails(int userId);
	public List<CardDetails> viewCardDetails(int userId);
	public CheckingAccDetails debit(double amt, String accNo);
	public CheckingAccDetails credit(double amt, String accNo);	
	public String getTransactionLogs();
}

package com.ss.service;

import java.util.List;

import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;


public interface IndividualUsersManager {
	
	public void authorizeTransaction(double amt, long cardNo, int userId);
	public List<CheckingTransactions> viewTransaction(int userId);
	
}



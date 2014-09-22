package com.ss.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;

@Service
public class IndividualUserManagerImpl implements IndividualUsersManager {


	@Override
	public void authorizeTransaction(double amt, long cardNo, int userId) {
		
	}

	@Override
	public List<CheckingTransactions> viewTransaction(int userId) {
		return null;
	}
}
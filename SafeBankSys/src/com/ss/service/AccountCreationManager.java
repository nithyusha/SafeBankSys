package com.ss.service;

import com.ss.entity.CheckingAccDetails;
import com.ss.entity.SavingAccDetails;

public interface AccountCreationManager {

	public void createCheckingAcc(CheckingAccDetails cad);
	public void createSavingAcc(SavingAccDetails sad);
}

package com.ss.service;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.AccountCreationDAO;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.SavingAccDetails;
import com.ss.exception.ApplicationException;
@Service
public class AccountCreationManagerImpl implements AccountCreationManager{

	@Override
	public void createCheckingAcc(CheckingAccDetails cad) {
		
		AccountCreationDAO agd = new AccountCreationDAO();
		try {
			agd.createCheckingAcc(cad);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createSavingAcc(SavingAccDetails sad) {

		AccountCreationDAO agd = new AccountCreationDAO();
		try {
			agd.createSavingAcc(sad);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
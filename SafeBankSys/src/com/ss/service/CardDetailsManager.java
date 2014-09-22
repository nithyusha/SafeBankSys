package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.ss.entity.CardDetails;
import com.ss.exception.ApplicationException;

public interface CardDetailsManager {

	public void createCardDetails(CardDetails card) throws HibernateException, ApplicationException;
	public int validateCardDetails(String expiry, int ssv_No, long cardNo) throws HibernateException, ApplicationException;
	public List<CardDetails> validateCardDetails1(int userId, String expiry, int ssv_No, long cardNo) throws HibernateException, ApplicationException;
}

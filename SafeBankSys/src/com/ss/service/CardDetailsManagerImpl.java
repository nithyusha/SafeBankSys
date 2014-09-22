package com.ss.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.CardDetailsDAO;
import com.ss.entity.CardDetails;
import com.ss.exception.ApplicationException;

@Service
public class CardDetailsManagerImpl implements CardDetailsManager{

	@Override
	public void createCardDetails(CardDetails card) throws HibernateException,
			ApplicationException {
		
		CardDetailsDAO card1 = new CardDetailsDAO();
		card1.createCardDetails(card);
	}

	@Override
	public int validateCardDetails(String expiry, int ssv_No,
			long cardNo) throws HibernateException, ApplicationException {

		CardDetailsDAO card1 = new CardDetailsDAO();
		return card1.validateCardDetails(expiry, ssv_No, cardNo);
	}

	@Override
	public List<CardDetails> validateCardDetails1(int userId, String expiry,
			int ssv_No, long cardNo) throws HibernateException,
			ApplicationException {

		CardDetailsDAO card1 = new CardDetailsDAO();
		return card1.validateCardDetails1(userId, expiry, ssv_No, cardNo);
	}
}

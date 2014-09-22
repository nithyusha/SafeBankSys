package com.ss.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.CardDetails;
import com.ss.entity.SBSUsers;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;


public class CardDetailsDAO {

	public void createCardDetails(CardDetails card) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
//			Query q = (Query) session.createCriteria("from SBSUsers where userId="+userId);
//			List<SBSUsers> l = q.list();
//			
//			for(SBSUsers s:l)
//				card.setSbsusers(s);
			
//			card.setUserId(userId);
			tx = session.beginTransaction();
			session.save(card);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public int validateCardDetails(String expiry, int ssv_No,
			long cardNo) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<CardDetails> results = null;
		int userId = 0;
		
		try {
			tx = session.beginTransaction();
			
			String sql = "SELECT * FROM CARDDETAILS WHERE EXPIRY = :expiry AND SSV_NO = :ssv_No AND CARDNO = :cardNo";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CardDetails.class);
			query.setParameter("expiry", expiry);
			query.setParameter("ssv_No", ssv_No);
			query.setParameter("cardNo", cardNo);

			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		
		if(results.size() !=0 && results !=null) {
			Query q1 = session.createQuery("from CardDetails where cardNo = "+cardNo);
			List<CardDetails> l1 = q1.list();
			
			for(CardDetails cd:l1) {
				userId = cd.getUserId();
			}

			session.close();
			return userId;
		}
		else {
			session.close();
			return 0;
		}

		/*if(!results.equals("null") || !results.equals("[]") || !results.isEmpty() || results!=null || !(results.size()==0))
			return true;
		else
			return false;
*/
	}


	public List<CardDetails> validateCardDetails1(int userId, String expiry, int ssv_No,
			long cardNo) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<CardDetails> results = null;
		
		try {
			tx = session.beginTransaction();
			String sql = "SELECT * FROM CARDDETAILS WHERE USERID = :userId AND EXPIRY = :expiry AND SSV_NO = :ssv_No AND CARDNO = :cardNo";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CardDetails.class);
			query.setParameter("expiry", expiry);
			query.setParameter("ssv_No", ssv_No);
			query.setParameter("cardNo", cardNo);
			query.setParameter("userId", userId);
			
			results = query.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		

		if(results.size() !=0 && results !=null){
			return results;
		}
		else 
			return results;

	}
}
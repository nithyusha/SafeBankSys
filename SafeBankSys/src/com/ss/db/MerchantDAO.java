package com.ss.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.entity.CardDetails;
import com.ss.entity.CheckingAccDetails;
import com.ss.entity.CheckingTransactions;
import com.ss.entity.SBSUsers;
import com.ss.entity.SalesTransaction;
import com.ss.exception.ApplicationException;
import com.ss.hibernate.util.HibernateUtil;
import com.ss.logs.LogFile;

public class MerchantDAO {

	public List<CheckingTransactions> viewTransactions(long userId) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CheckingTransactions where userId="+userId);
		List<CheckingTransactions> l1 = q.list();
				
		session.close();
		return l1;
		
	}
	
	public boolean authorizeTransaction(double amt, long cardNo, int userId) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		LogFile lf = new LogFile();
		
		Query q = session.createQuery("from CheckingAccDetails where userId="+userId);
		Query q1 = session.createQuery("from CardDetails where cardNo="+cardNo);
		Query q3 = session.createQuery("from SBSUsers where userId="+userId);
		
		List<CheckingAccDetails> l = q.list();
		List<CardDetails> l1 = q1.list();
		List<SBSUsers> l3 = q3.list();

		for(CardDetails cd:l1) {
	
//			SBSUsers a = cd.getSbsusers();
			int a = cd.getUserId();
			Query q2 = session.createQuery("from CheckingAccDetails where userId="+a);
			List<CheckingAccDetails> l2 = q2.list();
			double bal1 = 0;
			
			for(CheckingAccDetails c1:l2) {
				bal1 = c1.getBal();

				for(CheckingAccDetails c:l) {

					double bal = c.getBal();

					if(bal1>amt) {
						c.setBal(bal+amt);
						c1.setBal(bal1-amt);
						
						session.update(c);
						session.update(c1);
					} else {
						return false;
					}
					
					CheckingTransactions ct1 = new CheckingTransactions();
					CheckingTransactions ct2 = new CheckingTransactions();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Calendar cal = Calendar.getInstance();
				    System.out.println(dateFormat.format(cal.getTime()));
					
					
					Query q4 = session.createQuery("from SBSUsers where userId="+a);
					List<SBSUsers> l4 = q4.list();
					double x = c.getBal();
					
					
					for(SBSUsers s1:l3) {
						for(SBSUsers s:l4) {
							ct1.setAmt(x);
							ct1.setInvolvedParty("Transaction occured with "+s.getFirstName()+" "+s.getLastName());
							ct1.setStatus("PROCESSED");
							ct1.setTransactionType("CREDIT");
							ct1.setUserId(a);
							ct1.setDate(dateFormat.format(cal.getTime()));
							lf.addLogs("CREDIT Transaction occured between "+s.getFirstName()+" "+s.getLastName()+
									" and "+s1.getFirstName()+" "+s1.getLastName()+"(RECEIVER) on "+dateFormat.format(cal.getTime())+" for amount $"+amt+".**************************");
						}
					}
					
					
					for(SBSUsers s:l4) {
						for(SBSUsers s1:l3) {
							ct2.setAmt(c1.getBal());
							ct2.setInvolvedParty("Purchase happened with Merchant "+s1.getFirstName()+" "+s1.getLastName());
							ct2.setStatus("PROCESSED");
							ct2.setTransactionType("DEBIT");
							ct2.setDate(dateFormat.format(cal.getTime()));
							ct2.setUserId(userId);
							lf.addLogs("DEBIT Transaction occured between "+s1.getFirstName()+" "+s1.getLastName()+
									" and "+s.getFirstName()+" "+s.getLastName()+"(TRANSFERER) on "+dateFormat.format(cal.getTime())+" for amount $"+amt+".**************************");
						}
					}
					
					session.save(ct1);
					session.save(ct2);
				}
			}
		}
	
		tx.commit();
		session.close();
		return true;
	}
	
	
	public boolean checkBal(int userId, double amt) throws HibernateException, ApplicationException {
		
		double bal=0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Query q1 = session.createQuery("from CheckingAccDetails where userId = "+userId);
		List<CheckingAccDetails> l = q1.list();
		
		for(CheckingAccDetails cad : l) {
			bal = cad.getBal();
		}
		
		System.out.println(bal+"***"+amt);
		
		session.close();
		
		if(bal>amt)
			return true;
		else
			return false;
	}
	
	
	public int checkAccNo(String accNo) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q1 = session.createQuery("from CheckingAccDetails where accNo = "+accNo);
		List<CheckingAccDetails> l = q1.list();
		
		int userId1 = 0;
		
		for(CheckingAccDetails l1 : l) {
			userId1 = l1.getUserId();
		}

		session.close();
		return userId1;
	}	
	
	
	public void makeTransfer(int userId, double amt, int userId1) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Query q1T = session.createQuery("from CheckingAccDetails where userId="+userId);
		Query q1R = session.createQuery("from CheckingAccDetails where userId="+userId1);
		
		List<CheckingAccDetails> l1T = q1T.list();
		List<CheckingAccDetails> l1R = q1R.list();
		
		double bal1T = 0;
		double bal1R = 0;
		
		for(CheckingAccDetails cad1T:l1T) {
			bal1T = cad1T.getBal();
			bal1T = bal1T - amt;
			cad1T.setBal(bal1T);
			session.update(cad1T);
		}
			
		for(CheckingAccDetails cad1R:l1R) {
			bal1R = cad1R.getBal();
			bal1R = bal1R + amt;
			cad1R.setBal(bal1R);
			session.update(cad1R);
		}

		
/*
		Query q2T = session.createQuery("from CheckingTransactions where userId="+userId);
		Query q2R = session.createQuery("from CheckingTransactions where userId="+userId1);
*/
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
/*		
	    List<CheckingTransactions> l2T = q2T.list();
		List<CheckingTransactions> l2R = q2R.list();
*/
	    
	    CheckingTransactions ctT = new CheckingTransactions();
	    CheckingTransactions ctR = new CheckingTransactions();
	    
		Query q3T = session.createQuery("from SBSUsers where userId="+userId);
		Query q3R = session.createQuery("from SBSUsers where userId="+userId1);
		
		List<SBSUsers> l3T = q3T.list();
		List<SBSUsers> l3R = q3R.list();
		
		
		for(SBSUsers sbsR:l3R) {
				ctT.setAmt(bal1T);
				ctT.setStatus("PENDING");
				ctT.setTransactionType("DEBIT");
				ctT.setDate(dateFormat.format(cal.getTime()));
				ctT.setInvolvedParty("Money transferred to "+sbsR.getFirstName()+" "+sbsR.getLastName());
				ctT.setUserId(userId1);
				session.save(ctT);
		}
		

		for(SBSUsers sbsT:l3T) {
				ctT.setAmt(bal1R);
				ctR.setStatus("PENDING");
				ctR.setTransactionType("CREDIT");
				ctR.setDate(dateFormat.format(cal.getTime()));
				ctR.setInvolvedParty("Money received from "+sbsT.getFirstName()+" "+sbsT.getLastName());
				ctR.setUserId(userId);
				session.save(ctR);
		}

		tx.commit();
		session.close();
	}
	
	
	public void debit(double amt, int userId) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q1 = session.createQuery("from CheckingAccDetails where userId = "+userId);
		List<CheckingAccDetails> l1 = q1.list();
		
		double bal = 0;
		
		for(CheckingAccDetails cad:l1) {
			bal = cad.getBal();
			cad.setBal(bal-amt);
			session.update(cad);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		CheckingTransactions ct = new CheckingTransactions();
		
		ct.setDate(dateFormat.format(cal.getTime()));
		ct.setInvolvedParty("SELF WITHDRAWL");
		ct.setStatus("PROCESSED");
		ct.setTransactionType("WITHDRAWL/DEBIT");
		ct.setUserId(userId);
		ct.setAmt(bal-amt);
		

		Query q2 = session.createQuery("from SBSUsers where userId="+userId);
		List<SBSUsers> l2 = q2.list();
		
		LogFile lf = new LogFile();
		
		
		for(SBSUsers sbs:l2){
			lf.addLogs(sbs.getFirstName()+" "+sbs.getLastName()+" withdrew $"+amt+".**************************");
		}
		
		session.save(ct);
		tx.commit();
		session.close();
	}


	public void credit(double amt, int userId) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q1 = session.createQuery("from CheckingAccDetails where userId = "+userId);
		List<CheckingAccDetails> l1 = q1.list();
		
		double bal = 0;
		
		for(CheckingAccDetails cad:l1) {
			bal = cad.getBal();
			cad.setBal(bal+amt);
			session.update(cad);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		CheckingTransactions ct = new CheckingTransactions();
		
		ct.setDate(dateFormat.format(cal.getTime()));
		ct.setInvolvedParty("SELF DEPOSIT");
		ct.setStatus("PROCESSED");
		ct.setTransactionType("DEPOSIT/CREDIT");
		ct.setUserId(userId);
		ct.setAmt(amt+bal);
		
		Query q2 = session.createQuery("from SBSUsers where userId="+userId);
		List<SBSUsers> l2 = q2.list();
		
		LogFile lf = new LogFile();
		
		
		for(SBSUsers sbs:l2){
			lf.addLogs(sbs.getFirstName()+" "+sbs.getLastName()+" deposited $"+amt+".**************************");
		}

		
		session.save(ct);
		tx.commit();
		session.close();
	}
	
	
	public List<SBSUsers> viewAllMerchants() throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

        String sql = "SELECT * FROM SBSUSERS WHERE ROLE like 'ROLE_MERCHANT'";
        SQLQuery query = session.createSQLQuery(sql);
	 		query.addEntity(SBSUsers.class);

		
		List<SBSUsers> l1 = query.list();

		session.close();
		return l1;
	}

	
	public List<CheckingAccDetails> viewAccDetails(int userId) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q1 = session.createQuery("from CheckingAccDetails where userId = "+userId);
		List<CheckingAccDetails> l1 = q1.list();
		
		session.close();
		return l1;		
	}


	public List<CardDetails> viewCardDetails(int userId) throws HibernateException, ApplicationException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q1 = session.createQuery("from CardDetails where userId = "+userId);
		List<CardDetails> l1 = q1.list();
		
		session.close();
		return l1;		
	}


	public CheckingAccDetails debit(double amt, String accNo) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		CheckingAccDetails cad = new CheckingAccDetails();
		
        String sql = "SELECT * FROM CHECKINGACCDETAILS WHERE ACCNO = :accNo";
        SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CheckingAccDetails.class);
		query.setParameter("accNo", accNo);
		
		List<CheckingAccDetails> results = query.list();
		if(results != null && results.size()!=0)
		cad  = (CheckingAccDetails)results.get(0);

		return cad;
	}
	
	
	public CheckingAccDetails credit(double amt, String accNo) throws HibernateException, ApplicationException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		CheckingAccDetails cad = new CheckingAccDetails();
		
        String sql = "SELECT * FROM CHECKINGACCDETAILS WHERE ACCNO = :accNo";
        SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CheckingAccDetails.class);
		query.setParameter("accNo", accNo);
		
		List<CheckingAccDetails> results = query.list();
		if(results != null && results.size()!=0)
		cad  = (CheckingAccDetails)results.get(0);

		return cad;
	}

	
	public String getTransactionLogs() {
		
		LogFile lf = new LogFile();
		
		return lf.printLogs();
	}
}
package com.ss.entity;


public class CheckingTransactions {
	long transactionId;
	String date;
	String involvedParty;
	String status;
	String transactionType;		//Debit/Credit
//	SBSUsers sbsusers;
	int userId;
	double amt;
	

	
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
/*	public SBSUsers getSbsusers() {
		return sbsusers;
	}
	public void setSbsusers(SBSUsers sbsusers) {
		this.sbsusers = sbsusers;
	}
*/	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getInvolvedParty() {
		return involvedParty;
	}
	public void setInvolvedParty(String involvedParty) {
		this.involvedParty = involvedParty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CheckingTransactions [transactionId=" + transactionId
				+ ", date=" + date + ", involvedParty=" + involvedParty
				+ ", status=" + status + ", transactionType=" + transactionType
				+ ", userId=" + userId + ", amt=" + amt + "]";
	}
}
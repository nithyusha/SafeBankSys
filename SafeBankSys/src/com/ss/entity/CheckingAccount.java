package com.ss.entity;

public class CheckingAccount {
	
	private int checkingAcntId;
	private int acntId;
	private String checkingActNo;
	private double balance;
	Account acc;
	/**
	 * @return the checkingAcntId
	 */
	public int getCheckingAcntId() {
		return checkingAcntId;
	}
	/**
	 * @param checkingAcntId the checkingAcntId to set
	 */
	public void setCheckingAcntId(int checkingAcntId) {
		this.checkingAcntId = checkingAcntId;
	}
	/**
	 * @return the acntId
	 */
	public int getAcntId() {
		return acntId;
	}
	/**
	 * @param acntId the acntId to set
	 */
	public void setAcntId(int acntId) {
		this.acntId = acntId;
	}
	/**
	 * @return the checkingActNo
	 */
	public String getCheckingActNo() {
		return checkingActNo;
	}
	/**
	 * @param checkingActNo the checkingActNo to set
	 */
	public void setCheckingActNo(String checkingActNo) {
		this.checkingActNo = checkingActNo;
	}
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return the acc
	 */
	public Account getAcc() {
		return acc;
	}
	/**
	 * @param acc the acc to set
	 */
	public void setAcc(Account acc) {
		this.acc = acc;
	}
	
	
}

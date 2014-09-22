package com.ss.entity;

public class SavingsAccount {

	private int savingAcntId;
	public int acntId;
	private String savingActNo;
	private double balance;
	Account acc;
	/**
	 * @return the savingAcntId
	 */
	public int getSavingAcntId() {
		return savingAcntId;
	}
	/**
	 * @param savingAcntId the savingAcntId to set
	 */
	public void setSavingAcntId(int savingAcntId) {
		this.savingAcntId = savingAcntId;
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
	 * @return the savingActNo
	 */
	public String getSavingActNo() {
		return savingActNo;
	}
	/**
	 * @param savingActNo the savingActNo to set
	 */
	public void setSavingActNo(String savingActNo) {
		this.savingActNo = savingActNo;
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

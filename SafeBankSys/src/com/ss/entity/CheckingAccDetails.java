package com.ss.entity;

public class CheckingAccDetails {

	String accNo;
	double bal;
//	SBSUsers sbsUser;
	int userId;

	
	
public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	//	public SBSUsers getSbsUser() {
//		return sbsUser;
//	}
//	public void setSbsUser(SBSUsers sbsUser) {
//		this.sbsUser = sbsUser;
//	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	@Override
	public String toString() {
		return "CheckingAccDetails [accNo=" + accNo + ", bal=" + bal + ", userId=" + userId + "]";
	}
}

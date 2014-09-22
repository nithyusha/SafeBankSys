package com.ss.entity;

public class SavingAccDetails {

	String accNo;
	long routingNo;
	double bal;

	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public long getRoutingNo() {
		return routingNo;
	}
	public void setRoutingNo(long routingNo) {
		this.routingNo = routingNo;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	@Override
	public String toString() {
		return "SavingAccDetails [accNo=" + accNo + ", bal=" + bal
				 + ", routingNo=" + routingNo + "]";
	}
}

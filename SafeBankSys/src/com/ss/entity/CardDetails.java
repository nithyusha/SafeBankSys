package com.ss.entity;

public class CardDetails {
	String cardNo;
	String expiry;
	int ssv_No;
//	SBSUsers sbsusers;
	int userId;
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public CardDetails() {
		super();
	}
	public CardDetails(String cardNo, String expiry, int ssv_No,
			SBSUsers sbsusers) {
		super();
		this.cardNo = cardNo;
		this.expiry = expiry;
		this.ssv_No = ssv_No;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public int getSsv_No() {
		return ssv_No;
	}
	public void setSsv_No(int ssvNo) {
		ssv_No = ssvNo;
	}
	@Override
	public String toString() {
		return "CardDetails [cardNo=" + cardNo + ", expiry=" + expiry
				+ ", ssv_No=" + ssv_No + ", userId=" + userId + "]";
	}
}

package com.ss.entity;

import java.util.Date;

public class TMTransaction {
	
	private Integer tId;
	private Integer parentTId;
	private String AccNo;
	private String name;
	private String Type;
	private Double amount;
	private Date createdOn;
	private String status;
	private String createdBy;
	private String approver1;
	private String approver2;
	private boolean isCompleted;
	private Date lastModifyOn;
	private String lastModifyBy;
	/**
	 * @return the tId
	 */
	public Integer gettId() {
		return tId;
	}
	/**
	 * @param tId the tId to set
	 */
	public void settId(Integer tId) {
		this.tId = tId;
	}
	/**
	 * @return the parentTId
	 */
	public Integer getParentTId() {
		return parentTId;
	}
	/**
	 * @param parentTId the parentTId to set
	 */
	public void setParentTId(Integer parentTId) {
		this.parentTId = parentTId;
	}
	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return AccNo;
	}
	/**
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the approver1
	 */
	public String getApprover1() {
		return approver1;
	}
	/**
	 * @param approver1 the approver1 to set
	 */
	public void setApprover1(String approver1) {
		this.approver1 = approver1;
	}
	/**
	 * @return the approver2
	 */
	public String getApprover2() {
		return approver2;
	}
	/**
	 * @param approver2 the approver2 to set
	 */
	public void setApprover2(String approver2) {
		this.approver2 = approver2;
	}
	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}
	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	/**
	 * @return the lastModifyOn
	 */
	public Date getLastModifyOn() {
		return lastModifyOn;
	}
	/**
	 * @param lastModifyOn the lastModifyOn to set
	 */
	public void setLastModifyOn(Date lastModifyOn) {
		this.lastModifyOn = lastModifyOn;
	}
	/**
	 * @return the lastModifyBy
	 */
	public String getLastModifyBy() {
		return lastModifyBy;
	}
	/**
	 * @param lastModifyBy the lastModifyBy to set
	 */
	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}
	
	

}

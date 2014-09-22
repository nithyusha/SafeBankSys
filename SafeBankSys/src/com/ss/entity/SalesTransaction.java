package com.ss.entity;

import java.util.Date;

public class SalesTransaction {

	private Integer tId;
	private Integer parentTId;
	private String transcName;
	private String transDesc;
	private String createdBy;
	private String approver1;
	private String approver2;
	private String status;
	private boolean isCompleted;
	private Date creationDate;
	private Date lastModifyOn;
	private String lastModifyBy;
	
	public Date getLastModifyOn() {
		return lastModifyOn;
	}
	public void setLastModifyOn(Date lastModifyOn) {
		this.lastModifyOn = lastModifyOn;
	}
	public String getLastModifyBy() {
		return lastModifyBy;
	}
	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer gettId() {
		return tId;
	}
	public void settId(Integer tId) {
		this.tId = tId;
	}
	public Integer getParentTId() {
		return parentTId;
	}
	public void setParentTId(Integer parentTId) {
		this.parentTId = parentTId;
	}
	public String getTranscName() {
		return transcName;
	}
	public void setTranscName(String transcName) {
		this.transcName = transcName;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getApprover1() {
		return approver1;
	}
	public void setApprover1(String approver1) {
		this.approver1 = approver1;
	}
	public String getApprover2() {
		return approver2;
	}
	public void setApprover2(String approver2) {
		this.approver2 = approver2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}

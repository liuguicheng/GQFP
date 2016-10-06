package com.systemic.gq.pay.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class PayQueryInfo extends PaginationInfo {
	private static final long serialVersionUID = 3517111000510295740L;

	/** 申请人id(会员) */
	private String memberId;
	/**会员编号 */
	private String memnberStaffId;
	/** 开始时间 */
	private Date applyTimeUp;
	/** 结束时间 */
	private Date applyTimeDown;
	/** 记录编号 */
	private String numberId;
	/** 充值数 */
	private Long applyRecordNum;
	/** 充值类型 */
	private String recordType;
	/** 充值前金币 */
	private Double payBeforeNum;
	/** 充值后金币 */
	private Double payAfterNum;
	/** 申请时间 */
	 private Date applyTime;
	/** 申请人ip */
	private String applyLocalHost;
	/** 审核人 */
	private String handleId;
	/** 审核状态 */
	private String applyState;//1充值中 2充值成功 3充值失败
	/** 审核时间 */
	// private Date auditTime;
	/** 备注 */
	private String remark;
	/** 审核人IP */
	private String auditIp;
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getApplyTimeUp() {
		return applyTimeUp;
	}

	public void setApplyTimeUp(Date applyTimeUp) {
		this.applyTimeUp = applyTimeUp;
	}

	public Date getApplyTimeDown() {
		return applyTimeDown;
	}

	public void setApplyTimeDown(Date applyTimeDown) {
		this.applyTimeDown = applyTimeDown;
	}

	/**
	 * @return the numberId
	 */
	public String getNumberId() {
		return numberId;
	}

	/**
	 * @param numberId the numberId to set
	 */
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	/**
	 * @return the applyRecordNum
	 */
	public Long getApplyRecordNum() {
		return applyRecordNum;
	}

	/**
	 * @param applyRecordNum the applyRecordNum to set
	 */
	public void setApplyRecordNum(Long applyRecordNum) {
		this.applyRecordNum = applyRecordNum;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the payBeforeNum
	 */
	public Double getPayBeforeNum() {
		return payBeforeNum;
	}

	/**
	 * @param payBeforeNum the payBeforeNum to set
	 */
	public void setPayBeforeNum(Double payBeforeNum) {
		this.payBeforeNum = payBeforeNum;
	}

	/**
	 * @return the payAfterNum
	 */
	public Double getPayAfterNum() {
		return payAfterNum;
	}

	/**
	 * @param payAfterNum the payAfterNum to set
	 */
	public void setPayAfterNum(Double payAfterNum) {
		this.payAfterNum = payAfterNum;
	}

	/**
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @param applyTime the applyTime to set
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @return the applyLocalHost
	 */
	public String getApplyLocalHost() {
		return applyLocalHost;
	}

	/**
	 * @param applyLocalHost the applyLocalHost to set
	 */
	public void setApplyLocalHost(String applyLocalHost) {
		this.applyLocalHost = applyLocalHost;
	}

	/**
	 * @return the handleId
	 */
	public String getHandleId() {
		return handleId;
	}

	/**
	 * @param handleId the handleId to set
	 */
	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	/**
	 * @return the applyState
	 */
	public String getApplyState() {
		return applyState;
	}

	/**
	 * @param applyState the applyState to set
	 */
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the auditIp
	 */
	public String getAuditIp() {
		return auditIp;
	}

	/**
	 * @param auditIp the auditIp to set
	 */
	public void setAuditIp(String auditIp) {
		this.auditIp = auditIp;
	}

	/**
	 * @return the memnberStaffId
	 */
	public String getMemnberStaffId() {
		return memnberStaffId;
	}

	/**
	 * @param memnberStaffId the memnberStaffId to set
	 */
	public void setMemnberStaffId(String memnberStaffId) {
		this.memnberStaffId = memnberStaffId;
	}

}

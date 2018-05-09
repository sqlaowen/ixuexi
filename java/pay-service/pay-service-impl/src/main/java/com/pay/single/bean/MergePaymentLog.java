package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class MergePaymentLog implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

	public MergePaymentLog() {
	}

	// 合并支付单状态日志id
	private String logId;

	// 合并支付单id
	private String mergePaymentId;

	// 支付单状态id
	private Integer paymentStateId;

	// 备注
	private String paymentNote;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * 合并支付单状态日志id
	 * 
	 * @return
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 合并支付单状态日志id
	 * 
	 * @param logId
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 * 合并支付单id
	 * 
	 * @return
	 */
	public String getMergePaymentId() {
		return mergePaymentId;
	}

	/**
	 * 合并支付单id
	 * 
	 * @param mergePaymentId
	 */
	public void setMergePaymentId(String mergePaymentId) {
		this.mergePaymentId = mergePaymentId;
	}

	/**
	 * 支付单状态id
	 *
	 * @return
	 */
	public Integer getPaymentStateId() {
		return paymentStateId;
	}

	/**
	 * 支付单状态id
	 *
	 * @param paymentStateId
	 */
	public void setPaymentStateId(Integer paymentStateId) {
		this.paymentStateId = paymentStateId;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getPaymentNote() {
		return paymentNote;
	}

	/**
	 * 备注
	 * 
	 * @param paymentNote
	 */
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	/**
	 * create_time
	 *
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * create_time
	 *
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * create_user
	 *
	 * @return
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * create_user
	 *
	 * @param createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}
}

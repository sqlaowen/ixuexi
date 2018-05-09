package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentRefMerge implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

	public PaymentRefMerge() {
	}

	// ref_id
	private String refId;

	// 合并支付id
	private String mergePaymentId;

	// 支付单id
	private String paymentId;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * ref_id
	 *
	 * @return
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * ref_id
	 *
	 * @param refId
	 */
	public void setRefId(String refId) {
		this.refId = refId == null ? null : refId.trim();
	}

	/**
	 * 合并支付id
	 *
	 * @return
	 */
	public String getMergePaymentId() {
		return mergePaymentId;
	}

	/**
	 * 合并支付id
	 *
	 * @param mergePaymentId
	 */
	public void setMergePaymentId(String mergePaymentId) {
		this.mergePaymentId = mergePaymentId == null ? null : mergePaymentId.trim();
	}

	/**
	 * 支付单id
	 *
	 * @return
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * 支付单id
	 *
	 * @param paymentId
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId == null ? null : paymentId.trim();
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

package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentReqLog implements Serializable {

	private static final long serialVersionUID = 3830866173029935684L;

	public PaymentReqLog() {
	}

	// 支付请求记录ID
	private String logId;

	// 订单ids(使用,隔开)
	private String orderIds;

	// 支付请求
	private String payReq;

	// 支付请求返回
	private String payRes;

	// 备注
	private String logNote;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * 支付请求记录ID
	 *
	 * @return
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 支付请求记录ID
	 *
	 * @param logId
	 */
	public void setLogId(String logId) {
		this.logId = logId == null ? null : logId.trim();
	}

	/**
	 * 订单ids(使用,隔开)
	 * 
	 * @return
	 */
	public String getOrderIds() {
		return orderIds;
	}

	/**
	 * 订单ids(使用,隔开)
	 * 
	 * @param orderIds
	 */
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	/**
	 * 支付请求
	 *
	 * @return
	 */
	public String getPayReq() {
		return payReq;
	}

	/**
	 * 支付请求
	 *
	 * @param payReq
	 */
	public void setPayReq(String payReq) {
		this.payReq = payReq == null ? null : payReq.trim();
	}

	/**
	 * 支付请求返回
	 *
	 * @return
	 */
	public String getPayRes() {
		return payRes;
	}

	/**
	 * 支付请求返回
	 *
	 * @param payRes
	 */
	public void setPayRes(String payRes) {
		this.payRes = payRes == null ? null : payRes.trim();
	}

	/**
	 * 备注
	 *
	 * @return
	 */
	public String getLogNote() {
		return logNote;
	}

	/**
	 * 备注
	 *
	 * @param logNote
	 */
	public void setLogNote(String logNote) {
		this.logNote = logNote == null ? null : logNote.trim();
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
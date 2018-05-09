package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentState implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

	public PaymentState() {
	}

	// 支付单状态id
	private Integer paymentStateId;

	// 状态名
	private String stateName;

	// 状态备注
	private String stateNoet;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

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
	 * 状态名
	 *
	 * @return
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * 状态名
	 *
	 * @param stateName
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName == null ? null : stateName.trim();
	}

	/**
	 * 状态备注
	 *
	 * @return
	 */
	public String getStateNoet() {
		return stateNoet;
	}

	/**
	 * 状态备注
	 *
	 * @param stateNoet
	 */
	public void setStateNoet(String stateNoet) {
		this.stateNoet = stateNoet == null ? null : stateNoet.trim();
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

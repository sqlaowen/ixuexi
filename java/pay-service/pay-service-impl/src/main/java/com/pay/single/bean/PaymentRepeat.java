package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentRepeat implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

	public PaymentRepeat() {
	}

	// 重复支付id
	private String reapeatId;

	// 平台商户id
	private String merchantId;

	// 支付单id
	private String paymentId;

	// 支付网关Id
	private String gatewayId;

	// 合并支付id
	private String mergePaymentId;

	// 总金额(分)
	private Integer totalFee;

	// 第三方交易号(ali/wechat流水号)
	private String thirdTradeNo;

	// 实际支付时间
	private Date tradeTime;

	// 买家账号
	private String buyerName;

	// 订单id
	private String orderId;
	
	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * 重复支付id
	 *
	 * @return
	 */
	public String getReapeatId() {
		return reapeatId;
	}

	/**
	 * 重复支付id
	 *
	 * @param reapeatId
	 */
	public void setReapeatId(String reapeatId) {
		this.reapeatId = reapeatId == null ? null : reapeatId.trim();
	}

	/**
	 * 平台商户id
	 *
	 * @return
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * 平台商户id
	 *
	 * @param merchantId
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId == null ? null : merchantId.trim();
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
	 * 支付网关Id
	 *
	 * @return
	 */
	public String getGatewayId() {
		return gatewayId;
	}

	/**
	 * 支付网关Id
	 *
	 * @param gatewayId
	 */
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId == null ? null : gatewayId.trim();
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
	 * 总金额(分)
	 *
	 * @return
	 */
	public Integer getTotalFee() {
		return totalFee;
	}

	/**
	 * 总金额(分)
	 *
	 * @param totalFee
	 */
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 第三方交易号(ali/wechat流水号)
	 *
	 * @return
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	/**
	 * 第三方交易号(ali/wechat流水号)
	 *
	 * @param thirdTradeNo
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
	}

	/**
	 * 实际支付时间
	 *
	 * @return
	 */
	public Date getTradeTime() {
		return tradeTime;
	}

	/**
	 * 实际支付时间
	 *
	 * @param tradeTime
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
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
	
	/**
	 * 买家账号
	 * 
	 * @return
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * 买家账号
	 * 
	 * @param buyerName
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	

	/**
	 * 订单id
	 *
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 订单id
	 *
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}
}

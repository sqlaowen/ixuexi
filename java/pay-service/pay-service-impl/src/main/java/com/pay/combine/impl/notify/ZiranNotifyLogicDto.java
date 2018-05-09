package com.pay.combine.impl.notify;

import java.io.Serializable;
import java.util.Date;

public class ZiranNotifyLogicDto implements Serializable {

	private static final long serialVersionUID = 4171752768005704170L;

	public ZiranNotifyLogicDto() {
	}

	// 合并支付id
	private String mergePaymentId;

	// 支付单状态id [com.pay.consts.PaymentStateConst]
	private Integer paymentStateId;

	// 第三方交易号(ali/wechat流水号)
	private String thirdTradeNo;

	// 交易关闭时间
	private Date tradeCloseTime;

	// 实际支付时间
	private Date tradeTime;

	// 支付网关Id
	private String gatewayId;

	// 银行简码
	private String bankCode;

	// 备注
	private String comments;

	// 买家
	private String buyerName;

	// 总金额(分)
	private Long totalFee;

	// 支付来源 == 队列主题
	private String paymentSource;

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
	 * 支付单状态id
	 * 
	 * @see com.pay.consts.PaymentStateConst
	 *
	 * @return
	 */
	public Integer getPaymentStateId() {
		return paymentStateId;
	}

	/**
	 * 支付单状态id
	 * 
	 * @see com.pay.consts.PaymentStateConst
	 *
	 * @param paymentStateId
	 */
	public void setPaymentStateId(Integer paymentStateId) {
		this.paymentStateId = paymentStateId;
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
	 * 交易关闭时间
	 *
	 * @return
	 */
	public Date getTradeCloseTime() {
		return tradeCloseTime;
	}

	/**
	 * 交易关闭时间
	 *
	 * @param tradeCloseTime
	 */
	public void setTradeCloseTime(Date tradeCloseTime) {
		this.tradeCloseTime = tradeCloseTime;
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
	 * 银行简码
	 *
	 * @return
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * 银行简码
	 *
	 * @param bankCode
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode == null ? null : bankCode.trim();
	}

	/**
	 * 总金额(分)
	 *
	 * @return
	 */
	public Long getTotalFee() {
		return totalFee;
	}

	/**
	 * 总金额(分)
	 *
	 * @param totalFee
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 备注
	 * 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 买家
	 * 
	 * @return
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * 买家
	 * 
	 * @param buyerName
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 * 支付来源 == 队列主题
	 * 
	 * @return
	 */
	public String getPaymentSource() {
		return paymentSource;
	}

	/**
	 * 支付来源 == 队列主题
	 * 
	 * @param paymentSource
	 */
	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

}

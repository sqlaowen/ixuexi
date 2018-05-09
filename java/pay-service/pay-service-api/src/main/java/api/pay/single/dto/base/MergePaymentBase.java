package api.pay.single.dto.base;

import java.util.Date;

public abstract class MergePaymentBase {

	// 平台商户id
	private String merchantId;

	// 支付网关Id
	private String gatewayId;

	// 总金额(分)
	private Long totalFee;

	// 支付返回url
	private String returnUrl;

	// 支付通知url
	private String notifyUrl;

	// 银行简码
	private String bankCode;

	// 超时时间
	private Date timeOut;

	// 支付单来源
	private String paymentSource;

	// openid微信H5支付使用
	private String openid;

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
	 * 支付返回url
	 *
	 * @return
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * 支付返回url
	 *
	 * @param returnUrl
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl == null ? null : returnUrl.trim();
	}

	/**
	 * 支付通知url
	 *
	 * @return
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * 支付通知url
	 *
	 * @param notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
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
	 * 超时时间
	 *
	 * @return
	 */
	public Date getTimeOut() {
		return timeOut;
	}

	/**
	 * 超时时间
	 *
	 * @param timeOut
	 */
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * 支付单来源
	 *
	 * @return
	 */
	public String getPaymentSource() {
		return paymentSource;
	}

	/**
	 * 支付单来源
	 *
	 * @param paymentSource
	 */
	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource == null ? null : paymentSource.trim();
	}

	/**
	 * openid微信H5支付使用
	 * 
	 * @return
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * openid微信H5支付使用
	 * 
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

}

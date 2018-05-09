package api.pay.single.dto.base;

import java.util.Date;

public abstract class PaymentBase {

	// 平台商户id
	private String merchantId;

	// 支付网关Id
	private String gatewayId;

	// 总金额(分)
	private Long totalFee;

	// 商品名称
	private String spName;

	// 商品描述
	private String spDetail;

	// 超时时间
	private Date timeOut;

	// 支付返回url
	private String returnUrl;

	// 支付通知url
	private String notifyUrl;

	// 公用回传参数
	private String extraCommonParam;

	// 来源ip
	private String fromIp;

	// 支付单来源 [com.pay.consts.PaymentSourceConst]
	private String paymentSource;

	// 银行简码
	private String bankCode;

	// 备注
	private String paymentNote;
	
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
	 * 商品名称
	 *
	 * @return
	 */
	public String getSpName() {
		return spName;
	}

	/**
	 * 商品名称
	 *
	 * @param spName
	 */
	public void setSpName(String spName) {
		this.spName = spName == null ? null : spName.trim();
	}

	/**
	 * 商品描述
	 *
	 * @return
	 */
	public String getSpDetail() {
		return spDetail;
	}

	/**
	 * 商品描述
	 *
	 * @param spDetail
	 */
	public void setSpDetail(String spDetail) {
		this.spDetail = spDetail == null ? null : spDetail.trim();
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
	 * 公用回传参数
	 *
	 * @return
	 */
	public String getExtraCommonParam() {
		return extraCommonParam;
	}

	/**
	 * 公用回传参数
	 *
	 * @param extraCommonParam
	 */
	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
	}

	/**
	 * 来源ip
	 *
	 * @return
	 */
	public String getFromIp() {
		return fromIp;
	}

	/**
	 * 来源ip
	 *
	 * @param fromIp
	 */
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp == null ? null : fromIp.trim();
	}

	/**
	 * 支付单来源
	 * 
	 * @see com.pay.consts.PaymentSourceConst
	 * @return
	 */
	public String getPaymentSource() {
		return paymentSource;
	}

	/**
	 * 支付单来源
	 * 
	 * @see com.pay.consts.PaymentSourceConst
	 * @param paymentSource
	 */
	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource == null ? null : paymentSource.trim();
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
		this.paymentNote = paymentNote == null ? null : paymentNote.trim();
	}

}

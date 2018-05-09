package api.pay.single.dto.base;

import java.util.Date;

public abstract class AliNotifyBase {

	// 合并支付id
	private String mergePaymentId;

	// 商户订单号
	private String outTradeNo;

	// 通知时间
	private Date notifyTime;

	// 通知类型
	private String notifyType;

	// 通知校验ID
	private String notifyId;

	// 签名方式
	private String signType;

	// 签名
	private String sign;

	// 商品名称
	private String subject;

	// 支付类型
	private String paymentType;

	// 支付宝交易号
	private String tradeNo;

	// 交易状态
	private String tradeStatus;

	// 交易创建时间
	private Date gmtCreate;

	// 交易付款时间
	private Date gmtPayment;

	// 交易关闭时间
	private Date gmtClose;

	// 退款状态
	private String refundStatus;

	// 退款时间
	private Date gmtRefund;

	// 卖家支付宝账号
	private String sellerEmail;

	// 买家支付宝账号
	private String buyerEmail;

	// 卖家partnerId
	private String sellerId;

	// 买家partnerId
	private String buyerId;

	// 商品单价(分)
	private Integer price;

	// 交易金额(分)
	private Long totalFee;

	// 购买数量
	private Integer quantity;

	// 商品描述
	private String body;

	// 折扣
	private String discount;

	// 是否调整总价
	private String isTotalFeeAdjust;

	// 是否使用红包
	private String useCoupon;

	// 错误代码
	private String errorCode;

	// 网银流水
	private String bankSeqNo;

	// 公用回传参数
	private String extraCommonParam;

	// 支付渠道组合信息
	private String outChannelType;

	// 支付金额组合信息
	private String outChannelAmount;

	// 实际支付渠道
	private String outChannelInst;

	// 异步通知参数
	private String notifyValue;

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
	 * 商户订单号
	 *
	 * @return
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 商户订单号
	 *
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
	}

	/**
	 * 通知时间
	 *
	 * @return
	 */
	public Date getNotifyTime() {
		return notifyTime;
	}

	/**
	 * 通知时间
	 *
	 * @param notifyTime
	 */
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	/**
	 * 通知类型
	 *
	 * @return
	 */
	public String getNotifyType() {
		return notifyType;
	}

	/**
	 * 通知类型
	 *
	 * @param notifyType
	 */
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType == null ? null : notifyType.trim();
	}

	/**
	 * 通知校验ID
	 *
	 * @return
	 */
	public String getNotifyId() {
		return notifyId;
	}

	/**
	 * 通知校验ID
	 *
	 * @param notifyId
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId == null ? null : notifyId.trim();
	}

	/**
	 * 签名方式
	 *
	 * @return
	 */
	public String getSignType() {
		return signType;
	}

	/**
	 * 签名方式
	 *
	 * @param signType
	 */
	public void setSignType(String signType) {
		this.signType = signType == null ? null : signType.trim();
	}

	/**
	 * 签名
	 *
	 * @return
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * 签名
	 *
	 * @param sign
	 */
	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}

	/**
	 * 商品名称
	 *
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 商品名称
	 *
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	/**
	 * 支付类型
	 *
	 * @return
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * 支付类型
	 *
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType == null ? null : paymentType.trim();
	}

	/**
	 * 支付宝交易号
	 *
	 * @return
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * 支付宝交易号
	 *
	 * @param tradeNo
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo == null ? null : tradeNo.trim();
	}

	/**
	 * 交易状态
	 *
	 * @return
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}

	/**
	 * 交易状态
	 *
	 * @param tradeStatus
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
	}

	/**
	 * 交易创建时间
	 *
	 * @return
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * 交易创建时间
	 *
	 * @param gmtCreate
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * 交易付款时间
	 *
	 * @return
	 */
	public Date getGmtPayment() {
		return gmtPayment;
	}

	/**
	 * 交易付款时间
	 *
	 * @param gmtPayment
	 */
	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	/**
	 * 交易关闭时间
	 *
	 * @return
	 */
	public Date getGmtClose() {
		return gmtClose;
	}

	/**
	 * 交易关闭时间
	 *
	 * @param gmtClose
	 */
	public void setGmtClose(Date gmtClose) {
		this.gmtClose = gmtClose;
	}

	/**
	 * 退款状态
	 *
	 * @return
	 */
	public String getRefundStatus() {
		return refundStatus;
	}

	/**
	 * 退款状态
	 *
	 * @param refundStatus
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus == null ? null : refundStatus.trim();
	}

	/**
	 * 退款时间
	 *
	 * @return
	 */
	public Date getGmtRefund() {
		return gmtRefund;
	}

	/**
	 * 退款时间
	 *
	 * @param gmtRefund
	 */
	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	/**
	 * 卖家支付宝账号
	 *
	 * @return
	 */
	public String getSellerEmail() {
		return sellerEmail;
	}

	/**
	 * 卖家支付宝账号
	 *
	 * @param sellerEmail
	 */
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
	}

	/**
	 * 买家支付宝账号
	 *
	 * @return
	 */
	public String getBuyerEmail() {
		return buyerEmail;
	}

	/**
	 * 买家支付宝账号
	 *
	 * @param buyerEmail
	 */
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
	}

	/**
	 * 卖家partnerId
	 *
	 * @return
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * 卖家partnerId
	 *
	 * @param sellerId
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId == null ? null : sellerId.trim();
	}

	/**
	 * 买家partnerId
	 *
	 * @return
	 */
	public String getBuyerId() {
		return buyerId;
	}

	/**
	 * 买家partnerId
	 *
	 * @param buyerId
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId == null ? null : buyerId.trim();
	}

	/**
	 * 商品单价(分)
	 *
	 * @return
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * 商品单价(分)
	 *
	 * @param price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * 交易金额(分)
	 *
	 * @return
	 */
	public Long getTotalFee() {
		return totalFee;
	}

	/**
	 * 交易金额(分)
	 *
	 * @param totalFee
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 购买数量
	 *
	 * @return
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 购买数量
	 *
	 * @param quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 商品描述
	 *
	 * @return
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 商品描述
	 *
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body == null ? null : body.trim();
	}

	/**
	 * 折扣
	 *
	 * @return
	 */
	public String getDiscount() {
		return discount;
	}

	/**
	 * 折扣
	 *
	 * @param discount
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/**
	 * 是否调整总价
	 *
	 * @return
	 */
	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}

	/**
	 * 是否调整总价
	 *
	 * @param isTotalFeeAdjust
	 */
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust == null ? null : isTotalFeeAdjust.trim();
	}

	/**
	 * 是否使用红包
	 *
	 * @return
	 */
	public String getUseCoupon() {
		return useCoupon;
	}

	/**
	 * 是否使用红包
	 *
	 * @param useCoupon
	 */
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon == null ? null : useCoupon.trim();
	}

	/**
	 * 错误代码
	 *
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 错误代码
	 *
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode == null ? null : errorCode.trim();
	}

	/**
	 * 网银流水
	 *
	 * @return
	 */
	public String getBankSeqNo() {
		return bankSeqNo;
	}

	/**
	 * 网银流水
	 *
	 * @param bankSeqNo
	 */
	public void setBankSeqNo(String bankSeqNo) {
		this.bankSeqNo = bankSeqNo == null ? null : bankSeqNo.trim();
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
	 * 支付渠道组合信息
	 *
	 * @return
	 */
	public String getOutChannelType() {
		return outChannelType;
	}

	/**
	 * 支付渠道组合信息
	 *
	 * @param outChannelType
	 */
	public void setOutChannelType(String outChannelType) {
		this.outChannelType = outChannelType == null ? null : outChannelType.trim();
	}

	/**
	 * 支付金额组合信息
	 *
	 * @return
	 */
	public String getOutChannelAmount() {
		return outChannelAmount;
	}

	/**
	 * 支付金额组合信息
	 *
	 * @param outChannelAmount
	 */
	public void setOutChannelAmount(String outChannelAmount) {
		this.outChannelAmount = outChannelAmount == null ? null : outChannelAmount.trim();
	}

	/**
	 * 实际支付渠道
	 *
	 * @return
	 */
	public String getOutChannelInst() {
		return outChannelInst;
	}

	/**
	 * 实际支付渠道
	 *
	 * @param outChannelInst
	 */
	public void setOutChannelInst(String outChannelInst) {
		this.outChannelInst = outChannelInst == null ? null : outChannelInst.trim();
	}

	/**
	 * 异步通知参数
	 *
	 * @return
	 */
	public String getNotifyValue() {
		return notifyValue;
	}

	/**
	 * 异步通知参数
	 *
	 * @param notifyValue
	 */
	public void setNotifyValue(String notifyValue) {
		this.notifyValue = notifyValue == null ? null : notifyValue.trim();
	}

}

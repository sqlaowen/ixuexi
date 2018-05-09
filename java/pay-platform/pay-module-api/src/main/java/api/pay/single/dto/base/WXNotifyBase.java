package api.pay.single.dto.base;

public abstract class WXNotifyBase {

  // 合并支付id
  private String mergePaymentId;

  // 返回状态码
  private String returnCode;

  // 返回信息
  private String returnMsg;

  // 公众账号id
  private String appid;

  // 商户号
  private String mchId;

  // 设备号
  private String deviceInfo;

  // 随机字符串
  private String nonceStr;

  // 签名
  private String sign;

  // 业务结果
  private String resultCode;

  // 错误代码
  private String errCode;

  // 错误代码描述
  private String errCodeDes;

  // 用户标识
  private String openid;

  // 是否关注公众账号
  private String isSubscribe;

  // 交易类型
  private String tradeType;

  // 付款银行
  private String bankType;

  // 总金额(分)
  private Integer totalFee;

  // 货币种类
  private String feeType;

  // 现金支付金额(分)
  private Integer cashFee;

  // 现金支付货币类型
  private String cashFeeType;

  // 代金券立减优惠金额(分)
  private Integer couponFee;

  // 代金券或立减优惠使用数量
  private Integer couponCount;

  // 微信支付订单号
  private String transactionId;

  // 商户订单号
  private String outTradeNo;

  // 商家数据包
  private String attach;

  // 支付完成时间
  private String timeEnd;

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
   * 返回状态码
   *
   * @return
   */
  public String getReturnCode() {
    return returnCode;
  }

  /**
   * 返回状态码
   *
   * @param returnCode
   */
  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode == null ? null : returnCode.trim();
  }

  /**
   * 返回信息
   *
   * @return
   */
  public String getReturnMsg() {
    return returnMsg;
  }

  /**
   * 返回信息
   *
   * @param returnMsg
   */
  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg == null ? null : returnMsg.trim();
  }

  /**
   * 公众账号id
   *
   * @return
   */
  public String getAppid() {
    return appid;
  }

  /**
   * 公众账号id
   *
   * @param appid
   */
  public void setAppid(String appid) {
    this.appid = appid == null ? null : appid.trim();
  }

  /**
   * 商户号
   *
   * @return
   */
  public String getMchId() {
    return mchId;
  }

  /**
   * 商户号
   *
   * @param mchId
   */
  public void setMchId(String mchId) {
    this.mchId = mchId == null ? null : mchId.trim();
  }

  /**
   * 设备号
   *
   * @return
   */
  public String getDeviceInfo() {
    return deviceInfo;
  }

  /**
   * 设备号
   *
   * @param deviceInfo
   */
  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
  }

  /**
   * 随机字符串
   *
   * @return
   */
  public String getNonceStr() {
    return nonceStr;
  }

  /**
   * 随机字符串
   *
   * @param nonceStr
   */
  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr == null ? null : nonceStr.trim();
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
   * 业务结果
   *
   * @return
   */
  public String getResultCode() {
    return resultCode;
  }

  /**
   * 业务结果
   *
   * @param resultCode
   */
  public void setResultCode(String resultCode) {
    this.resultCode = resultCode == null ? null : resultCode.trim();
  }

  /**
   * 错误代码
   *
   * @return
   */
  public String getErrCode() {
    return errCode;
  }

  /**
   * 错误代码
   *
   * @param errCode
   */
  public void setErrCode(String errCode) {
    this.errCode = errCode == null ? null : errCode.trim();
  }

  /**
   * 错误代码描述
   *
   * @return
   */
  public String getErrCodeDes() {
    return errCodeDes;
  }

  /**
   * 错误代码描述
   *
   * @param errCodeDes
   */
  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes == null ? null : errCodeDes.trim();
  }

  /**
   * 用户标识
   *
   * @return
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * 用户标识
   *
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
  }

  /**
   * 是否关注公众账号
   *
   * @return
   */
  public String getIsSubscribe() {
    return isSubscribe;
  }

  /**
   * 是否关注公众账号
   *
   * @param isSubscribe
   */
  public void setIsSubscribe(String isSubscribe) {
    this.isSubscribe = isSubscribe == null ? null : isSubscribe.trim();
  }

  /**
   * 交易类型
   *
   * @return
   */
  public String getTradeType() {
    return tradeType;
  }

  /**
   * 交易类型
   *
   * @param tradeType
   */
  public void setTradeType(String tradeType) {
    this.tradeType = tradeType == null ? null : tradeType.trim();
  }

  /**
   * 付款银行
   *
   * @return
   */
  public String getBankType() {
    return bankType;
  }

  /**
   * 付款银行
   *
   * @param bankType
   */
  public void setBankType(String bankType) {
    this.bankType = bankType == null ? null : bankType.trim();
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
   * 货币种类
   *
   * @return
   */
  public String getFeeType() {
    return feeType;
  }

  /**
   * 货币种类
   *
   * @param feeType
   */
  public void setFeeType(String feeType) {
    this.feeType = feeType == null ? null : feeType.trim();
  }

  /**
   * 现金支付金额(分)
   *
   * @return
   */
  public Integer getCashFee() {
    return cashFee;
  }

  /**
   * 现金支付金额(分)
   *
   * @param cashFee
   */
  public void setCashFee(Integer cashFee) {
    this.cashFee = cashFee;
  }

  /**
   * 现金支付货币类型
   *
   * @return
   */
  public String getCashFeeType() {
    return cashFeeType;
  }

  /**
   * 现金支付货币类型
   *
   * @param cashFeeType
   */
  public void setCashFeeType(String cashFeeType) {
    this.cashFeeType = cashFeeType == null ? null : cashFeeType.trim();
  }

  /**
   * 代金券立减优惠金额(分)
   *
   * @return
   */
  public Integer getCouponFee() {
    return couponFee;
  }

  /**
   * 代金券立减优惠金额(分)
   *
   * @param couponFee
   */
  public void setCouponFee(Integer couponFee) {
    this.couponFee = couponFee;
  }

  /**
   * 代金券或立减优惠使用数量
   *
   * @return
   */
  public Integer getCouponCount() {
    return couponCount;
  }

  /**
   * 代金券或立减优惠使用数量
   *
   * @param couponCount
   */
  public void setCouponCount(Integer couponCount) {
    this.couponCount = couponCount;
  }

  /**
   * 微信支付订单号
   *
   * @return
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * 微信支付订单号
   *
   * @param transactionId
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId == null ? null : transactionId.trim();
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
   * 商家数据包
   *
   * @return
   */
  public String getAttach() {
    return attach;
  }

  /**
   * 商家数据包
   *
   * @param attach
   */
  public void setAttach(String attach) {
    this.attach = attach == null ? null : attach.trim();
  }

  /**
   * 支付完成时间
   *
   * @return
   */
  public String getTimeEnd() {
    return timeEnd;
  }

  /**
   * 支付完成时间
   *
   * @param timeEnd
   */
  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd == null ? null : timeEnd.trim();
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

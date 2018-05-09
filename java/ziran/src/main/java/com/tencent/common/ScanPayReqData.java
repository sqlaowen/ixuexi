package com.tencent.common;

import java.io.Serializable;

/**
 * 请求扫码支付API需要提交的数据
 */
public class ScanPayReqData implements Serializable {

  private static final long serialVersionUID = -7814320478592586128L;
  // 每个字段具体的意思请查看API文档
  private String appid;
  private String mch_id;
  // 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
  private String device_info;
  private String nonce_str;
  private String sign;
  private String body;
  private String detail;
  private String attach;
  private String out_trade_no;
  private String fee_type;
  private Integer total_fee;
  private String spbill_create_ip;
  private String time_start;
  private String time_expire;
  private String goods_tag;
  private String notify_url;
  private String trade_type;
  private String product_id;
  private String limit_pay;
  private String openid;

//  /**
//   * 必输项构造
//   * 
//   * @param body 商品描述:要支付的商品的描述信息，用户会在支付成功页面里看到这个信息 String(32)
//   * @param outTradeNo 商户订单号:商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一 String(32)
//   * @param totalFee 订单总金额，单位为“分”，只能整数
//   * @param spBillCreateIP 订单生成的机器IP String(16)
//   * @param notifyUrl 接收微信支付异步通知回调地址 String(256)
//   * @param tradeType 交易类型：JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、WAP--手机浏览器H5支付，MICROPAY--刷卡支付
//   */
//  public ScanPayReqData(String body, String outTradeNo, int totalFee, String spBillCreateIP,
//      String notifyUrl, String tradeType) {
//
//    // 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
//    setBody(body);
//
//    // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
//    setOut_trade_no(outTradeNo);
//
//    // 订单总金额，单位为“分”，只能整数
//    setTotal_fee(totalFee);
//
//    // 订单生成的机器IP
//    setSpbill_create_ip(spBillCreateIP);
//
//    // 接收微信支付异步通知回调地址
//    setNotify_url(notifyUrl);
//
//    // 交易类型
//    setTrade_type(tradeType);
//  }

  /**
   * （必输）公众账号ID
   * 
   * @return
   */
  public String getAppid() {
    return appid;
  }

  /**
   * （必输）公众账号ID 微信分配的公众账号ID（企业号corpid即为此appId）
   * 
   * @param appid
   */
  public void setAppid(String appid) {
    this.appid = appid;
  }

  /**
   * （必输）商户号
   * 
   * @return
   */
  public String getMch_id() {
    return mch_id;
  }

  /**
   * （必输）商户号 微信支付分配的商户号
   * 
   * @param mch_id
   */
  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  /**
   * 设备号
   * 
   * @return
   */
  public String getDevice_info() {
    return device_info;
  }

  /**
   * 设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
   * 
   * @param device_info
   */
  public void setDevice_info(String device_info) {
    this.device_info = device_info;
  }

  /**
   * （必输）随机字符串
   * 
   * @return
   */
  public String getNonce_str() {
    return nonce_str;
  }

  /**
   * （必输）随机字符串 不长于32位
   * 
   * @param nonce_str
   */
  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
  }

  /**
   * （必输）签名
   * 
   * @return
   */
  public String getSign() {
    return sign;
  }

  /**
   * （必输）签名
   * 
   * @param sign
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  /**
   * （必输）商品描述
   * 
   * @return
   */
  public String getBody() {
    return body;
  }

  /**
   * （必输）商品描述
   * 
   * @param body
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * 商品详情
   * 
   * @return
   */
  public String getDetail() {
    return detail;
  }

  /**
   * 商品详情
   * 
   * @param detail
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * 附加数据
   * 
   * @return
   */
  public String getAttach() {
    return attach;
  }

  /**
   * 附加数据 在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   * 
   * @param attach
   */
  public void setAttach(String attach) {
    this.attach = attach;
  }

  /**
   * （必输）商户订单号
   * 
   * @return
   */
  public String getOut_trade_no() {
    return out_trade_no;
  }

  /**
   * （必输）商户订单号 商户系统内部的订单号,32个字符内
   * 
   * @param out_trade_no
   */
  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  /**
   * 货币类型
   * 
   * @return
   */
  public String getFee_type() {
    return fee_type;
  }

  /**
   * 货币类型 符合ISO 4217标准的三位字母代码，默认人民币：CNY
   * 
   * @param fee_type
   */
  public void setFee_type(String fee_type) {
    this.fee_type = fee_type;
  }

  /**
   * （必输）总金额(分)
   * 
   * @return
   */
  public Integer getTotal_fee() {
    return total_fee;
  }

  /**
   * （必输）总金额(分)
   * 
   * @param total_fee
   */
  public void setTotal_fee(Integer total_fee) {
    this.total_fee = total_fee;
  }

  /**
   * （必输）终端IP
   * 
   * @return
   */
  public String getSpbill_create_ip() {
    return spbill_create_ip;
  }

  /**
   * （必输）终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
   * 
   * @param spbill_create_ip
   */
  public void setSpbill_create_ip(String spbill_create_ip) {
    this.spbill_create_ip = spbill_create_ip;
  }

  /**
   * 交易起始时间
   * 
   * @return
   */
  public String getTime_start() {
    return time_start;
  }

  /**
   * 交易起始时间 订单生成时间，格式为yyyyMMddHHmmss
   * 
   * @param time_start
   */
  public void setTime_start(String time_start) {
    this.time_start = time_start;
  }

  /**
   * 交易结束时间
   * 
   * @return
   */
  public String getTime_expire() {
    return time_expire;
  }

  /**
   * 交易结束时间 订单失效时间，格式为yyyyMMddHHmmss，
   * 
   * @param time_expire
   */
  public void setTime_expire(String time_expire) {
    this.time_expire = time_expire;
  }

  /**
   * 商品标记
   * 
   * @return
   */
  public String getGoods_tag() {
    return goods_tag;
  }

  /**
   * 商品标记 代金券或立减优惠功能的参数
   * 
   * @param goods_tag
   */
  public void setGoods_tag(String goods_tag) {
    this.goods_tag = goods_tag;
  }

  /**
   * （必输）通知地址
   * 
   * @return
   */
  public String getNotify_url() {
    return notify_url;
  }

  /**
   * （必输）通知地址
   * 
   * @param notify_url
   */
  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }

  /**
   * （必输）交易类型
   * 
   * @return
   */
  public String getTrade_type() {
    return trade_type;
  }

  /**
   * （必输）交易类型 JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、WAP--手机浏览器H5支付，MICROPAY--刷卡支付
   * 
   * @param trade_type
   */
  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

  /**
   * 商品ID
   * 
   * @return
   */
  public String getProduct_id() {
    return product_id;
  }

  /**
   * 商品ID trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
   * 
   * @param product_id
   */
  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  /**
   * 指定支付方式
   * 
   * @return
   */
  public String getLimit_pay() {
    return limit_pay;
  }

  /**
   * 指定支付方式
   * 
   * @param limit_pay
   */
  public void setLimit_pay(String limit_pay) {
    this.limit_pay = limit_pay;
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
   * 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
   * 
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }

}

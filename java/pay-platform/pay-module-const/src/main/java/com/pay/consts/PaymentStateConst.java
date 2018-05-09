package com.pay.consts;

/**
 * 支付单枚举状态
 *
 */
public final class PaymentStateConst {

  private PaymentStateConst(){}
  
  /**
   * 交易创建
   * 此状态应该进行异步业务处理
   */
  public static final int TRADE_CREATE = 1;

  /**
   * 支付成功
   */
  public static final int TRADE_SUCCESS = 2;

  /**
   * 支付失败
   */
  public static final int TRADE_FAIL = 3;
  
  /**
   * 支付失败-支付单过期未支付
   */
  public static final int TRADE_TIMEOUT = 31;
  
  /**
   * 交易关闭
   * 有可能在订单关闭后再支付,此状态应该进行异步业务处理
   */
  public static final int TRADE_CLOSE=4;

  /**
   * 交易关闭-交易不存在(支付宝提示交易不存在)
   * 有可能在订单关闭后再支付,此状态应该进行异步业务处理
   */
  public static final int TRADE_NOT_EXIST = 41;
  
}

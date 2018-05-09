package com.pay.consts;

public final class GatewayCodeConst {

  private GatewayCodeConst() {}

  /**
   * ali 即时到账/纯网关 [create_direct_pay_by_user]
   */
  public static final String GATEWAY_ALIDIRECTPAY = "ALIDIRECTPAY";

//  /**
//   * ali 合并支付[trade_merge_pay_by_user]
//   */
//  public static final String GATEWAY_ALIMERGEPAY = "ALIMERGEPAY";

  /**
   * ali APP支付[mobile.securitypay.pay]
   */
  public static final String GATEWAY_ALIMOBILEPAY = "ALIMOBILEPAY";

  /**
   * wx (PC)原生扫码支付[NATIVE]
   */
  public static final String GATEWAY_WXNATIVEPAY = "WXNATIVE";

  /**
   * wx H5页面支付[JSAPI]
   */
  public static final String GATEWAY_WXJSAPIPAY = "WXJSAPI";

  /**
   * wx APP支付[APP]
   */
  public static final String GATEWAY_WXAPPPAY = "WXAPP";
  
  /**
   * wx支付工具--企业付款
   */
  public static final String GATEWAY_WXMCHPAY = "WXMCHPAY";
}

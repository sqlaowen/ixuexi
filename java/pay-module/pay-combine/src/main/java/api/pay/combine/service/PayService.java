package api.pay.combine.service;

import api.pay.combine.dto.AliDirectPayDto;
import api.pay.combine.dto.AliMergePayDto;
import api.pay.combine.dto.ResData;

public interface PayService {

  /**
   * 纯网关支付接口:create_direct_pay_by_user
   * code:[success:成功;  fail:失败]
   * msg:失败提示
   * T:成功时返回的form表单
   * 
   * @param aliDirectPayDto :AliDirectPayDto
   * @return ResData<String>
   */
  public ResData<String> aliDirectPay(AliDirectPayDto aliDirectPayDto);
  
  /**
   * 合并支付接口:trade_merge_pay_by_user 
   * code:[success:成功;  fail:失败]
   * msg:失败提示
   * T:成功时返回的form表单
   * 
   * @param aliMergePayDto :AliMergePayDto
   * @return ResData<String>
   */
  public ResData<String> aliMergePay(AliMergePayDto aliMergePayDto);
  
  /**
   * 单笔交易查询接口:single_trade_query
   * 
   * @param orderId :商户订单号
   * @param paymentId :平台支付单流水号
   * @return String
   */
  public String aliTradeQuery(String orderId, String paymentId);
  
  /**
   * 交易关闭接口:close_trade
   * code:[T:成功;  F:失败]
   * msg:失败提示
   * 
   * @param orderId :商户订单号
   * @param paymentId :平台支付单流水号
   * @return ResData<String>
   */
  public ResData<String> aliCloseTrade(String orderId, String paymentId);
}

package com.pay.combine.validate;

import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.pay.single.dto.req.CreateAliNotifyReqDto;

public class BuildAliNotifyReqDto {

  private static Logger log = LoggerFactory.getLogger(BuildAliNotifyReqDto.class);

  /**
   * 构建CreateAliNotifyReqDto
   * 
   * @param map :Map<String, String>
   * @return CreateAliNotifyReqDto
   */
  public static CreateAliNotifyReqDto bulidAliNotifyReqDto(Map<String, String> map) {

    CreateAliNotifyReqDto dto = new CreateAliNotifyReqDto();

    if (StringUtils.isNotBlank(map.get("out_trade_no"))) {
      dto.setPaymentId(map.get("out_trade_no"));
    }
    if (StringUtils.isNotBlank(map.get("sign"))) {
      dto.setSign(map.get("sign"));
    }
    if (StringUtils.isNotBlank(map.get("sign_type"))) {
      dto.setSignType(map.get("sign_type"));
    }
    if (StringUtils.isNotBlank(map.get("notify_id"))) {
      dto.setNotifyId(map.get("notify_id"));
    }
    if (StringUtils.isNotBlank(map.get("notify_time"))) {
      try {
        dto.setNotifyTime(DateUtils.parseDate(map.get("notify_time"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知notify_time异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("notify_type"))) {
      dto.setNotifyType(map.get("notify_type"));
    }
    if (StringUtils.isNotBlank(map.get("subject"))) {
      dto.setSubject(map.get("subject"));
    }
    if (StringUtils.isNotBlank(map.get("payment_type"))) {
      dto.setPaymentType(map.get("payment_type"));
    }
    if (StringUtils.isNotBlank(map.get("trade_no"))) {
      dto.setTradeNo(map.get("trade_no"));
    }
    if (StringUtils.isNotBlank(map.get("trade_status"))) {
      dto.setTradeStatus(map.get("trade_status").toUpperCase());
    }
    if (StringUtils.isNotBlank(map.get("gmt_create"))) {
      try {
        dto.setGmtCreate(DateUtils.parseDate(map.get("gmt_create"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_create转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("gmt_payment"))) {
      try {
        dto.setGmtPayment(DateUtils.parseDate(map.get("gmt_payment"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_payment转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("gmt_close"))) {
      try {
        dto.setGmtClose(DateUtils.parseDate(map.get("gmt_close"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_close转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("refund_status"))) {
      dto.setTradeStatus(map.get("refund_status"));
    }
    if (StringUtils.isNotBlank(map.get("gmt_refund"))) {
      try {
        dto.setGmtRefund(DateUtils.parseDate(map.get("gmt_refund"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_refund转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("buyer_email"))) {
      dto.setBuyerEmail(map.get("buyer_email"));
    }
    if (StringUtils.isNotBlank(map.get("buyer_id"))) {
      dto.setBuyerId(map.get("buyer_id"));
    }
    if (StringUtils.isNotBlank(map.get("seller_email"))) {
      dto.setSellerEmail(map.get("seller_email"));
    }
    if (StringUtils.isNotBlank(map.get("seller_id"))) {
      dto.setSellerId(map.get("seller_id"));
    }
    if (StringUtils.isNotBlank(map.get("price"))) {
      Long price = (long) (Double.parseDouble(map.get("price")) * 100);
      dto.setPrice(price);
    }
    if (StringUtils.isNotBlank(map.get("total_fee"))) {
      Long totalFee = (long) (Double.parseDouble(map.get("total_fee")) * 100);
      dto.setTotalFee(totalFee);
    }
    if (StringUtils.isNotBlank(map.get("quantity"))) {
      dto.setQuantity(Integer.parseInt(map.get("quantity")));
    }
    if (StringUtils.isNotBlank(map.get("body"))) {
      dto.setBody(map.get("body"));
    }
    if (StringUtils.isNotBlank(map.get("discount"))) {
      dto.setDiscount(Integer.parseInt(map.get("discount")));
    }
    if (StringUtils.isNotBlank(map.get("is_total_fee_adjust"))) {
      dto.setIsTotalFeeAdjust(map.get("is_total_fee_adjust"));
    }
    if (StringUtils.isNotBlank(map.get("use_coupon"))) {
      dto.setUseCoupon(map.get("use_coupon"));
    }
    if (StringUtils.isNotBlank(map.get("error_code"))) {
      dto.setErrorCode(map.get("error_code"));
    }
    if (StringUtils.isNotBlank(map.get("bank_seq_no"))) {
      dto.setBankSeqNo(map.get("bank_seq_no"));
    }
    if (StringUtils.isNotBlank(map.get("extra_common_param"))) {
      dto.setExtraCommonParam(map.get("extra_common_param"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_type"))) {
      dto.setOutChannelType(map.get("out_channel_type"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_amount"))) {
      dto.setOutChannelAmount(map.get("out_channel_amount"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_inst"))) {
      dto.setOutChannelInst(map.get("out_channel_inst"));
    }

    dto.setNotifyValue(map.toString());
    return dto;
  }

}

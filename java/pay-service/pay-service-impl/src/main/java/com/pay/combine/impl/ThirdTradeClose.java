package com.pay.combine.impl;

public class ThirdTradeClose {

//	private static Logger log = LoggerFactory.getLogger(ThirdTradeClose.class);
//
//	private static PaymentService paymentService;
//	public void setPaymentService(PaymentService paymentService) {
//		ThirdTradeClose.paymentService = paymentService;
//	}
//
//	private static PaymentGatewayService paymentGatewayService;
//	public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
//		ThirdTradeClose.paymentGatewayService = paymentGatewayService;
//	}
//
//	// ali交易关闭
//	public static Map<String, String> aliTradeClose(String paymentId) {
//		PaymentResponse paymentResponse = paymentService.getPaymentById(paymentId);
//		if (null == paymentResponse) {
//			log.error("通过paymentId:{}未找到支付单...", paymentId);
//			return null;
//		}
//		PaymentGatewayResponse gateway = paymentGatewayService
//				.getPaymentGatewayById(paymentResponse.getPaymentGatewayId());
//		if (null == gateway) {
//			log.error("通过paymentGatewayId:{}未找到支付网关...", paymentResponse.getPaymentGatewayId());
//			return null;
//		}
//
//		// 把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "close_trade");
//		sParaTemp.put("partner", gateway.getAccount());
//		sParaTemp.put("_input_charset", "utf-8");
//		sParaTemp.put("out_order_no", paymentId);
//
//		Map<String, String> map = null;
//		String xml = null;
//		// 建立请求
//		try {
//			xml = AlipaySubmit.sendHttpResquest(sParaTemp, gateway.getCloseApi(), gateway.getKey(),
//					gateway.getSignType(), "", "");
//			log.debug("ali交易关闭响应:\r\n{}", xml);
//			map = CommonUtil.getMapFromXML(xml);
//		} catch (Exception e) {
//			log.debug("ali交易关闭失败:\r\n{}", e.getMessage());
//		}
//		return map;
//	}
//
//	// 微信关闭交易
//	public static Map<String, String> wxTradeClose(String paymentId) {
//		return null;
//	}
//
//	public static void main(String[] args) {
//		// 把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "close_trade");
//		sParaTemp.put("partner", "2088211554961671");
//		sParaTemp.put("_input_charset", "utf-8");
//		sParaTemp.put("out_order_no", "0f9d177cee904a578f91cb69567f52fc");
//
//		String xml = null;
//		// 建立请求
//		try {
//			xml = AlipaySubmit.sendHttpResquest(sParaTemp, "https://mapi.alipay.com/gateway.do?",
//					"azn0ihswk1elxa7ggt63vj37efjhlzld", "MD5", "", "");
//			log.debug("ali交易关闭响应:\r\n{}", xml);
//			System.out.println(CommonUtil.getMapFromXML(xml));
//		} catch (Exception e) {
//			log.debug("ali交易关闭失败:\r\n{}", e.getMessage());
//		}
//	}
}

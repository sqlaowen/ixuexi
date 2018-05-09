package com.pay.tests.single;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.PaymentGatewayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class PaymentGatewayServiceImpl {

	@Autowired
	private PaymentGatewayService paymentGatewayService;

	// 根据id查询
	@Test
	public void test01() {
		PaymentGatewayResDto res = paymentGatewayService.findById("xx");
		System.out.println(res);
	}

	// 根据网关编码和商户id查询
	@Test
	public void test02() {
		PaymentGatewayResDto res = paymentGatewayService.findByCodeAndMerchantId("mid", "native");
		System.out.println(res);
	}
}

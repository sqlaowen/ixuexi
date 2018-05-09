package com.pay.tests.single;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.PaymentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class PaymentServiceTest {

	@Autowired
	private PaymentService paymentService;

	// 保存, 查询
	@Test
	public void test01() {
		CreatePaymentReqDto dto = new CreatePaymentReqDto();
		dto.setTimeOut(new Date());
		String paymentId = paymentService.saveOne(dto);
		System.out.println(paymentId);
		PaymentResDto res = paymentService.findById(paymentId);
		System.out.println(res);

	}
}

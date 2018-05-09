package com.pay.tests.single;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.single.dto.req.mergepayment.CreateMergePaymentReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.service.MergePaymentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class MergePaymentServiceTest {

  @Autowired
  private MergePaymentService mergePaymentService;

  // 保存, 查询
  @Test
  public void test01() {
    CreateMergePaymentReqDto dto = new CreateMergePaymentReqDto();
    dto.setMerchantId("merchantId");
    dto.setGatewayId("gatewayId");
    dto.setTotalFee(1);
    String mergePaymentId = mergePaymentService.saveOne(dto);
    MergePaymentResDto res = mergePaymentService.findById(mergePaymentId);
    System.out.println(res);
  }
}

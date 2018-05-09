package api.pay.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.single.service.PaymentOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class PaymentOrderServiceTest {

  @Autowired
  private PaymentOrderService paymentOrderService;
  
  @Test
  public void test01(){
    CreatePaymentOrderReqDto dto=new CreatePaymentOrderReqDto();
    dto.setOrderId("orderId");
    dto.setPaymentId("paymentId");
    paymentOrderService.saveOne(dto);
  }
}

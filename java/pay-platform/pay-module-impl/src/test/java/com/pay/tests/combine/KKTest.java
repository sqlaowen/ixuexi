package com.pay.tests.combine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.pay.kafka.KKProducer;

import api.pay.combine.dto.PayItemReqDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class KKTest {

  @Test
  public void test01() throws Exception {
    PayItemReqDto dto = new PayItemReqDto();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    while ((line = reader.readLine()) != null) {

      dto.setOrderNo("order001");
      dto.setTotalFee(1);
      dto.setSpName("spname001");
      dto.setTimeOut(new Date());
      dto.setExtraCommonParam("{\"id\":\"001\"}");
      dto.setSpDetail(line);

      KKProducer.kkMsgProducer("test", JSON.toJSONString(dto));
    }
  }
}

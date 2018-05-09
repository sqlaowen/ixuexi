package com.pay.tests.combine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.SendResult;

import api.pay.combine.dto.PayItemReqDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class MetaqTest {

	@Autowired
	private MetaqTemplate metaqTemplate;

	@Test
	public void test01() throws Exception {
		PayItemReqDto dto = new PayItemReqDto();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = reader.readLine()) != null) {

			dto.setOrderNo("order001");
			dto.setTotalFee(1l);
			dto.setSpName("spname001");
			dto.setTimeOut(new Date());
			dto.setExtraCommonParam("{\"id\":\"001\"}");
			dto.setSpDetail(line);

			SendResult st=metaqTemplate.send(MessageBuilder.withTopic("test").withBody(dto));
			System.out.println(JSON.toJSONString(st));
		}
	}
}

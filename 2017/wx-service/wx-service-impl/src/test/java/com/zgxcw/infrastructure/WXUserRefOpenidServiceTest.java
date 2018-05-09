package com.zgxcw.infrastructure;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.wxuserrefopenid.CreateWXUserRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXUserRefOpenidResponse;
import com.zgxcw.service.infrastructure.service.WXUserRefOpenidService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXUserRefOpenidServiceTest {

  @Autowired
  private WXUserRefOpenidService wxUserRefOpenidService;
  
  @Test
  public void test01(){
    CreateWXUserRefOpenidRequest req=new CreateWXUserRefOpenidRequest();
    req.setWxId("zgxcw_2016");
    req.setOpenid("openid");
    req.setQiyeId("qiyeid");
    req.setYhuId("yhuId");
    String id= wxUserRefOpenidService.saveOne(req);
    System.out.println(id);
    WXUserRefOpenidResponse res1= wxUserRefOpenidService.findById(id);
    System.out.println(res1);
    Collection<WXUserRefOpenidResponse> res2= wxUserRefOpenidService.findByAccountId("zgxcw_2016", "qiyeid");
    System.out.println(res2);
  }
}

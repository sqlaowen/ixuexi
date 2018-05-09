package com.zgxcw.infrastructure;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.wxbankrefopenid.CreateWXBankRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXBankRefOpenidResponse;
import com.zgxcw.service.infrastructure.service.WXBankRefOpenidService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXBankRefOpenidServiceTest {

  @Autowired
  WXBankRefOpenidService wxBankRefOpenidService;

  @Test
  public void test01() {
    CreateWXBankRefOpenidRequest createWXBankRefOpenidRequest = new CreateWXBankRefOpenidRequest();
    createWXBankRefOpenidRequest.setBankName("工商银行1");
    createWXBankRefOpenidRequest.setBankNo("bankNo1");
    createWXBankRefOpenidRequest.setOpenid("openId1");
    createWXBankRefOpenidRequest.setCreateUser("createUser1");
    createWXBankRefOpenidRequest.setWxId("wxId1");
    wxBankRefOpenidService.saveOne(createWXBankRefOpenidRequest);
  }

  @Test
  public void test02() {
    WXBankRefOpenidResponse response =
        wxBankRefOpenidService.findById("cb43c904f6264cafa871dfaa8a56c705");
    System.out.println(response);
    if (response != null) {
      System.out.println("RefId:" + response.getRefId());
      System.out.println("CreateUser:" + response.getCreateUser());
      System.out.println("BankNo:" + response.getBankNo());
      System.out.println("Openid:" + response.getOpenid());
      System.out.println("WxId:" + response.getWxId());
      System.out.println("CreateTime:" + response.getCreateTime());
    }

  }

  @Test
  public void test03() {
    Collection<WXBankRefOpenidResponse> responses =
        wxBankRefOpenidService.findByBankNo("bankNo", "wxId");
    System.out.println(responses);
    if (responses != null) {
      for (WXBankRefOpenidResponse response : responses) {
        System.out.println("RefId:" + response.getRefId());
        System.out.println("CreateUser:" + response.getCreateUser());
        System.out.println("BankNo:" + response.getBankNo());
        System.out.println("Openid:" + response.getOpenid());
        System.out.println("WxId:" + response.getWxId());
        System.out.println("CreateTime:" + response.getCreateTime());
      }
    }
  }

  @Test
  public void test04() {
    WXBankRefOpenidResponse response = wxBankRefOpenidService.findByOpenid("openId1", "wxId1");
    System.out.println(response);
    if (response != null) {
      System.out.println("RefId:" + response.getRefId());
      System.out.println("CreateUser:" + response.getCreateUser());
      System.out.println("BankName:" + response.getBankName());
      System.out.println("BankNo:" + response.getBankNo());
      System.out.println("Openid:" + response.getOpenid());
      System.out.println("WxId:" + response.getWxId());
      System.out.println("CreateTime:" + response.getCreateTime());
    }
  }


  @Test
  public void test05() {
    int result = wxBankRefOpenidService.editBankNo("建设银行2","bankNo2", "openId1", "wxId1");
    System.out.println(result);
  }
}


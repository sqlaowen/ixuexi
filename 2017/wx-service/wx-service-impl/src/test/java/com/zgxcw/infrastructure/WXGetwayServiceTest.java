package com.zgxcw.infrastructure;

import com.zgxcw.service.infrastructure.dto.request.wxgetway.CreateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.UpdateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.response.WXGetwayResponse;
import com.zgxcw.service.infrastructure.service.WXGetwayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huolh on 2016/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration("/notenv/applicationContext.xml")
public class WXGetwayServiceTest {
  @Autowired private WXGetwayService wxGetwayService;

  // 根据id查询
  @Test
  public void test01() {
    WXGetwayResponse res = wxGetwayService.findById("a5a124e7b25244f99caa00ed656e1cb5");
    System.out.println(res);
  }

  //新增
  @Test
  public void test02() {
    CreateWXGetwayRequest request = new CreateWXGetwayRequest();
    request.setWxName("WxName");
    request.setAppId("AppId");
    request.setAppSecret("AppSecret");
    request.setAccessToken("AccessToken");
    request.setAkTimeOut(System.currentTimeMillis());
    request.setWxToken("WxToken");
    request.setWxType(1);
    request.setWxGhid("WxGhid");
    request.setWxMailbox("WxMailbox");
    request.setWxNo("WxNo");
    String res = wxGetwayService.saveOne(request);
    System.out.println(res);

  }

  // 修改
  @Test
  public void test03() {
    UpdateWXGetwayRequest request = new UpdateWXGetwayRequest();
    request.setWxId("a5a124e7b25244f99caa00ed656e1cb5");
    request.setWxName("WxNameUpdate123");
    int res = wxGetwayService.editById(request);
    System.out.println(res);
  }
}

package com.zgxcw.infrastructure;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.CreateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.response.WXSelfMenuResponse;
import com.zgxcw.service.infrastructure.service.WXSelfMenuService;
import com.zgxcw.service.utils.AccessTokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXSelfMenuServiceTest {

  @Autowired
  private WXSelfMenuService wxSelfMenuService;

  // 测试所有
  @Test
  public void test01() {
    CreateWXSelfMenuRequest request = new CreateWXSelfMenuRequest();
    request.setMenuKey("key1.1");
    request.setMenuName("name1.1");
    request.setMenuSeq(0);
    request.setMenuType("VIEW");
    request.setMenuUrl("http://www.baidu.com");
    request.setWxId("wxid");
    String id = wxSelfMenuService.saveOne(request);
    System.out.println(id);
    WXSelfMenuResponse res = wxSelfMenuService.findById("100000");
    System.out.println(res);
    List<WXSelfMenuResponse> resList = wxSelfMenuService.findList("100");
    System.out.println(resList);
    int rev = wxSelfMenuService.delAll("wxid");
    System.out.println(rev);
  }
  
  //取accessToken
  @Test
  public void test02(){
    String ak=AccessTokenUtil.getAccessToken("100");
    System.out.println(ak);
  }

}

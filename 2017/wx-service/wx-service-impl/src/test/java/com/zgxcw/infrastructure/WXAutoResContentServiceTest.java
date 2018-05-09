package com.zgxcw.infrastructure;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.CreateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.UpdateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResContentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXAutoResContentServiceTest {

  @Autowired
  private WXAutoResContentService wxAutoResContentService;

  @Test
  public void test01() {
    CreateWXAutoResContentRequest request = new CreateWXAutoResContentRequest();
    request.setResId("TEXT");
    request.setDescription("你好!欢迎关注!");
    request.setContentSeq(1);
    String id = wxAutoResContentService.saveOne(request);
    System.out.println(id);
    WXAutoResContentResponse res = wxAutoResContentService.findById(id);
    System.out.println(res);
    UpdateWXAutoResContentRequest req = new UpdateWXAutoResContentRequest();
    req.setContentId(id);
    req.setDescription("欢迎欢迎,热烈欢迎!");
    wxAutoResContentService.eidtById(req);
    List<WXAutoResContentResponse> resList = wxAutoResContentService.findByResId("TEXT");
    System.out.println(resList);
  }
}

package com.zgxcw.infrastructure;

import com.zgxcw.service.infrastructure.dto.request.page.PageRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.CreateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.QueryWxAutoResRequest;
import com.zgxcw.service.infrastructure.dto.response.PageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.wxautores.UpdateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXAutoResServiceTest {

  @Autowired
  private WXAutoResService wxAutoResService;

  // 根据id查询
  @Test
  public void test01() {
    WXAutoResResponse res = wxAutoResService.findById("TEXT");
    System.out.println(res);
  }

  // 修改
  @Test
  public void test02() {
    UpdateWXAutoResRequest request = new UpdateWXAutoResRequest();
    request.setResId("TEXT");
    request.setWxId("wxid");
    wxAutoResService.editById(request);

  }

  // 分页
  @Test
  public void test03() {
    QueryWxAutoResRequest queryWxAutoResRequest = new QueryWxAutoResRequest();
    queryWxAutoResRequest.setWxId("100");
    queryWxAutoResRequest.setResName("文本");
    queryWxAutoResRequest.setResType("文本");
    queryWxAutoResRequest.setResState(0);
    queryWxAutoResRequest.setMsgType(1);
    PageRequest<QueryWxAutoResRequest> pageRequest = new PageRequest<QueryWxAutoResRequest>();
    pageRequest.setT(queryWxAutoResRequest);
    pageRequest.setPageNum(1);
    pageRequest.setPageSize(20);
    PageResponse<WXAutoResResponse> pageResponse = wxAutoResService.getPageList(pageRequest);
    if(pageResponse!=null&&pageResponse.getList()!=null) {
      for(WXAutoResResponse item : pageResponse.getList()){
        System.out.println(item.getResId());
      }
    }
  }

  //新增
  @Test
  public void test04() {
    CreateWXAutoResRequest request = new CreateWXAutoResRequest();
    request.setWxId("100");
    request.setResName("文本");
    request.setResType("文本");
    request.setResState(0);
    request.setMsgType(1);
    String res = wxAutoResService.saveOne(request);
    System.out.println(res);
  }
}

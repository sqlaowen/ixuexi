package api.authc.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.FindSysAreaReqDto;
import api.authc.single.dto.res.SysAreaResDto;
import api.authc.single.service.SysAreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class SysAreaServiceTest {

  @Autowired
  private SysAreaService sysAreaService;
  
  @Test
  public void test01(){
    PageReq<FindSysAreaReqDto> req=new PageReq<>();
    req.setPageNum(1);
    req.setPageSize(10);
    FindSysAreaReqDto dto=new FindSysAreaReqDto();
    req.setT(dto);
    PageRes<SysAreaResDto> res=sysAreaService.findList(req);
    System.out.println(res);
  }
}

package com.zgxcw.combine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.combine.service.SetMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class SetMenuServiceTest {

  @Autowired
  private SetMenuService setMenuService ;
  
  //设置菜单
  @Test
  public void test01(){
    String sendXml=setMenuService.createMenu("100");
    System.out.println(sendXml);
  }
}

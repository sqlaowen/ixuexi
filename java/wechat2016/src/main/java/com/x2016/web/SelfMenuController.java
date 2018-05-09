package com.x2016.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.x2016.util.AccessTokenThread;
import com.x2016.util.NetWorkHelper;

@Controller
@RequestMapping("/menu")
public class SelfMenuController {
  private Logger log=LoggerFactory.getLogger(getClass());
  
  //创建自定义菜单
  @RequestMapping("/create")
  public void create(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String dataStr="{"
        +"\"button\":["
        + "{\"name\":\"今日歌曲\",\"type\":\"click\",\"key\":\"V1001_TODAY_MUSIC\"}"
        + ",{\"name\":\"菜单\",\"sub_button\":["
          + "{\"name\":\"搜索\",\"type\":\"view\",\"url\":\"http://www.baidu.com\"}"
          + ",{\"name\":\"扫码\",\"type\":\"scancode_waitmsg\",\"key\":\"menu_0_1\"}"
          + ",{\"name\":\"拍照\",\"type\":\"pic_sysphoto\",\"key\":\"menu_0_2\"}"
          + ",{\"name\":\"位置\",\"type\":\"location_select\",\"key\":\"menu_1_0\"}]}"
        + "]}";
    log.info("自定义菜单数据:{}",dataStr);
    String result=netHelper.getHttpsResponse(url, "POST",dataStr);
    log.info("自定义菜单创建接口地址:{}",url);
    log.info("自定义菜单创建接口返回:{}",result);
  }
  
  //查询自定义菜单
  @RequestMapping("/get")
  public void get(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String result=netHelper.getHttpsResponse(url, "GET",null);
    log.info("自定义菜单查询接口地址:{}",url);
    log.info("自定义菜单查询接口返回:{}",result);
  }
  
//自定义菜单删除接口
  @RequestMapping("/del")
  public void del(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String result=netHelper.getHttpsResponse(url, "GET",null);
    log.info("自定义菜单删除接口地址:{}",url);
    log.info("自定义菜单删除接口返回:{}",result);
  }
}

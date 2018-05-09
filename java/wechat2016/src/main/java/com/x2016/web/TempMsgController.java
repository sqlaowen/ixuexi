package com.x2016.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.x2016.util.AccessTokenThread;
import com.x2016.util.NetWorkHelper;

@Controller
@RequestMapping("/temp/msg")
public class TempMsgController {

  private Logger log=LoggerFactory.getLogger(getClass());
  
  //获取模板列表
  @RequestMapping("/list")
  public void getTempMsgList(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String result=netHelper.getHttpsResponse(url, "GET",null);
    log.info("获取模板消息列表返回:{}",result);
  }
  
  //设置所属行业信息
  @RequestMapping("/set/industry")
  public void setTempMsgIndustry(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String jsonData="{\"industry_id2\":25,\"industry_id1\":1}";
    String result=netHelper.getHttpsResponse(url, "POST",jsonData);
    log.info("设置所属行业请求地址:{}",url);
    log.info("设置所属行业返回:{}",result);
  }
  
  //获取设置的行业信息
  @RequestMapping("/get/industry")
  public void getTempMsgIndustry(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String result=netHelper.getHttpsResponse(url, "POST",null);
    log.info("获取设置的行业信息请求地址:{}",url);
    log.info("获取设置的行业信息返回:{}",result);
  }
  
  //获得模板ID
  @RequestMapping("/get/tempid")
  public void getTempId(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String jsonData="{\"template_id_short\":\"TM00004\"}";
    String result=netHelper.getHttpsResponse(url, "POST",jsonData);
    log.info("获得模板ID请求地址:{}",url);
    log.info("获得模板ID返回:{}",result);
  }
  
  //2Vee80ePkfqHMcqhzSQqsoAsWX01jQ_nBgUF9tfX20o
  //发送模板消息
  @RequestMapping("/send")
  public void sendMsg(){
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",AccessTokenThread.accessToken.getAccessToken());
    String dataStr="{"
        + "\"touser\":\"oFKMnwzhv83wK79L1rwfHbNniPkg\","
        + "\"template_id\":\"2Vee80ePkfqHMcqhzSQqsoAsWX01jQ_nBgUF9tfX20o\","
        + "\"url\":\"http://www.baidu.com\","
        + "\"data\":"
          + "{"
            + "\"first\":{\"value\":\"您好，您对微信影城影票的抢购未成功，已退款\",\"color\":\"#25428\"},"
            + "\"reason\":{\"value\":\"哪有这么多原因\",\"color\":\"#25428\"},"
            + "\"refund\":{\"value\":\"100.00\",\"color\":\"#25428\"},"
            + "\"remark\":{\"value\":\"如有疑问我们也不会解释\",\"color\":\"#25428\"}"
          + "}"
        + "}";
    String result=netHelper.getHttpsResponse(url, "POST",dataStr);
    log.info("发送模板消息请求地址:{}",url);
    log.info("发送模板消息返回:{}",result);
  }
}

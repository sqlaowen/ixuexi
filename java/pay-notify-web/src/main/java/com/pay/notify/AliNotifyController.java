package com.pay.notify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import api.pay.combine.service.PayNotifyService;

@Controller
@RequestMapping("/ali")
public class AliNotifyController {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  private PayNotifyService payNotifyService;
  
  //同步
  @RequestMapping("/sync")
  public String sync(HttpServletRequest request){
    Map<String,String> map=buildMap(request);
    log.info("ali同步请求参数转换的Map<String,String>:{}",map);
    
    String url= payNotifyService.aliReturn(map);
    if(StringUtils.isBlank(url)){
      url="/";
    }
    return "redirect:"+url;
  }
  
  //异步
  @RequestMapping(value="/async")//,method=RequestMethod.POST)
  public void async(HttpServletRequest request, HttpServletResponse response){
    Map<String, String> map = buildMap(request);
    log.info("ali异步请求参数转换的Map<String,String>:{}",map);
    
    // 如果notify_id为空，可能不是来自支付宝的通知，结束
    if (StringUtils.isBlank(map.get("notify_id"))) {
      log.error("ali异步通知notify_id为空，可能遭篡改...,异步通知为:{}", map);
      return;
    }

    boolean rev = payNotifyService.aliNotify(map);
    PrintWriter out = null;
    try {
      out = response.getWriter();
    } catch (IOException e) {
      log.error("HttpServletResponse.getWriter() error:",e.getMessage());
      e.printStackTrace();
    }
    if (rev) {
      log.debug("ali异步通知处理成功...");
      out.println("success"); // 请不要修改或删除
    } else {
      log.debug("ali异步通知处理失败...");
      out.println("fail"); // 请不要修改或删除
    }
  }
  
  //取请求参数
  private Map<String, String> buildMap(HttpServletRequest request) {
    
    Map<String,String> params = new HashMap<String,String>();
    Map<?,?> requestParams = request.getParameterMap();
    
    log.info("ali通知请求参数HttpServletRequest.getParameterMap:{}",requestParams);
    for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      params.put(name, valueStr);
    }
    return params;
  }
}

package com.zgxcw.wx.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zgxcw.service.infrastructure.dto.response.WXGetwayResponse;
import com.zgxcw.service.infrastructure.service.WXGetwayService;
import com.zgxcw.wx.utils.HttpsUtil;

@Controller
@RequestMapping("/account")
public class AccountBindingController {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  @Autowired
  private WXGetwayService wxGetwayService;

  // 微信网页授权,不弹出授权页面
  //微信里访问路径,https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe21ad38684c7166d
      //&redirect_uri=http://zgxcw.ngrok.natapp.cn/wechat/account/bind?wxId=zgxcw_2016
      //&response_type=code&scope=snsapi_base&state=#wechat_redirect
  @RequestMapping("/bind")
  @ResponseBody
  public String accountBindOpenId(String wxId, HttpServletRequest request) {
    if(StringUtils.isBlank(wxId)){
      log.error("微信网页授权,网关id为空...");
      return null;
    }
    log.debug("微信网页授权,网关id:{}",wxId);
    WXGetwayResponse wx = wxGetwayService.findById(wxId);
    String code = request.getParameter("code");
    log.debug("微信网页授权,code:{}",code);
    String url=String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code" ,wx.getAppId(),wx.getAppSecret(),code);
    String rxml = HttpsUtil.getHttpsResponse(url, "GET", null);
    System.out.println(rxml);//此时返回已经包含了openId
    return rxml;
    //...后续业务
  }
  
  //获取用户信息,弹出授权页面
  //微信里访问路径, https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx55321f628b4acc0a
  //&redirect_uri=http://zgxcw.ngrok.natapp.cn/wechat/account/alert/bind?wxId=100
  //&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect
 @RequestMapping("/alert/bind")
 @ResponseBody
 public void accountAlertBindOpenId(String wxId, HttpServletRequest request) {
   if(StringUtils.isBlank(wxId)){
     log.error("微信网页授权,网关id为空...");
     return;
   }
   log.debug("微信网页授权,网关id:{}",wxId);
   WXGetwayResponse wx = wxGetwayService.findById(wxId);
   String code = request.getParameter("code");
   log.debug("微信网页授权,code:{}",code);
   
   //code 换 access_token
   String akUrl=String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code" ,wx.getAppId(),wx.getAppSecret(),code);
   String akXml = HttpsUtil.getHttpsResponse(akUrl, "GET", null);
   //{"access_token":"OezXcEiiBSKSxW0eoylIeLM0jty2IDDNWvWqWqWSS0aM6l4DjZRo-_ENQNAVpexqrK89sXEPPayx4a6CNk3SHPhVulx7fCZnedpC_cWUj0_WFbYxSlvm3YalFOhPAGASeDTP_L2-cTuCL-HwELjdWg","expires_in":7200,"refresh_token":"OezXcEiiBSKSxW0eoylIeLM0jty2IDDNWvWqWqWSS0aM6l4DjZRo-_ENQNAVpexqQaGtBiX7qtbruKYi-SL2ihrQnfxeJSwBCyJDDW6i62ceTqzET-pJ91vweGIs5kEGM3mznFaqFC2nEdWP8kvziQ","openid":"oFKMnwzhv83wK79L1rwfHbNniPkg","scope":"snsapi_userinfo"}
   System.out.println(akXml);//取得临时access_token 和 refresh_token
   
//   //刷新access_token, 如有必要
//   String refresh_token="";//从akXml中解析
//   String rakUrl=String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s",wx.getAppId(),refresh_token);
//   String rakXml=HttpsUtil.getHttpsResponse(rakUrl, "GET", null);
//   System.out.println(rakXml);
   
   //摘取用户信息
   String access_token="";
   String openid="";
   String pUrl=String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",access_token,openid );
   String pXml=HttpsUtil.getHttpsResponse(pUrl, "GET", null);
   //{"openid":"oFKMnwzhv83wK79L1rwfHbNniPkg","nickname":"自然","sex":0,"language":"zh_CN","city":"昌平","province":"北京","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/7cicwAfInRaibicbkoOOEWTIJtBVu14RbibQiaFMXLBxkBCLQP1ib6QY31Co3I2TGULr9Br0jgnnjdBW77xTKxVnZmibsNQLg2GyHiaP\/0","privilege":[]}
   System.out.println(pXml);
 }
}

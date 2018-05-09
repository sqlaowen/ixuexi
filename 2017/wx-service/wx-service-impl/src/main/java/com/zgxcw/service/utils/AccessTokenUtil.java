package com.zgxcw.service.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.UpdateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.response.WXGetwayResponse;
import com.zgxcw.service.infrastructure.service.WXGetwayService;

public class AccessTokenUtil {

  private static Logger log=LoggerFactory.getLogger(AccessTokenUtil.class);
  private static WXGetwayService wxGetwayService;
  public void setWxGetwayService(WXGetwayService wxGetwayService) {
    AccessTokenUtil.wxGetwayService = wxGetwayService;
  }

  private static HttpsUtil httpsUtil;
  public void setHttpsUtil(HttpsUtil httpsUtil) {
    AccessTokenUtil.httpsUtil = httpsUtil;
  }

  /**
   * 获取access_token
   * @param wxId :微信网关id
   * @return
   */
  public static String getAccessToken(String wxId) {
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      return null;
    }
    WXGetwayResponse wx = wxGetwayService.findById(wxId);
    if (null == wx) {
      log.error("微信网关id:{}对应网关为空...", wxId);
      return null;
    }
    if (StringUtils.isBlank(wx.getAppId())) {
      log.error("appId为空,对应微信网关:{}...", JSON.toJSONString(wx));
      return null;
    }
    if (StringUtils.isBlank(wx.getAppSecret())) {
      log.error("appSecret为空,对应微信网关:{}...", JSON.toJSONString(wx));
      return null;
    }
    String accessToken = null;
    Integer expiresIn = 0;
    // 提前200秒抓取新的access_token
    if (null == wx.getAkTimeOut() 
        || (new Date()).getTime() >= wx.getAkTimeOut() - 200 * 1000
        || StringUtils.isBlank(wx.getAccessToken())) {
      String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", wx.getAppId(), wx.getAppSecret());
      String result = httpsUtil.getHttpsResponse(url, "GET", null);
      log.debug("获取accessToken结果为:{}", result);
      if (StringUtils.isNotBlank(result)) {
        JSONObject json = JSON.parseObject(result);
        accessToken = json.getString("access_token");
        expiresIn = json.getInteger("expires_in");
      }
      //更新
      UpdateWXGetwayRequest request = new UpdateWXGetwayRequest();
      request.setWxId(wxId);
      request.setAccessToken(accessToken);
      request.setAkTimeOut((new Date()).getTime() + expiresIn * 1000);
      wxGetwayService.editById(request);
    } else{
      accessToken = wx.getAccessToken();
    }
    return accessToken;
  }
}

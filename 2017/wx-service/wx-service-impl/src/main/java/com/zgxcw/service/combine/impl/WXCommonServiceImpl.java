package com.zgxcw.service.combine.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteDto;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteRequest;
import com.zgxcw.service.combine.dto.request.SendMsgTempleteRequest;
import com.zgxcw.service.combine.service.WXCommonService;
import com.zgxcw.service.infrastructure.dao.WxMsgTempleteMapper;
import com.zgxcw.service.infrastructure.dto.request.WxMsgTemplete.CreateWxMsgTempleteRequest;
import com.zgxcw.service.infrastructure.dto.request.WxSendTempleteLog.CreateWxSendTempleteLogRequest;
import com.zgxcw.service.infrastructure.dto.response.WXBankRefOpenidResponse;
import com.zgxcw.service.infrastructure.dto.response.WXResponse;
import com.zgxcw.service.infrastructure.dto.response.WXUserRefOpenidResponse;
import com.zgxcw.service.infrastructure.dto.response.WxMsgTempleteResponse;
import com.zgxcw.service.infrastructure.service.WXBankRefOpenidService;
import com.zgxcw.service.infrastructure.service.WXUserRefOpenidService;
import com.zgxcw.service.infrastructure.service.WxMsgTempleteService;
import com.zgxcw.service.infrastructure.service.WxSendTempleteLogService;
import com.zgxcw.service.utils.AccessTokenUtil;
import com.zgxcw.service.utils.HttpsUtil;

public class WXCommonServiceImpl implements WXCommonService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  
  private HttpsUtil httpsUtil;
  public void setHttpsUtil(HttpsUtil httpsUtil) {
    this.httpsUtil = httpsUtil;
  }
  
  private WxMsgTempleteService wxMsgTempleteService;
  public void setWxMsgTempleteService(WxMsgTempleteService wxMsgTempleteService) {
    this.wxMsgTempleteService = wxMsgTempleteService;
  }
  
  private WxSendTempleteLogService wxSendTempleteLogService;
  public void setWxSendTempleteLogService(WxSendTempleteLogService wxSendTempleteLogService) {
    this.wxSendTempleteLogService = wxSendTempleteLogService;
  }
  
  private WxMsgTempleteMapper wxMsgTempleteMapper;
  public void setWxMsgTempleteMapper(WxMsgTempleteMapper wxMsgTempleteMapper) {
    this.wxMsgTempleteMapper = wxMsgTempleteMapper;
  }
  
  private WXUserRefOpenidService wxUserRefOpenidService;
  public void setWxUserRefOpenidService(WXUserRefOpenidService wxUserRefOpenidService) {
    this.wxUserRefOpenidService = wxUserRefOpenidService;
  }
  
  private WXBankRefOpenidService wxBankRefOpenidService;
  public void setWxBankRefOpenidService(WXBankRefOpenidService wxBankRefOpenidService) {
    this.wxBankRefOpenidService = wxBankRefOpenidService;
  }
  
  private MetaqTemplate metaqTemplate;
  public void setMetaqTemplate(MetaqTemplate metaqTemplate) {
    this.metaqTemplate = metaqTemplate;
  }

  //获取accessToken
  @Override
  public String getAccessToken(String wxId) throws ServiceException {
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return AccessTokenUtil.getAccessToken(wxId);
  }
  
  @Override
  public void getAllTemplete(String wxId) throws ServiceException {
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    
    //1.清空网关id上的模板...
    wxMsgTempleteMapper.delAll(wxId);
    
    //2.获取微信模板
    String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s",getAccessToken(wxId));
    String result=httpsUtil.getHttpsResponse(url, "GET",null);
    log.debug("请求微信模板消息列表响应:{}",result);
    JSONObject jsObj=JSON.parseObject(result);
    JSONArray jsArr= jsObj.getJSONArray("template_list");
    CreateWxMsgTempleteRequest request=null;
    for (Iterator<Object> iterator = jsArr.iterator(); iterator.hasNext();) {
      request = new CreateWxMsgTempleteRequest();
      jsObj = (JSONObject) iterator.next();
      request.setWxId(wxId);
      request.setTempId(jsObj.getString("template_id"));
      request.setTempTitle(jsObj.getString("title"));
      request.setTempContent(jsObj.getString("content"));
      request.setIndustry1(jsObj.getString("primary_industry"));
      request.setIndustry2(jsObj.getString("deputy_industry"));
      request.setForExample(jsObj.getString("example"));
      wxMsgTempleteService.saveOne(request);
    }
  }
  
  @Override
  public WXResponse<String> sendTempMsg(SendMsgTempleteRequest msgRequest) throws ServiceException {
    
    log.debug("发送模板消息请求参数SendMsgTempleteRequest:{}",JSON.toJSONString(msgRequest));
    
    WXResponse<String> res = sendTempReqValidate(msgRequest);
    if(!"SUCCESS".equalsIgnoreCase(res.getCode())){
      return res;
    }
    res = new WXResponse<>();
    res.setCode("FAIL");
    
    WxMsgTempleteResponse wxTempRes = wxMsgTempleteService.findById(msgRequest.getTemplateId());
    Collection<WXUserRefOpenidResponse> refList = wxUserRefOpenidService.findByAccountId(msgRequest.getWxId(), msgRequest.getQiyeId());
    
    CreateWxSendTempleteLogRequest requestLog=new CreateWxSendTempleteLogRequest();
    buildWXSendTempLog(msgRequest, requestLog);
    
    int failNum=0;
    int successNum=0;
    for (WXUserRefOpenidResponse ref : refList) {
      String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", getAccessToken(msgRequest.getWxId()));
      
      String dataStr = "{";
      dataStr += String.format("\"touser\":\"%s\"", ref.getOpenid());
      dataStr += String.format(",\"template_id\":\"%s\"", msgRequest.getTemplateId());
      if (StringUtils.isNotBlank(msgRequest.getDetailUrl())) {
        dataStr += String.format(",\"url\":\"%s\"", msgRequest.getDetailUrl());
      }

      Pattern pattern = Pattern.compile("\\{\\{(\\w+)\\.DATA\\}\\}");
      Matcher matcher = pattern.matcher(wxTempRes.getTempContent());
      int i = 0;
      String data_ = "";
      while (matcher.find()) {
        if (StringUtils.isBlank(data_)) {
          data_ += String.format("\"%s\":%s", matcher.group(1), msgRequest.getFillContentList().get(i));
        } else {
          data_ += String.format(",\"%s\":%s", matcher.group(1), msgRequest.getFillContentList().get(i));
        }
        i++;
      }
      dataStr += String.format(",\"data\":{%s}", data_);
      dataStr += "}";

      requestLog.setContent(dataStr);
      requestLog.setOpenid(ref.getOpenid());
      
      String result = httpsUtil.getHttpsResponse(url, "POST", dataStr);

      log.debug("发送模板消息返回:{}, 对应请求:{}", result, JSON.toJSONString(msgRequest));
      JSONObject jsObj = JSON.parseObject(result);
      // {\"errcode\":0,\"errmsg\":\"ok\",\"msgid\":408337198}
      requestLog.setErrcode(jsObj.getString("errcode"));
      requestLog.setErrmsg(jsObj.getString("errmsg"));
      requestLog.setMsgid(jsObj.getString("msgid"));
      if (jsObj.getString("errmsg").equalsIgnoreCase("ok")) {
        requestLog.setNote("发送成功");
        successNum += 1;
      } else {
        requestLog.setNote("发送失败");
        failNum += 1;
      }
      wxSendTempleteLogService.saveOne(requestLog);
    }
    res.setMsg(String.format("企业id:%s,在服务号:%s 下对应 %s 个微信号,成功发送 %s 条,失败发送 %s 条",msgRequest.getQiyeId(),msgRequest.getWxId(),refList.size(), successNum, failNum));
    if(successNum>0){
      res.setCode("SUCCESS");
    }
    return res;
  }
  
  @Override
  public WXResponse<String> sendBankTempMsg(SendBankMsgTempleteRequest msgRequest) throws ServiceException {

    log.debug("发送银行卡号模板消息请求参数SendBankMsgTempleteRequest:{}",JSON.toJSONString(msgRequest));
    
    WXResponse<String> res = sendBankTempReqValidate(msgRequest);
    if(!"SUCCESS".equalsIgnoreCase(res.getCode())){
      return res;
    }
    res = new WXResponse<>();
    res.setCode("FAIL");
    
    WxMsgTempleteResponse wxTempRes = wxMsgTempleteService.findById(msgRequest.getTemplateId());
    Collection<WXBankRefOpenidResponse> refList= wxBankRefOpenidService.findByBankNo(msgRequest.getBankNo(), msgRequest.getWxId());
    
    CreateWxSendTempleteLogRequest requestLog=new CreateWxSendTempleteLogRequest();
    buildWXSendBankTempLog(msgRequest, requestLog);
    
    int failNum=0;
    int successNum=0;
    for (WXBankRefOpenidResponse ref : refList) {
      String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", getAccessToken(msgRequest.getWxId()));
      
      String dataStr = "{";
      dataStr += String.format("\"touser\":\"%s\"", ref.getOpenid());
      dataStr += String.format(",\"template_id\":\"%s\"", msgRequest.getTemplateId());
      if (StringUtils.isNotBlank(msgRequest.getDetailUrl())) {
        dataStr += String.format(",\"url\":\"%s\"", msgRequest.getDetailUrl());
      }

      Pattern pattern = Pattern.compile("\\{\\{(\\w+)\\.DATA\\}\\}");
      Matcher matcher = pattern.matcher(wxTempRes.getTempContent());
      int i = 0;
      String data_ = "";
      while (matcher.find()) {
        if (StringUtils.isBlank(data_)) {
          data_ += String.format("\"%s\":%s", matcher.group(1), msgRequest.getFillContentList().get(i));
        } else {
          data_ += String.format(",\"%s\":%s", matcher.group(1), msgRequest.getFillContentList().get(i));
        }
        i++;
      }
      dataStr += String.format(",\"data\":{%s}", data_);
      dataStr += "}";

      requestLog.setContent(dataStr);
      requestLog.setOpenid(ref.getOpenid());
      
      String result = httpsUtil.getHttpsResponse(url, "POST", dataStr);

      log.debug("发送银行卡号模板消息返回:{}, 对应请求:{}", result, JSON.toJSONString(msgRequest));
      JSONObject jsObj = JSON.parseObject(result);
      // {\"errcode\":0,\"errmsg\":\"ok\",\"msgid\":408337198}
      requestLog.setErrcode(jsObj.getString("errcode"));
      requestLog.setErrmsg(jsObj.getString("errmsg"));
      requestLog.setMsgid(jsObj.getString("msgid"));
      if (jsObj.getString("errmsg").equalsIgnoreCase("ok")) {
        requestLog.setNote("发送成功");
        successNum += 1;
      } else {
        requestLog.setNote("发送失败");
        failNum += 1;
      }
      wxSendTempleteLogService.saveOne(requestLog);
    }
    res.setMsg(String.format("银行卡号:%s,在服务号:%s 下对应 %s 个微信号,成功发送 %s 条,失败发送 %s 条",msgRequest.getBankNo(),msgRequest.getWxId(),refList.size(), successNum, failNum));
    if(successNum>0){
      res.setCode("SUCCESS");
    }
    return res;
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////
  
  private void buildWXSendTempLog(SendMsgTempleteRequest msgRequest, CreateWxSendTempleteLogRequest requestLog) {
    requestLog.setWxId(msgRequest.getWxId());
    requestLog.setQiyeId(msgRequest.getQiyeId());
    requestLog.setTempId(msgRequest.getTemplateId());
    requestLog.setPreFill(StringUtils.join(msgRequest.getFillContentList().toArray(), " , "));
  }
  
  //参数验证
  private WXResponse<String> sendTempReqValidate(SendMsgTempleteRequest msgRequest){
    WXResponse<String> res=new WXResponse<>();
    res.setCode("FAIL");
    
    String error="";
    List<String> errList=new ArrayList<>();
    
    CreateWxSendTempleteLogRequest requestLog=new CreateWxSendTempleteLogRequest();
    
    if (null == msgRequest) {
      error = "请求参数SendMsgTempleteRequest为空...";
      log.error(error);
      res.setMsg(error);
      requestLog.setNote(error);
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }
    
    //基本参数验证
    if(StringUtils.isBlank(msgRequest.getWxId())){
      error = "请求参数SendMsgTempleteRequest.getWxId为空...";
      log.error(error);
      errList.add(error);
    }
    if(StringUtils.isBlank(msgRequest.getQiyeId())){
      error = "请求参数SendMsgTempleteRequest.getQiyeId为空...";
      log.error(error);
      errList.add(error);
    }
    if(StringUtils.isBlank(msgRequest.getTemplateId())){
      error = "请求参数SendMsgTempleteRequest.getTemplateId为空...";
      log.error(error);
      errList.add(error);
    } 
    if(0 != errList.size()){
      res.setMsg(StringUtils.join(errList.toArray(), " ; \r\n "));
      buildWXSendTempLog(msgRequest, requestLog);
      requestLog.setNote(StringUtils.join(errList.toArray(), " ; \r\n "));
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }

    //参数验证
    errList = new ArrayList<>();
    WxMsgTempleteResponse wxTempRes = wxMsgTempleteService.findById(msgRequest.getTemplateId());
    if (null == wxTempRes) {
      error = "请求参数SendMsgTempleteRequest.getTemplateId对应模板不存在...";
      log.error(error);
      errList.add(error);
    } else {
      String tmpContent = wxTempRes.getTempContent().replaceAll("\\{\\{\\w+\\.DATA\\}\\}", "~-~");
      if (msgRequest.getFillContentList().size() != StringUtils.countMatches(tmpContent, "~-~")) {
        error = "请求参数SendMsgTempleteRequest.getFillContentList.size与对应模板待填充占位不相等...";
        log.error(error);
        errList.add(error);
      }
    }
    Collection<WXUserRefOpenidResponse> refList = wxUserRefOpenidService.findByAccountId(msgRequest.getWxId(), msgRequest.getQiyeId());
    if (null == refList || 0 == refList.size()) {
      error=String.format("该企业用户:%s 暂时未关注公众号:%s, 不能发送模板消息", msgRequest.getQiyeId(), msgRequest.getWxId());
      log.error(error);
      errList.add(error);
    }
    
    if(0 != errList.size()){
      res.setMsg(StringUtils.join(errList.toArray(), " ; \r\n "));
      buildWXSendTempLog(msgRequest, requestLog);
      requestLog.setNote(StringUtils.join(errList.toArray(), " ; \r\n "));
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }
    
    res.setCode("SUCCESS");
    return res;
  }
  
  /////////////////////////////////////////////////////////////////////////////////////
  
  private void buildWXSendBankTempLog(SendBankMsgTempleteRequest msgRequest, CreateWxSendTempleteLogRequest requestLog) {
    requestLog.setWxId(msgRequest.getWxId());
    requestLog.setQiyeId(msgRequest.getBankNo());
    requestLog.setTempId(msgRequest.getTemplateId());
    requestLog.setPreFill(StringUtils.join(msgRequest.getFillContentList().toArray(), " , "));
  }
  
  //参数验证
  private WXResponse<String> sendBankTempReqValidate(SendBankMsgTempleteRequest msgRequest){
    WXResponse<String> res=new WXResponse<>();
    res.setCode("FAIL");
    
    String error="";
    List<String> errList=new ArrayList<>();
    
    CreateWxSendTempleteLogRequest requestLog=new CreateWxSendTempleteLogRequest();
    
    if (null == msgRequest) {
      error = "请求参数SendBankMsgTempleteRequest为空...";
      log.error(error);
      res.setMsg(error);
      requestLog.setNote(error);
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }
    
    //基本参数验证
    if(StringUtils.isBlank(msgRequest.getWxId())){
      error = "请求参数SendBankMsgTempleteRequest.getWxId为空...";
      log.error(error);
      errList.add(error);
    }
    if(StringUtils.isBlank(msgRequest.getBankNo())){
      error = "请求参数SendBankMsgTempleteRequest.getBankNo为空...";
      log.error(error);
      errList.add(error);
    }
    if(StringUtils.isBlank(msgRequest.getTemplateId())){
      error = "请求参数SendBankMsgTempleteRequest.getTemplateId为空...";
      log.error(error);
      errList.add(error);
    } 
    if(0 != errList.size()){
      res.setMsg(StringUtils.join(errList.toArray(), " ; \r\n "));
      buildWXSendBankTempLog(msgRequest, requestLog);
      requestLog.setNote(StringUtils.join(errList.toArray(), " ; \r\n "));
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }

    //参数验证
    errList = new ArrayList<>();
    WxMsgTempleteResponse wxTempRes = wxMsgTempleteService.findById(msgRequest.getTemplateId());
    if (null == wxTempRes) {
      error = "请求参数SendBankMsgTempleteRequest.getTemplateId对应模板不存在...";
      log.error(error);
      errList.add(error);
    } else {
      String tmpContent = wxTempRes.getTempContent().replaceAll("\\{\\{\\w+\\.DATA\\}\\}", "~-~");
      if (msgRequest.getFillContentList().size() != StringUtils.countMatches(tmpContent, "~-~")) {
        error = "请求参数SendBankMsgTempleteRequest.getFillContentList.size与对应模板待填充占位不相等...";
        log.error(error);
        errList.add(error);
      }
    }
    Collection<WXBankRefOpenidResponse> refList= wxBankRefOpenidService.findByBankNo(msgRequest.getBankNo(), msgRequest.getWxId());
    if (null == refList || 0 == refList.size()) {
      error=String.format("银行卡号:%s 在服务号:%s下没有对应的openid, 不能发送模板消息", msgRequest.getBankNo(), msgRequest.getWxId());
      log.error(error);
      errList.add(error);
    }
    
    if(0 != errList.size()){
      res.setMsg(StringUtils.join(errList.toArray(), " ; \r\n "));
      buildWXSendBankTempLog(msgRequest, requestLog);
      requestLog.setNote(StringUtils.join(errList.toArray(), " ; \r\n "));
      wxSendTempleteLogService.saveOne(requestLog);
      return res;
    }
    
    res.setCode("SUCCESS");
    return res;
  }

  @Override
  public WXResponse<String> sendBankTempMsg2MQ(SendBankMsgTempleteDto msgDto) throws ServiceException {
    String json = JSON.toJSONString(msgDto);
    log.info("ODC调用RPC服务发送模板消息到MQ, 请求参数:{}", json);
    WXResponse<String> res=new WXResponse<>();
    res.setCode("FAIL");
    SendResult result = null;
    int i=3;//重试2次, 共尝试向队列放3次
    while(i>0){
      try {
        result = metaqTemplate.send(MessageBuilder.withTopic("ODC_SENDWXTEMPMSG").withPayload(json.getBytes()));
      } catch (InterruptedException e) {
        log.error("ODC调用RPC服务发送模板消息到MQ异常, 异常信息:{}, 对应请求:{}", e.getMessage(), json);
      }
      if(result.isSuccess()){
        break;
      }
      try {
        Thread.sleep(100);//线程 sleep 0.1 秒
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      i--;
    }
    if(result.isSuccess()){
      res.setCode("SUCCESS");
    } else {
      res.setMsg("ODC调用RPC服务发送模板消息到MQ失败, 失败提示:" + result.getErrorMessage());
    }
    return res;
  }

}

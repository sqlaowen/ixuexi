package com.zgxcw.service.combine.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.combine.service.SendPassiveMsgService;
import com.zgxcw.service.constants.WXMsgTypeConstant;
import com.zgxcw.service.infrastructure.dto.request.WxReceiveMsg.CreateWxReceiveMsgRequest;
import com.zgxcw.service.infrastructure.dto.request.WxSendMsgLog.CreateWxSendMsgLogRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResContentService;
import com.zgxcw.service.infrastructure.service.WXAutoResService;
import com.zgxcw.service.infrastructure.service.WxReceiveMsgService;
import com.zgxcw.service.infrastructure.service.WxSendMsgLogService;
import com.zgxcw.service.messge.ReceiveXmlMsg;
import com.zgxcw.service.utils.ResolveReceiveXmlMsgUtil;

/**
 * 被动回复消息处理
 * 
 *
 */
public class SendPassiveMsgServiceImpl implements SendPassiveMsgService {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  private WXAutoResService wxAutoResService;
  public void setWxAutoResService(WXAutoResService wxAutoResService) {
    this.wxAutoResService = wxAutoResService;
  }
  
  private WXAutoResContentService wxAutoResContentService;
  public void setWxAutoResContentService(WXAutoResContentService wxAutoResContentService) {
    this.wxAutoResContentService = wxAutoResContentService;
  }
  
  private WxReceiveMsgService wxReceiveMsgService;
  public void setWxReceiveMsgService(WxReceiveMsgService wxReceiveMsgService) {
    this.wxReceiveMsgService = wxReceiveMsgService;
  }
  
  private WxSendMsgLogService wxSendMsgLogService;
  public void setWxSendMsgLogService(WxSendMsgLogService wxSendMsgLogService) {
    this.wxSendMsgLogService = wxSendMsgLogService;
  }

  /**
   * 自动回复(1关注回复,2输入回复,3事件回复)
   * 
   * @param reqXml :微信转发来的xml请求
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  @Override
  public String autoAnswer(String reqXml, String wxId) throws ServiceException {
    if(StringUtils.isBlank(reqXml)){
      log.error("请求参数reqXml为空...");
      throw new ServiceException("请求参数reqXml为空...");
    }
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    ReceiveXmlMsg dto = ResolveReceiveXmlMsgUtil.getMsgEntity(reqXml);
    if (null == dto) {
      log.error("解析xml失败,对应xml为:{}", reqXml);
      throw new ServiceException("解析xml失败,对应xml为:" + reqXml);
    }
    
    //保存微信服务器转发过来的消息 
    CreateWxReceiveMsgRequest createReceiveMsgReq=new CreateWxReceiveMsgRequest();
    BeanUtils.copyProperties(dto, createReceiveMsgReq);
    createReceiveMsgReq.setLocationX(dto.getLocation_X());
    createReceiveMsgReq.setLocationY(dto.getLocation_Y());
    createReceiveMsgReq.setWxId(wxId);
    wxReceiveMsgService.saveOne(createReceiveMsgReq);
    
    //------------------输入回复,事件回复(关注和非关注)-------------------
    WXAutoResResponse wxRes = null;
    String tmpErr = null;
    if (!dto.getMsgType().toUpperCase().equals("EVENT")) {// 输入回复
      wxRes = wxAutoResService.findTheSet(2, wxId);
      if (null == wxRes) {
        tmpErr = "微信网关id:" + wxId + "没有找到已设置的自动回复消息...";
      }
    } else {// 事件回复
      if (!dto.getEvent().toUpperCase().equals("SUBSCRIBE")) {// 菜单回复
        wxRes = wxAutoResService.findById(dto.getEventKey());
        if (null == wxRes) {
          tmpErr = "微信网关id:" + wxId + "，eventKey:" + dto.getEventKey() + "没有找到已设置的自动回复消息...";
        }
      } else {// 关注回复
        wxRes = wxAutoResService.findTheSet(1, wxId);
        if (null == wxRes) {
          tmpErr = "微信网关id:" + wxId + "没有找到已设置的关注回复消息...";
        }
      }
    }
    if (null == wxRes) {
      log.error(tmpErr);
      throw new ServiceException(tmpErr);
    }
    //---------------------------------------------------------
    
    if (StringUtils.isBlank(wxRes.getResType())) {
      log.error("自动回复消息类型为空,对应自动回复消息:{}", JSON.toJSONString(wxRes));
      throw new ServiceException("自动回复消息类型为空,对应自动回复消息:{}" + JSON.toJSONString(wxRes));
    }
    List<WXAutoResContentResponse> wxResContentList = wxAutoResContentService.findByResId(wxRes.getResId());
    if (null == wxResContentList || wxResContentList.size() == 0) {
      log.error("微信网关id:{}没有找到已设置的自动回复消息内容...", wxId);
      throw new ServiceException("微信网关id:" + wxId + "没有找到已设置的自动回复消息内容...");
    }
    WXAutoResContentResponse wxResContent = wxResContentList.get(0);
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(setFromTo(dto.getFromUserName(), dto.getToUserName()));
      if(wxRes.getResType().equalsIgnoreCase(WXMsgTypeConstant.MSGTYPE_TEXT)){//回复文本消息
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append(String.format("<Content><![CDATA[%s]]></Content>", wxResContent.getDescription()));
      } else if(wxRes.getResType().equalsIgnoreCase(WXMsgTypeConstant.MSGTYPE_NEWS)){//回复图文消息
        sb.append("<MsgType><![CDATA[news]]></MsgType>");
        sb.append(String.format("<ArticleCount>%s</ArticleCount>", wxResContentList.size()));
        sb.append("<Articles>");
          for (WXAutoResContentResponse res : wxResContentList) {
            sb.append("<item>");
              sb.append(String.format("<Title><![CDATA[%s]]></Title>", res.getTitle()));
              sb.append(String.format("<Description><![CDATA[%s]]></Description>", res.getDescription()));
              sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>", res.getPicUrl()));
              sb.append(String.format("<Url><![CDATA[%s]]></Url>", res.getUrl()));
            sb.append("</item>");
          }
        sb.append("</Articles>");
      } else {//找不到相对应的回复消息类型时,默认回复文本消息
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append(String.format("<Content><![CDATA[%s]]></Content>", wxResContent.getDescription()));
      }
    sb.append("</xml>");
    
    //保存回复粉丝消息记录
    CreateWxSendMsgLogRequest createSendMsgLogReq=new CreateWxSendMsgLogRequest();
    createSendMsgLogReq.setOpenid(dto.getFromUserName());
    createSendMsgLogReq.setGhid(dto.getToUserName());
    createSendMsgLogReq.setMsgType(wxRes.getResType());
    createSendMsgLogReq.setMsgContent(sb.toString());
    createSendMsgLogReq.setWxId(wxId);
    wxSendMsgLogService.saveOne(createSendMsgLogReq);
    
    return sb.toString();
  }

  /**
   * 
   * @param to
   * @param from
   * @return
   */
  private String setFromTo(String to, String from) {
    StringBuffer sb = new StringBuffer();
    sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", to));
    sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", from));
    sb.append(String.format("<CreateTime>%s</CreateTime>", new Date().getTime()));
    return sb.toString();
  }

}

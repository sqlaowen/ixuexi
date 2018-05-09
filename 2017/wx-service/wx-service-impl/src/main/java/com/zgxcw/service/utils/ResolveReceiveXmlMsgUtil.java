package com.zgxcw.service.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zgxcw.service.messge.ReceiveXmlMsg;

public class ResolveReceiveXmlMsgUtil {

  private static Logger log = LoggerFactory.getLogger(ResolveReceiveXmlMsgUtil.class);

  /**
   * 解析微信服务器转发进来的xml消息
   * 
   * @param xml
   * @return
   */
  @SuppressWarnings("unchecked")
  public static ReceiveXmlMsg getMsgEntity(String xml) {
    log.debug("微信服务器转发进来的消息为:{}",xml);
    ReceiveXmlMsg msg = null;
    if (StringUtils.isBlank(xml)) {
      log.error("微信服务器转发进来的消息空...");
      return msg;
    }
    // 将字符串转化为XML文档对象
    Document document = null;
    try {
      document = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      log.error("解析微信服务器转发进来的消息:{},失败:{}...", xml, e.getMessage());
      e.printStackTrace();
      return msg;
    }
    // 获得文档的根节点
    Element root = document.getRootElement();
    msg = new ReceiveXmlMsg();
    for (Element el : (List<Element>) root.elements()) {
      if ("ToUserName".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setToUserName(el.getTextTrim());
        }
      }
      if ("FromUserName".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setFromUserName(el.getTextTrim());
        }
      }
      if ("CreateTime".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setCreateTime(el.getTextTrim());
        }
      }
      if ("MsgType".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMsgType(el.getTextTrim());
        }
      }
      if ("MsgId".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMsgId(el.getTextTrim());
        }
      }
      if ("Event".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setEvent(el.getTextTrim());
        }
      }
      if ("EventKey".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setEventKey(el.getTextTrim());
        }
      }
      if ("Ticket".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setTicket(el.getTextTrim());
        }
      }
      if ("Latitude".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLatitude(el.getTextTrim());
        }
      }
      if ("Longitude".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLongitude(el.getTextTrim());
        }
      }
      if ("Precision".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setPrecision(el.getTextTrim());
        }
      }
      if ("PicUrl".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setPicUrl(el.getTextTrim());
        }
      }
      if ("MediaId".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMediaId(el.getTextTrim());
        }
      }
      if ("ThumbMediaId".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setThumbMediaId(el.getTextTrim());
        }
      }
      if ("Title".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setTitle(el.getTextTrim());
        }
      }
      if ("Description".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setDescription(el.getTextTrim());
        }
      }
      if ("Url".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setUrl(el.getTextTrim());
        }
      }
      if ("Location_X".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLocation_X(el.getTextTrim());
        }
      }
      if ("Location_Y".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLocation_Y(el.getTextTrim());
        }
      }
      if ("Scale".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setScale(el.getTextTrim());
        }
      }
      if ("Label".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLabel(el.getTextTrim());
        }
      }
      if ("Content".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setContent(el.getTextTrim());
        }
      }
      if ("Format".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setFormat(el.getTextTrim());
        }
      }
      if ("Recognition".equalsIgnoreCase(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setRecognition(el.getTextTrim());
        }
      }
    }
    return msg;
  }
}

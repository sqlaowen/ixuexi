package com.x2016.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x2016.model.ReceiveXmlEntity;

public class ReceiveXmlProcess {
  
  private Logger log=LoggerFactory.getLogger(getClass());
  /**
   * 解析微信转发进来的xml消息
   * 
   * @param xml
   * @return
   */
  @SuppressWarnings("unchecked")
  public ReceiveXmlEntity getMsgEntity(String xml) {
    
    ReceiveXmlEntity msg = null;
    if (StringUtils.isBlank(xml)) {
      log.error("微信转发进来的消息空...");
      return msg;
    }
    // 将字符串转化为XML文档对象
    Document document=null;
    try {
      document = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      log.error("解析微信转发进来的消息:{},失败:{}...",xml,e.getMessage());
      e.printStackTrace();
      return msg;
    }
    // 获得文档的根节点
    Element root = document.getRootElement();
    msg = new ReceiveXmlEntity();
    for (Element el : (List<Element>) root.elements()) {
      if ("ToUserName".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setToUserName(el.getTextTrim());
        }
      }
      if ("FromUserName".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setFromUserName(el.getTextTrim());
        }
      }
      if ("CreateTime".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setCreateTime(el.getTextTrim());
        }
      }
      if ("MsgType".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMsgType(el.getTextTrim());
        }
      }
      if ("MsgId".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMsgId(el.getTextTrim());
        }
      }
      if ("Event".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setEvent(el.getTextTrim());
        }
      }
      if ("EventKey".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setEventKey(el.getTextTrim());
        }
      }
      if ("Ticket".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setTicket(el.getTextTrim());
        }
      }
      if ("Latitude".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLatitude(el.getTextTrim());
        }
      }
      if ("Longitude".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLongitude(el.getTextTrim());
        }
      }
      if ("Precision".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setPrecision(el.getTextTrim());
        }
      }
      if ("PicUrl".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setPicUrl(el.getTextTrim());
        }
      }
      if ("MediaId".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setMediaId(el.getTextTrim());
        }
      }
      if ("ThumbMediaId".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setThumbMediaId(el.getTextTrim());
        }
      }
      if ("Title".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setTitle(el.getTextTrim());
        }
      }
      if ("Description".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setDescription(el.getTextTrim());
        }
      }
      if ("Url".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setUrl(el.getTextTrim());
        }
      }
      if ("Location_X".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLocation_X(el.getTextTrim());
        }
      }
      if ("Location_Y".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLocation_Y(el.getTextTrim());
        }
      }
      if ("Scale".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setScale(el.getTextTrim());
        }
      }
      if ("Label".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setLabel(el.getTextTrim());
        }
      }
      if ("Content".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setContent(el.getTextTrim());
        }
      }
      if ("Format".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setFormat(el.getTextTrim());
        }
      }
      if ("Recognition".equals(el.getName())) {
        if (StringUtils.isNotBlank(el.getTextTrim())) {
          msg.setRecognition(el.getTextTrim());
        }
      }
    }
    return msg;
  }
}

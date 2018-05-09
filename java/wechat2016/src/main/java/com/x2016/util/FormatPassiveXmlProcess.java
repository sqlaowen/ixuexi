package com.x2016.util;

import java.util.Date;

import com.x2016.messge.passive.ArticleAnswerMsg;
import com.x2016.messge.passive.MusicAnswerMsg;
import com.x2016.messge.passive.NewsAnswerMsg;
import com.x2016.messge.passive.VideoAnswerMsg;

/**
 * 被动回复消息处理
 * 
 * @author wensw
 *
 */
public class FormatPassiveXmlProcess {

  /**
   * 回复文本消息
   * 
   * @param to :接收方帐号（收到的OpenID）
   * @param from :开发者微信号
   * @param content
   * @return
   */
  public String textXmlAnswer(String to, String from, String content) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(to, from));
      sb.append("<MsgType><![CDATA[text]]></MsgType>");
      sb.append(String.format("<Content><![CDATA[%s]]></Content>",content));
    sb.append("</xml>");
    return sb.toString();
  }
  
  /**
   * 回复图片消息
   * @param to :接收方帐号（收到的OpenID）
   * @param from :开发者微信号
   * @param mediaId :通过素材管理接口上传多媒体文件，得到的id
   * @return
   */
  public String imageXmlAnswer(String to,String from,String mediaId){
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(to, from));
      sb.append("<MsgType><![CDATA[image]]></MsgType>");
      sb.append("<Image>");
        sb.append(String.format("<MediaId><![CDATA[%s]]></MediaId>",mediaId));
      sb.append("</Image>");
    sb.append("</xml>");
    return sb.toString();
  }
  
  /**
   * 回复语音消息
   * @param to :接收方帐号（收到的OpenID）
   * @param from :开发者微信号
   * @param mediaId :通过素材管理接口上传多媒体文件，得到的id
   * @return
   */
  public String voiceXmlAnswer(String to, String from,String mediaId) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(to, from));
      sb.append("<MsgType><![CDATA[voice]]></MsgType>");
      sb.append("<Voice>");
        sb.append(String.format("<MediaId><![CDATA[%s]]></MediaId>",mediaId));
      sb.append("</Voice>");
    sb.append("</xml>");
    return sb.toString();
  }
  
  /**
   * 回复视频消息
   * @param msg :VideoAnswerMsg
   * @return
   */
  public String videoXmlAnswer(VideoAnswerMsg msg) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(msg.getToUserName(), msg.getFromUserName()));
      sb.append("<MsgType><![CDATA[video]]></MsgType>");
      sb.append("<Video>");
        sb.append(String.format("<MediaId><![CDATA[%s]]></MediaId>",msg.getMediaId()));
        sb.append(String.format("<Title><![CDATA[%s]]></Title>",msg.getTitle()));
        sb.append(String.format("<Description><![CDATA[%s]]></Description>",msg.getDescription()));
      sb.append("</Video>");
    sb.append("</xml>");
    return sb.toString();
  }
  
  /**
   * 回复音乐消息
   * @param msg :MusicAnswerMsg
   * @return
   */
  public String musicXmlAnswer(MusicAnswerMsg msg) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(msg.getToUserName(), msg.getFromUserName()));
      sb.append("<MsgType><![CDATA[music]]></MsgType>");
      sb.append("<Music>");
        sb.append(String.format("<Title><![CDATA[%s]]></Title>",msg.getTitle()));
        sb.append(String.format("<Description><![CDATA[%s]]></Description>",msg.getDescription()));
        sb.append(String.format("<MusicUrl><![CDATA[%s]]></MusicUrl>",msg.getMusicUrl()));
        sb.append(String.format("<HQMusicUrl><![CDATA[%s]]></HQMusicUrl>",msg.getHQMusicUrl()));
        sb.append(String.format("<ThumbMediaId><![CDATA[%s]]></ThumbMediaId>",msg.getThumbMediaId()));
      sb.append("</Music>");
    sb.append("</xml>");

    return sb.toString();
  }
  
  /**
   *  回复图文消息
   * @param msg :NewsAnswerMsg
   * @return String
   */
  public String newsXmlAnswer(NewsAnswerMsg msg) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
      sb.append(xmlAnswer(msg.getToUserName(), msg.getFromUserName()));
      sb.append("<MsgType><![CDATA[news]]></MsgType>");
      sb.append(String.format("<ArticleCount>%s</ArticleCount>", msg.getArticles().size()));
      sb.append("<Articles>");
        for (ArticleAnswerMsg article : msg.getArticles()) {
          sb.append("<item>");
            sb.append(String.format("<Title><![CDATA[%s]]></Title>", article.getTitle()));
            sb.append(String.format("<Description><![CDATA[%s]]></Description>", article.getDescription()));
            sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>", article.getPicUrl()));
            sb.append(String.format("<Url><![CDATA[%s]]></Url>", article.getUrl()));
          sb.append("</item>");
        }
      sb.append("</Articles>");
    sb.append("</xml>");

    return sb.toString();
  }
  
  private String xmlAnswer(String to,String from){
    StringBuffer sb = new StringBuffer();
    sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>",to));
    sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>",from));
    sb.append(String.format("<CreateTime>%s</CreateTime>",new Date().getTime()));
    return sb.toString();
  }
}

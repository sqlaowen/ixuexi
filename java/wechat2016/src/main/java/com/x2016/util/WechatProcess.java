package com.x2016.util;

import java.util.ArrayList;
import java.util.List;

import com.x2016.messge.passive.ArticleAnswerMsg;
import com.x2016.messge.passive.NewsAnswerMsg;
import com.x2016.model.ReceiveXmlEntity;

public class WechatProcess {

  /**
   * 解析处理xml、获取智能回复结果（通过图灵机器人api接口）
   * 
   * @param xml 接收到的微信数据
   * @return 最终的解析结果（xml格式数据）
   */
  public String processWechatMag(String xml) {
    /** 解析xml数据 */
    ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);

    /** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */
    String result = "";
//    if ("text".endsWith(xmlEntity.getMsgType())) {
//      result = "温世威";//new TulingApiProcess().getTulingResult(xmlEntity.getContent());
//    }

    /**
     * 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容 因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息
     */
    //result = new FormatPassiveXmlProcess().textXmlAnswer(xmlEntity.getFromUserName(),xmlEntity.getToUserName(), "hello world !");
    //result=new FormatPassiveXmlProcess().imageXmlAnswer(xmlEntity.getFromUserName(),xmlEntity.getToUserName(), "");
    //result=new FormatPassiveXmlProcess().voiceXmlAnswer(xmlEntity.getFromUserName(),xmlEntity.getToUserName(), "");
    
    NewsAnswerMsg msg=new NewsAnswerMsg();
    msg.setToUserName(xmlEntity.getFromUserName());
    msg.setFromUserName(xmlEntity.getToUserName());
    List<ArticleAnswerMsg> articles=new ArrayList<>();
    ArticleAnswerMsg articleAnswerMsg=new ArticleAnswerMsg();
    articleAnswerMsg.setTitle("主标题");
    articleAnswerMsg.setDescription("2016-01-28");
    articleAnswerMsg.setUrl("http://www.baidu.com");
    articleAnswerMsg.setPicUrl("http://pc.huochepiao.360.cn/images/banner_app.png");
    articles.add(articleAnswerMsg);
    articleAnswerMsg=new ArticleAnswerMsg();
    articleAnswerMsg.setTitle("副标题1");
    articleAnswerMsg.setDescription("2016-01-28");
    articleAnswerMsg.setUrl("http://www.qq.com");
    articleAnswerMsg.setPicUrl("http://pc.huochepiao.360.cn/images/banner_app.png");
    articles.add(articleAnswerMsg);
    articleAnswerMsg=new ArticleAnswerMsg();
    articleAnswerMsg.setTitle("副标题2");
    articleAnswerMsg.setDescription("2016-01-28");
    articleAnswerMsg.setUrl("http://www.google.com");
    articleAnswerMsg.setPicUrl("http://pc.huochepiao.360.cn/images/banner_app.png");
    articles.add(articleAnswerMsg);
    msg.setArticles(articles);    
    result = new FormatPassiveXmlProcess().newsXmlAnswer(msg);
    
//    result=new FormatPassiveXmlProcess().imageXmlAnswer(xmlEntity.getFromUserName(),
//        xmlEntity.getToUserName(), 
//        "http://pc.huochepiao.360.cn/images/banner_app.png");
    return result;
  }
}

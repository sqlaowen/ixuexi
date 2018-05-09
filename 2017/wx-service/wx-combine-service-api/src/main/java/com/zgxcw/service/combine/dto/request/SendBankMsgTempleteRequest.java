package com.zgxcw.service.combine.dto.request;

import java.io.Serializable;
import java.util.List;

public class SendBankMsgTempleteRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String wxId; // 微信网关id
  private String bankNo;// 银行卡号
  private String templateId; // 模板id
  private String detailUrl; // 详情url
  private List<String> fillContentList;// 模板填充内容集合,size=模板需要填充内容的个数,
                                       // 格式 : {\"value\":\"您好，购票未成功，已退款\",\"color\":\"#25428\"}

  public SendBankMsgTempleteRequest() {}

  /**
   * 微信网关id
   * 
   * @return
   */
  public String getWxId() {
    return wxId;
  }

  /**
   * 微信网关id
   * 
   * @param wxId
   */
  public void setWxId(String wxId) {
    this.wxId = wxId;
  }

  /**
   * 银行卡号
   * 
   * @return
   */
  public String getBankNo() {
    return bankNo;
  }

  /**
   * 银行卡号
   * 
   * @param bankNo
   */
  public void setBankNo(String bankNo) {
    this.bankNo = bankNo;
  }

  /**
   * 模板id
   * 
   * @return
   */
  public String getTemplateId() {
    return templateId;
  }

  /**
   * 模板id
   * 
   * @param templateId
   */
  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  /**
   * 详情url
   * 
   * @return
   */
  public String getDetailUrl() {
    return detailUrl;
  }

  /**
   * 详情url
   * 
   * @param detailUrl
   */
  public void setDetailUrl(String detailUrl) {
    this.detailUrl = detailUrl;
  }

  /**
   * 模板填充内容集合,size=模板需要填充内容的个数, 
   * 格式 : {\"value\":\"您好，购票未成功，已退款\",\"color\":\"#25428\"}
   * 
   * @return
   */
  public List<String> getFillContentList() {
    return fillContentList;
  }

  /**
   * 
   * 模板填充内容集合,size=模板需要填充内容的个数, 
   * 格式 : {\"value\":\"您好，购票未成功，已退款\",\"color\":\"#25428\"}
   * 
   * @param fillContentList
   */
  public void setFillContentList(List<String> fillContentList) {
    this.fillContentList = fillContentList;
  }
}

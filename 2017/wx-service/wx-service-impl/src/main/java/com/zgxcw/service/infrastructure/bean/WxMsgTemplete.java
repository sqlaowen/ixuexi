package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

/**
 * ΢��ģ����Ϣ
 */
public class WxMsgTemplete implements Serializable {

  private static final long serialVersionUID = 614137422741675223L;
  private String tempId;
  private String wxId;

  private String tempTitle;

  private String tempContent;

  private String industry1;

  private String industry2;
  private String forExample; // 样例

  public WxMsgTemplete() {}

  /**
   * ģ��id
   *
   * @return ģ��id
   */
  public String getTempId() {
    return tempId;
  }

  /**
   * ģ��id
   *
   * @param tempId ģ��id
   */
  public void setTempId(String tempId) {
    this.tempId = tempId == null ? null : tempId.trim();
  }

  /**
   * 微信网关id
   *
   * @return 微信网关id
   */
  public String getWxId() {
    return wxId;
  }

  /**
   * 微信网关id
   *
   * @param wxId 微信网关id
   */
  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  /**
   * ģ�����
   *
   * @return ģ�����
   */
  public String getTempTitle() {
    return tempTitle;
  }

  /**
   * ģ�����
   *
   * @param tempTitle ģ�����
   */
  public void setTempTitle(String tempTitle) {
    this.tempTitle = tempTitle == null ? null : tempTitle.trim();
  }

  /**
   * ģ������
   *
   * @return ģ������
   */
  public String getTempContent() {
    return tempContent;
  }

  /**
   * ģ������
   *
   * @param tempContent ģ������
   */
  public void setTempContent(String tempContent) {
    this.tempContent = tempContent == null ? null : tempContent.trim();
  }

  /**
   * ������ҵ1
   *
   * @return ������ҵ1
   */
  public String getIndustry1() {
    return industry1;
  }

  /**
   * ������ҵ1
   *
   * @param industry1 ������ҵ1
   */
  public void setIndustry1(String industry1) {
    this.industry1 = industry1 == null ? null : industry1.trim();
  }

  /**
   * ������ҵ2
   *
   * @return ������ҵ2
   */
  public String getIndustry2() {
    return industry2;
  }

  /**
   * ������ҵ2
   *
   * @param industry2 ������ҵ2
   */
  public void setIndustry2(String industry2) {
    this.industry2 = industry2 == null ? null : industry2.trim();
  }

  /**
   * 样例
   * 
   * @return
   */
  public String getForExample() {
    return forExample;
  }

  /**
   * 样例
   * 
   * @param forExample
   */
  public void setForExample(String forExample) {
    this.forExample = forExample;
  }


}

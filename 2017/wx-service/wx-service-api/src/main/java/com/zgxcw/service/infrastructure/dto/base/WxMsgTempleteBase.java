package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public abstract class WxMsgTempleteBase implements Serializable {

  private static final long serialVersionUID = -380298689598436421L;
  private String tempId;
  private String wxId;
  private String tempTitle;

  private String tempContent;

  private String industry1;

  private String industry2;
  private String forExample; // 样例

  /**
   * 模板id
   *
   * @return 模板id
   */
  public String getTempId() {
    return tempId;
  }

  /**
   * 模板id
   *
   * @param tempId 模板id
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
   * 模板标题
   *
   * @return 模板标题
   */
  public String getTempTitle() {
    return tempTitle;
  }

  /**
   * 模板标题
   *
   * @param tempTitle 模板标题
   */
  public void setTempTitle(String tempTitle) {
    this.tempTitle = tempTitle == null ? null : tempTitle.trim();
  }

  /**
   * 模板内容
   *
   * @return 模板内容
   */
  public String getTempContent() {
    return tempContent;
  }

  /**
   * 模板内容
   *
   * @param tempContent 模板内容
   */
  public void setTempContent(String tempContent) {
    this.tempContent = tempContent == null ? null : tempContent.trim();
  }

  /**
   * 所属行业1
   *
   * @return 所属行业1
   */
  public String getIndustry1() {
    return industry1;
  }

  /**
   * 所属行业1
   *
   * @param industry1 所属行业1
   */
  public void setIndustry1(String industry1) {
    this.industry1 = industry1 == null ? null : industry1.trim();
  }

  /**
   * 所属行业2
   *
   * @return 所属行业2
   */
  public String getIndustry2() {
    return industry2;
  }

  /**
   * 所属行业2
   *
   * @param industry2 所属行业2
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

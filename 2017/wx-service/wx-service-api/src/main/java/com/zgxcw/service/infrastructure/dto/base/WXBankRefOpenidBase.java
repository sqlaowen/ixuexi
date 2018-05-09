package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

public abstract class WXBankRefOpenidBase implements Serializable {

  private static final long serialVersionUID = 1445428148251486053L;

  // 微信网关id
  private String wxId;

  //银行名称
  private String bankName;

  // 银行卡号
  private String bankNo;

  // openid
  private String openid;

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
    this.wxId = wxId == null ? null : wxId.trim();
  }

  /**
   * 银行名称
   * @return
   */
  public String getBankName() {
      return bankName;
  }

  /**
   * 银行名称
   * @param bankName
   */
  public void setBankName(String bankName) {
      this.bankName = bankName;
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
    this.bankNo = bankNo == null ? null : bankNo.trim();
  }

  /**
   * openid
   *
   * @return
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * openid
   *
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
  }

}

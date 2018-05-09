package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

public class WXBankUNRefOpenidLog implements Serializable {

  private static final long serialVersionUID = 4429705009844157925L;

  // 记录id
  private String logId;

  // 关联id
  private String refId;

  //银行名称
  private String bankName;

  // 银行卡号
  private String bankNo;

  // 创建时间
  private Long createTime;

  // 创建操作人
  private String createUser;

  /**
   * 记录id
   *
   * @return
   */
  public String getLogId() {
    return logId;
  }

  /**
   * 记录id
   *
   * @param logId
   */
  public void setLogId(String logId) {
    this.logId = logId == null ? null : logId.trim();
  }

  /**
   * 关联id
   *
   * @return
   */
  public String getRefId() {
    return refId;
  }

  /**
   * 关联id
   *
   * @param refId
   */
  public void setRefId(String refId) {
    this.refId = refId == null ? null : refId.trim();
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
   * 创建时间
   *
   * @return
   */
  public Long getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   *
   * @param createTime
   */
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  /**
   * 创建操作人
   *
   * @return
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * 创建操作人
   *
   * @param createUser
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}

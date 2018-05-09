package com.example;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : wenshiwei
 * @description :
 * @date : 2017/12/28
 */
public class InvoiceBusinessBillLedgerQueryMO implements Serializable {

    /**
     * 业务单据号（结算单号、收付款单号）
     */
    private String rfBillNo;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 周期开始时间
     */
    private Date beginDate;

    /**
     * 周期结束时间
     */
    private Date endDate;

    /**
     * 状态:[1:未核销;2:部分核销;3:已核销]  参见枚举：LedgerVerificationStatusEnum
     */
    private Integer verificationStatus;

    public InvoiceBusinessBillLedgerQueryMO() {
    }

    public String getRfBillNo() {
        return rfBillNo;
    }

    public void setRfBillNo(String rfBillNo) {
        this.rfBillNo = rfBillNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Integer verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}

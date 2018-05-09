package com.example;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class InvoiceBusinessBillLedger implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 业务单据类型 1.结算单 2.收付费
     */
    private Integer rfBillType;

    /**
     * 业务单据号（结算单号、收付款单号）
     */
    private String rfBillNo;

    /**
     * 明细条数
     */
    private Long detailCount;

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
     * 状态:未核销;部分核销;已核销  参见枚举：LedgerVerificationStatusEnum
     */
    private Integer verificationStatus;

    /**
     * 应核销金额
     */
    private BigDecimal shouldVerificationAmount;

    /**
     * 已核销金额
     */
    private BigDecimal realVerificationAmount;

    /**
     * 冻结核销金额
     */
    private BigDecimal frozenVerificationAmount;

    /**
     * 核销完成日期
     */
    private Date verificationDate;

    /**
     * 状态:未开票;部分开票;已开票 参见枚举：LedgerInvoiceStatusEnum
     */
    private Integer invoiceStatus;

    /**
     * 应开票金额
     */
    private BigDecimal shouldInvoiceAmount;

    /**
     * 已开票金额
     */
    private BigDecimal realInvoiceAmount;

    /**
     * 冻结开票金额
     */
    private BigDecimal frozenInvoiceAmount;

    /**
     * 开票完成日期
     */
    private Date invoiceDate;

    /**
     * 状态:初始;已关单;已校验  参见枚举：LedgerBillStatusEnum
     */
    private Integer billStatus;

    /**
     * 业务单据关单时间
     */
    private Date rfBillFinishTime;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRfBillType() {
        return rfBillType;
    }

    public void setRfBillType(Integer rfBillType) {
        this.rfBillType = rfBillType;
    }

    public String getRfBillNo() {
        return rfBillNo;
    }

    public void setRfBillNo(String rfBillNo) {
        this.rfBillNo = rfBillNo;
    }

    public Long getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Long detailCount) {
        this.detailCount = detailCount;
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

    public BigDecimal getShouldVerificationAmount() {
        return shouldVerificationAmount;
    }

    public void setShouldVerificationAmount(BigDecimal shouldVerificationAmount) {
        this.shouldVerificationAmount = shouldVerificationAmount;
    }

    public BigDecimal getRealVerificationAmount() {
        return realVerificationAmount;
    }

    public void setRealVerificationAmount(BigDecimal realVerificationAmount) {
        this.realVerificationAmount = realVerificationAmount;
    }

    public BigDecimal getFrozenVerificationAmount() {
        return frozenVerificationAmount;
    }

    public void setFrozenVerificationAmount(BigDecimal frozenVerificationAmount) {
        this.frozenVerificationAmount = frozenVerificationAmount;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public BigDecimal getShouldInvoiceAmount() {
        return shouldInvoiceAmount;
    }

    public void setShouldInvoiceAmount(BigDecimal shouldInvoiceAmount) {
        this.shouldInvoiceAmount = shouldInvoiceAmount;
    }

    public BigDecimal getRealInvoiceAmount() {
        return realInvoiceAmount;
    }

    public void setRealInvoiceAmount(BigDecimal realInvoiceAmount) {
        this.realInvoiceAmount = realInvoiceAmount;
    }

    public BigDecimal getFrozenInvoiceAmount() {
        return frozenInvoiceAmount;
    }

    public void setFrozenInvoiceAmount(BigDecimal frozenInvoiceAmount) {
        this.frozenInvoiceAmount = frozenInvoiceAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Date getRfBillFinishTime() {
        return rfBillFinishTime;
    }

    public void setRfBillFinishTime(Date rfBillFinishTime) {
        this.rfBillFinishTime = rfBillFinishTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
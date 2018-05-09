package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.MergePaymentBase;

public class MergePaymentResDto extends MergePaymentBase implements Serializable {

	private static final long serialVersionUID = 7846532799657371230L;

	public MergePaymentResDto() {
	}

	// 合并支付id
	private String mergePaymentId;

	// 支付单状态id
	private Integer paymentStateId;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	// update_time
	private Date updateTime;

	// update_user
	private String updateUser;

	// 实际支付时间
	private Date tradeTime;

	// 订单关闭时间
	private Date orderCloseTime;

	// 交易关闭时间
	private Date tradeCloseTime;

	// 第三方交易号(ali/wechat流水号)
	private String thirdTradeNo;

	// 买家账号
	private String buyerName;
	
	/**
	 * 合并支付id
	 *
	 * @return
	 */
	public String getMergePaymentId() {
		return mergePaymentId;
	}

	/**
	 * 合并支付id
	 *
	 * @param mergePaymentId
	 */
	public void setMergePaymentId(String mergePaymentId) {
		this.mergePaymentId = mergePaymentId == null ? null : mergePaymentId.trim();
	}

	/**
	 * 支付单状态id
	 *
	 * @return
	 */
	public Integer getPaymentStateId() {
		return paymentStateId;
	}

	/**
	 * 支付单状态id
	 *
	 * @param paymentStateId
	 */
	public void setPaymentStateId(Integer paymentStateId) {
		this.paymentStateId = paymentStateId;
	}

	/**
	 * create_time
	 *
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * create_time
	 *
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * create_user
	 *
	 * @return
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * create_user
	 *
	 * @param createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	/**
	 * update_time
	 *
	 * @return
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * update_time
	 *
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * update_user
	 *
	 * @return
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * update_user
	 *
	 * @param updateUser
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	/**
	 * 实际支付时间
	 *
	 * @return
	 */
	public Date getTradeTime() {
		return tradeTime;
	}

	/**
	 * 实际支付时间
	 *
	 * @param tradeTime
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * 订单关闭时间
	 *
	 * @return
	 */
	public Date getOrderCloseTime() {
		return orderCloseTime;
	}

	/**
	 * 订单关闭时间
	 *
	 * @param orderCloseTime
	 */
	public void setOrderCloseTime(Date orderCloseTime) {
		this.orderCloseTime = orderCloseTime;
	}

	/**
	 * 交易关闭时间
	 *
	 * @return
	 */
	public Date getTradeCloseTime() {
		return tradeCloseTime;
	}

	/**
	 * 交易关闭时间
	 *
	 * @param tradeCloseTime
	 */
	public void setTradeCloseTime(Date tradeCloseTime) {
		this.tradeCloseTime = tradeCloseTime;
	}

	/**
	 * 第三方交易号(ali/wechat流水号)
	 *
	 * @return
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	/**
	 * 第三方交易号(ali/wechat流水号)
	 *
	 * @param thirdTradeNo
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
	}

	/**
	 * 买家账号
	 * 
	 * @return
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * 买家账号
	 * 
	 * @param buyerName
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

}

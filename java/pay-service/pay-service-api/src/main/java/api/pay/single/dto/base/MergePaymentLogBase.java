package api.pay.single.dto.base;

import java.io.Serializable;

public abstract class MergePaymentLogBase implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

	// 合并支付单id
	private String mergePaymentId;

	// 支付单状态id
	private Integer paymentStateId;

	// 备注
	private String paymentNote;

	/**
	 * 合并支付单id
	 * 
	 * @return
	 */
	public String getMergePaymentId() {
		return mergePaymentId;
	}

	/**
	 * 合并支付单id
	 * 
	 * @param mergePaymentId
	 */
	public void setMergePaymentId(String mergePaymentId) {
		this.mergePaymentId = mergePaymentId;
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
	 * 备注
	 * 
	 * @return
	 */
	public String getPaymentNote() {
		return paymentNote;
	}

	/**
	 * 备注
	 * 
	 * @param paymentNote
	 */
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

}

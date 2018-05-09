package api.pay.single.dto.base;

public abstract class PaymentLogBase {

	// 支付单id
	private String paymentId;

	// 支付单状态id
	private Integer paymentStateId;

	// 备注
	private String paymentNote;
	
	/**
	 * 支付单id
	 *
	 * @return
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * 支付单id
	 *
	 * @param paymentId
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId == null ? null : paymentId.trim();
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

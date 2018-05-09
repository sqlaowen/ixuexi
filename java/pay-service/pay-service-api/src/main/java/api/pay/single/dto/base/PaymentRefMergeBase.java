package api.pay.single.dto.base;

public abstract class PaymentRefMergeBase {

	// 合并支付id
	private String mergePaymentId;

	// 支付单id
	private String paymentId;

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

}

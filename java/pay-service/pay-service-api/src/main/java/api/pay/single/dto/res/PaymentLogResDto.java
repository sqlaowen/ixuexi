package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentLogBase;

public class PaymentLogResDto extends PaymentLogBase implements Serializable {

	private static final long serialVersionUID = 4632195513312694365L;

	public PaymentLogResDto() {
	}

	// 支付单状态日志id
	private String paymentLogId;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * 支付单状态日志id
	 *
	 * @return
	 */
	public String getPaymentLogId() {
		return paymentLogId;
	}

	/**
	 * 支付单状态日志id
	 *
	 * @param paymentLogId
	 */
	public void setPaymentLogId(String paymentLogId) {
		this.paymentLogId = paymentLogId == null ? null : paymentLogId.trim();
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
}

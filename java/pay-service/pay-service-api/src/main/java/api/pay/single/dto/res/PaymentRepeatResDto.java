package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentRepeatBase;

public class PaymentRepeatResDto extends PaymentRepeatBase implements Serializable {

	private static final long serialVersionUID = 7697700251602794801L;

	public PaymentRepeatResDto() {
	}

	// 重复支付id
	private String reapeatId;

	// create_time
	private Date createTime;

	// create_user
	private String createUser;

	/**
	 * 重复支付id
	 *
	 * @return
	 */
	public String getReapeatId() {
		return reapeatId;
	}

	/**
	 * 重复支付id
	 *
	 * @param reapeatId
	 */
	public void setReapeatId(String reapeatId) {
		this.reapeatId = reapeatId == null ? null : reapeatId.trim();
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

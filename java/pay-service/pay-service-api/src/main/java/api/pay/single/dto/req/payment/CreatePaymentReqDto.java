package api.pay.single.dto.req.payment;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentBase;

public class CreatePaymentReqDto extends PaymentBase implements Serializable {

	private static final long serialVersionUID = 2237924644457258593L;

	public CreatePaymentReqDto() {
	}

	// create_user
	private String createUser;

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

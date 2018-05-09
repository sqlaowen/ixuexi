package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentLogBase;

public class CreatePaymentLogReqDto extends PaymentLogBase implements Serializable {

	private static final long serialVersionUID = -4689087886447352039L;

	public CreatePaymentLogReqDto() {
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

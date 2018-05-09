package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentRefMergeBase;

public class CreatePaymentRefMergeReqDto extends PaymentRefMergeBase implements Serializable {

	private static final long serialVersionUID = 1100381666287713602L;

	public CreatePaymentRefMergeReqDto() {
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

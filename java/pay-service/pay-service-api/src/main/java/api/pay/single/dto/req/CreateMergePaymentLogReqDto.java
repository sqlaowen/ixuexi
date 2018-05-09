package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.MergePaymentLogBase;

public class CreateMergePaymentLogReqDto extends MergePaymentLogBase implements Serializable {

	private static final long serialVersionUID = 3075082760008431452L;

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

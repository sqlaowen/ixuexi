package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.AliNotifyBase;

public class CreateAliNotifyReqDto extends AliNotifyBase implements Serializable {

	private static final long serialVersionUID = -8865448436040138127L;

	public CreateAliNotifyReqDto() {
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

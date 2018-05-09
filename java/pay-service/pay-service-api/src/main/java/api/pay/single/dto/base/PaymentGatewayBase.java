package api.pay.single.dto.base;

public abstract class PaymentGatewayBase {

	// 平台商户id
	private String merchantId;

	// 平台商户名
	private String merchantName;

	// 支付网关名称
	private String gatewayName;

	// 支付网关编码 [com.pay.consts.GatewayCodeConst]
	private String gatewayCode;

	// 状态[0:可用, 1:不可用]
	private Integer gatewayState;

	// 秘钥
	private String gatewayKey;

	// 账号[ali:合作者身份id, wx:公众号id]]
	private String gatewayAccount;

	// 用户名[ali:收款方支付宝账号, wx:商户号id]]
	private String gatewayUserName;

	// 支付返回url
	private String returnUrl;

	// 支付通知url
	private String notifyUrl;

	// 支付订单api
	private String payApi;

	// 查询订单api
	private String queryApi;

	// 关闭订单api
	private String closeApi;

	// 退款订单api
	private String refundApi;

	// 消息验证api
	private String verifyApi;

	// 验签公钥
	private String publicKey;

	// 证书路径
	private String certPath;

	// 加密方式 [wx:MD5, ali:[PC:MD5,移动APP:RSA]]
	private String signType;

	// 接口版本
	private String gatewayVersion;

	/**
	 * 平台商户id
	 *
	 * @return
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * 平台商户id
	 *
	 * @param merchantId
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId == null ? null : merchantId.trim();
	}

	/**
	 * 平台商户名
	 * 
	 * @return
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 平台商户名
	 * 
	 * @param merchantName
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * 支付网关名称
	 *
	 * @return
	 */
	public String getGatewayName() {
		return gatewayName;
	}

	/**
	 * 支付网关名称
	 *
	 * @param gatewayName
	 */
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName == null ? null : gatewayName.trim();
	}

	/**
	 * 支付网关编码
	 * 
	 * @see com.pay.consts.GatewayCodeConst
	 * @return
	 */
	public String getGatewayCode() {
		return gatewayCode;
	}

	/**
	 * 支付网关编码
	 * 
	 * @see com.pay.consts.GatewayCodeConst
	 * @param gatewayCode
	 */
	public void setGatewayCode(String gatewayCode) {
		this.gatewayCode = gatewayCode == null ? null : gatewayCode.trim();
	}

	/**
	 * 状态[0:可用, 1:不可用]
	 *
	 * @return
	 */
	public Integer getGatewayState() {
		return gatewayState;
	}

	/**
	 * 状态[0:可用, 1:不可用]
	 *
	 * @param gatewayState
	 */
	public void setGatewayState(Integer gatewayState) {
		this.gatewayState = gatewayState;
	}

	/**
	 * 秘钥
	 *
	 * @return
	 */
	public String getGatewayKey() {
		return gatewayKey;
	}

	/**
	 * 秘钥
	 *
	 * @param gatewayKey
	 */
	public void setGatewayKey(String gatewayKey) {
		this.gatewayKey = gatewayKey == null ? null : gatewayKey.trim();
	}

	/**
	 * 账号[ali:合作者身份id, wx:公众号id]]
	 *
	 * @return
	 */
	public String getGatewayAccount() {
		return gatewayAccount;
	}

	/**
	 * 账号[ali:合作者身份id, wx:公众号id]]
	 *
	 * @param gatewayAccount
	 */
	public void setGatewayAccount(String gatewayAccount) {
		this.gatewayAccount = gatewayAccount == null ? null : gatewayAccount.trim();
	}

	/**
	 * 用户名[ali:收款方支付宝账号, wx:商户号id]]
	 *
	 * @return
	 */
	public String getGatewayUserName() {
		return gatewayUserName;
	}

	/**
	 * 用户名[ali:收款方支付宝账号, wx:商户号id]]
	 *
	 * @param gatewayUserName
	 */
	public void setGatewayUserName(String gatewayUserName) {
		this.gatewayUserName = gatewayUserName == null ? null : gatewayUserName.trim();
	}

	/**
	 * 支付返回url
	 *
	 * @return
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * 支付返回url
	 *
	 * @param returnUrl
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl == null ? null : returnUrl.trim();
	}

	/**
	 * 支付通知url
	 *
	 * @return
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * 支付通知url
	 *
	 * @param notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
	}

	/**
	 * 支付订单api
	 *
	 * @return
	 */
	public String getPayApi() {
		return payApi;
	}

	/**
	 * 支付订单api
	 *
	 * @param payApi
	 */
	public void setPayApi(String payApi) {
		this.payApi = payApi == null ? null : payApi.trim();
	}

	/**
	 * 查询订单api
	 *
	 * @return
	 */
	public String getQueryApi() {
		return queryApi;
	}

	/**
	 * 查询订单api
	 *
	 * @param queryApi
	 */
	public void setQueryApi(String queryApi) {
		this.queryApi = queryApi == null ? null : queryApi.trim();
	}

	/**
	 * 关闭订单api
	 *
	 * @return
	 */
	public String getCloseApi() {
		return closeApi;
	}

	/**
	 * 关闭订单api
	 *
	 * @param closeApi
	 */
	public void setCloseApi(String closeApi) {
		this.closeApi = closeApi == null ? null : closeApi.trim();
	}

	/**
	 * 退款订单api
	 *
	 * @return
	 */
	public String getRefundApi() {
		return refundApi;
	}

	/**
	 * 退款订单api
	 *
	 * @param refundApi
	 */
	public void setRefundApi(String refundApi) {
		this.refundApi = refundApi == null ? null : refundApi.trim();
	}

	/**
	 * 消息验证api
	 * 
	 * @return
	 */
	public String getVerifyApi() {
		return verifyApi;
	}

	/**
	 * 消息验证api
	 * 
	 * @param verifyApi
	 */
	public void setVerifyApi(String verifyApi) {
		this.verifyApi = verifyApi;
	}

	/**
	 * 验签公钥
	 * 
	 * @return
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * 验签公钥
	 * 
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * 证书路径
	 * 
	 * @return
	 */
	public String getCertPath() {
		return certPath;
	}

	/**
	 * 证书路径
	 * 
	 * @param certPath
	 */
	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	/**
	 * 加密方式 [wx:MD5, ali:[PC:MD5,移动APP:RSA]]
	 * 
	 * @return
	 */
	public String getSignType() {
		return signType;
	}

	/**
	 * 加密方式 [wx:MD5, ali:[PC:MD5,移动APP:RSA]]
	 * 
	 * @param signType
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}

	/**
	 * 接口版本
	 *
	 * @return
	 */
	public String getGatewayVersion() {
		return gatewayVersion;
	}

	/**
	 * 接口版本
	 *
	 * @param gatewayVersion
	 */
	public void setGatewayVersion(String gatewayVersion) {
		this.gatewayVersion = gatewayVersion == null ? null : gatewayVersion.trim();
	}

}

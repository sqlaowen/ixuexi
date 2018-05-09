package api.pay.combine.dto;

import java.io.Serializable;
import java.util.Date;

public class PayItemReqDto implements Serializable {

	private static final long serialVersionUID = -2643947010608903508L;

	public PayItemReqDto() {
	}

	private String orderNo;// 订单号
	private Long totalFee;// 总金额(分)
	private String spName;// 商品名称
	private String spDetail;// 商品描述
	private Date timeOut;// 超时时间
	private String extraCommonParam;// 公用回传参数

	/**
	 * 订单号
	 * 
	 * @return
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单号
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 总金额(分)
	 *
	 * @return
	 */
	public Long getTotalFee() {
		return totalFee;
	}

	/**
	 * 总金额(分)
	 *
	 * @param totalFee
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 商品名称
	 *
	 * @return
	 */
	public String getSpName() {
		return spName;
	}

	/**
	 * 商品名称
	 *
	 * @param spName
	 */
	public void setSpName(String spName) {
		this.spName = spName == null ? null : spName.trim();
	}

	/**
	 * 商品描述
	 *
	 * @return
	 */
	public String getSpDetail() {
		return spDetail;
	}

	/**
	 * 商品描述
	 *
	 * @param spDetail
	 */
	public void setSpDetail(String spDetail) {
		this.spDetail = spDetail == null ? null : spDetail.trim();
	}

	/**
	 * 超时时间
	 *
	 * @return
	 */
	public Date getTimeOut() {
		return timeOut;
	}

	/**
	 * 超时时间
	 *
	 * @param timeOut
	 */
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * 公用回传参数
	 *
	 * @return
	 */
	public String getExtraCommonParam() {
		return extraCommonParam;
	}

	/**
	 * 公用回传参数
	 *
	 * @param extraCommonParam
	 */
	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
	}

}

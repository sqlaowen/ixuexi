package com.pay.single.dao;

import org.apache.ibatis.annotations.Param;

import com.pay.single.bean.PaymentGateway;

public interface PaymentGatewayMapper {

	PaymentGateway findById(String gatewayId);

	// 根据网关编码和商户id查询
	PaymentGateway findByCodeAndMerchantId(@Param("merchantId") String merchantId,
			@Param("gatewayCode") String gatewayCode);
}

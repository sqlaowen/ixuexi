package com.zgxcw.service.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zgxcw.service.infrastructure.bean.WXBankRefOpenid;

public interface WXBankRefOpenidMapper {

  int saveOne(WXBankRefOpenid record);

  WXBankRefOpenid findById(String refId);

  List<WXBankRefOpenid> findByBankNo(@Param("bankNo") String bankNo,@Param("wxId") String wxId);
  
  WXBankRefOpenid findByOpenid(@Param("openid") String openid,@Param("wxId") String wxId);

  int editBankNo(@Param("bankName") String bankName,@Param("bankNo") String bankNo, @Param("openid") String openid,@Param("wxId") String wxId);
}

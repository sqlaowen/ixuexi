package com.zgxcw.service.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zgxcw.service.infrastructure.bean.WXUserRefOpenid;

public interface WXUserRefOpenidMapper {

  int saveOne(WXUserRefOpenid record);

  WXUserRefOpenid findById(String refId);

  // <!-- 根据wxId, 企业id里查询 -->
  List<WXUserRefOpenid> findByAccountId(@Param("wxId") String wxId, @Param("qiyeId") String qiyeId);
  
  // <!-- 根据wxId, 企业id, openid -->
  WXUserRefOpenid findByQiyeidOpenid(@Param("wxId") String wxId, @Param("openid") String openid,
      @Param("qiyeId") String qiyeId, @Param("yhuId") String yhuId);
}

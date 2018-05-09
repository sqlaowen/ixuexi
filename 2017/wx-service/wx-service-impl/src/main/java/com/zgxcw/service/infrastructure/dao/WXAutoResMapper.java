package com.zgxcw.service.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zgxcw.service.infrastructure.bean.WXAutoRes;

public interface WXAutoResMapper {

  WXAutoRes findById(String resId);
  
  WXAutoRes findTheSet(@Param("msgType") Integer msgType,@Param("wxId") String wxId);
  
  List<WXAutoRes> findByResOrMsgType(@Param("resType") String resType,@Param("msgType") String msgType,@Param("wxId") String wxId);

  int saveOne(WXAutoRes model);

  int editById(WXAutoRes model);

  int deleteById(String resId);
  
  int editOthersDisable(@Param("resId") String resId,@Param("msgType") String msgType,@Param("wxId") String wxId);
  
  List<WXAutoRes> getPageList(@Param("wxId") String wxId,@Param("resName") String resName,@Param("resType") String resType,@Param("resState") Integer resState,@Param("msgType") Integer msgType);

}

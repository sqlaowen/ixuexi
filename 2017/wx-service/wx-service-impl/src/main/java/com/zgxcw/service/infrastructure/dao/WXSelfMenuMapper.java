package com.zgxcw.service.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zgxcw.service.infrastructure.bean.WXSelfMenu;

public interface WXSelfMenuMapper {

  WXSelfMenu findById(String menuId);

  List<WXSelfMenu> findList(String wxId);
  
  List<WXSelfMenu> findChildById(String wxId);
  
  WXSelfMenu findByMenuSeq(@Param("menuSeq") Integer menuSeq,@Param("wxId") String wxId);

  int saveOne(WXSelfMenu model);
  
  int editById(WXSelfMenu model);

  int delAll(String wxId);
  
  int delById(String menuId);

}

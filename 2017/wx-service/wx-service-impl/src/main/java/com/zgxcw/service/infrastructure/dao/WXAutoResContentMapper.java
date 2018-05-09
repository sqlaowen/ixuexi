package com.zgxcw.service.infrastructure.dao;

import java.util.List;

import com.zgxcw.service.infrastructure.bean.WXAutoResContent;

public interface WXAutoResContentMapper {

  int saveOne(WXAutoResContent model);

  WXAutoResContent findById(String contentId);

  List<WXAutoResContent> findByResId(String resId);
  
  int eidtById(WXAutoResContent model);

  int deleteByResId(String resId);
}

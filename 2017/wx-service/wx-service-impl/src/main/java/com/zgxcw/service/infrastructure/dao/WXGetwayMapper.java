package com.zgxcw.service.infrastructure.dao;

import com.zgxcw.service.infrastructure.bean.WXGetway;

public interface WXGetwayMapper {

  WXGetway findById(String wxId);

  int saveOne(WXGetway model);

  int editById(WXGetway model);

}

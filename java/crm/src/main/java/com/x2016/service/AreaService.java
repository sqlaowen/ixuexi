package com.x2016.service;

import com.x2016.poco.SysArea;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;

public interface AreaService {

  PageRes<SysArea> findList(PageReq<SysArea> req);
}

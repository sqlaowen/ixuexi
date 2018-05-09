package com.x2016.service;

import com.x2016.poco.SysArea;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

public interface SysAreaService {

  PageRes<SysArea> findList(PageReq<SysArea> req);
}

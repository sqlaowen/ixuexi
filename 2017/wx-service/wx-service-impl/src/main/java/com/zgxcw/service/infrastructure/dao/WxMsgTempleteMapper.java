package com.zgxcw.service.infrastructure.dao;

import com.zgxcw.service.infrastructure.bean.WxMsgTemplete;

/**
 * Created by huolh on 2016/7/26.
 */
public interface WxMsgTempleteMapper {
    WxMsgTemplete findById(String tempId);

    int saveOne(WxMsgTemplete model);
    
    int delAll(String wxId);
}

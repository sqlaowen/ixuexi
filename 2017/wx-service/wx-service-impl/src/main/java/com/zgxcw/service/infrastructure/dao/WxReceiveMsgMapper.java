package com.zgxcw.service.infrastructure.dao;

import com.zgxcw.service.infrastructure.bean.WxReceiveMsg;

/**
 * Created by huolh on 2016/7/25.
 */
public interface WxReceiveMsgMapper {

    WxReceiveMsg findById(String id);

    int saveOne(WxReceiveMsg model);
}

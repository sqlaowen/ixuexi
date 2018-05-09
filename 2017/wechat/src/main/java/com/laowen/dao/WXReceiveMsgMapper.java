package com.laowen.dao;

import com.laowen.bean.WXReceiveMsg;
import org.springframework.stereotype.Repository;

/**
 * 微信收到的消息
 */
@Repository
public interface WXReceiveMsgMapper {

    int insertOne(WXReceiveMsg wxReceiveMsg);

}
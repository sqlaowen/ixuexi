package com.laowen.service;

import com.laowen.bean.FansRefCard;

import java.util.List;

public interface FansRefCardService {

    /**
     * 保存
     *
     * @param fansRefCard
     * @return
     */
    int addOrUpdate(FansRefCard fansRefCard);

    /**
     * 通过openId查询
     *
     * @param openId
     * @return
     */
    List<FansRefCard> findByOpenId(String openId);

}
package com.warez.service;

import com.warez.bean.ECGoods;
import com.warez.utils.PageRes;

public interface ECGoodsService {

    /**
     * 增加
     *
     * @param ecGoods
     * @return
     */
    String insertOne(ECGoods ecGoods);

    /**
     * 分页查
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageRes<ECGoods> selectList(Integer pageNum, Integer pageSize);

}
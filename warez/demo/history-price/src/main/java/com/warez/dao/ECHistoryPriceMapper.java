package com.warez.dao;

import com.warez.bean.ECHistoryPrice;

import java.util.List;

public interface ECHistoryPriceMapper {

    /**
     * 增加
     *
     * @param ecHistoryPrice
     * @return
     */
    int insertOne(ECHistoryPrice ecHistoryPrice);

    /**
     * 通过goodsId查询某个商品的价格
     *
     * @param goodsId
     * @return
     */
    List<ECHistoryPrice> selectByGoodsId(String goodsId);
}
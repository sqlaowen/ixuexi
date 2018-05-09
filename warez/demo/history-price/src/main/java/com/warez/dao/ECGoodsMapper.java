package com.warez.dao;

import com.warez.bean.ECGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ECGoodsMapper {

    /**
     * 添加
     *
     * @param ecGoods
     * @return
     */
    int insertOne(ECGoods ecGoods);

    /**
     * 根据skuId和platformId查
     *
     * @param skuId
     * @param platformId
     * @return
     */
    ECGoods selectBySKUAndPlatform(@Param("skuId") String skuId, @Param("platformId") Integer platformId);

    /**
     * 分页用于更新价格
     *
     * @return
     */
    List<ECGoods> selectList();

    /**
     * 更新
     *
     * @param ecGoods
     * @return
     */
    int updateById(ECGoods ecGoods);
}
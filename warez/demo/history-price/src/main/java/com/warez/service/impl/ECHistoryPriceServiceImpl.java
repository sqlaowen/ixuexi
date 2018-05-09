package com.warez.service.impl;

import com.warez.bean.ECGoods;
import com.warez.bean.ECHistoryPrice;
import com.warez.dao.ECGoodsMapper;
import com.warez.dao.ECHistoryPriceMapper;
import com.warez.service.ECHistoryPriceService;
import com.warez.utils.RandomGUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by iyou on 2016/12/18.
 */
public class ECHistoryPriceServiceImpl implements ECHistoryPriceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ECHistoryPriceMapper ecHistoryPriceMapper;
    private ECGoodsMapper ecGoodsMapper;

    //region 注入

    public void setEcHistoryPriceMapper(ECHistoryPriceMapper ecHistoryPriceMapper) {
        this.ecHistoryPriceMapper = ecHistoryPriceMapper;
    }

    public void setEcGoodsMapper(ECGoodsMapper ecGoodsMapper) {
        this.ecGoodsMapper = ecGoodsMapper;
    }

    //endregion

    @Override
    public String insertOne(ECHistoryPrice ecHistoryPrice) {
        if (ecHistoryPrice.getSkuPrice().startsWith("-")) { // 商品下架, -1.00
            log.info("商品已下架, 对应 goods_id:{}, sku_price:{}", ecHistoryPrice.getGoodsId(), ecHistoryPrice.getSkuPrice());
            ECGoods ecGoods = new ECGoods();
            ecGoods.setGoodsId(ecHistoryPrice.getGoodsId());
            ecGoods.setSkuState(1);
            ecGoods.setUpdateTime(new Date());
            ecGoodsMapper.updateById(ecGoods);
        }
        String id = new RandomGUID().toString();
        ecHistoryPrice.setPriceId(id);
        ecHistoryPrice.setPriceState(0);
        ecHistoryPrice.setCreateTime(new Date());
        ecHistoryPriceMapper.insertOne(ecHistoryPrice);
        return id;
    }

}

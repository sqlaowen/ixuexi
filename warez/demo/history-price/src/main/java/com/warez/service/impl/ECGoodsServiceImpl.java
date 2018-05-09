package com.warez.service.impl;

import com.github.pagehelper.PageHelper;
import com.warez.bean.ECGoods;
import com.warez.dao.ECGoodsMapper;
import com.warez.service.ECGoodsService;
import com.warez.utils.PageRes;
import com.warez.utils.RandomGUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by iyou on 2016/12/18.
 */
public class ECGoodsServiceImpl implements ECGoodsService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //region 注入
    private ECGoodsMapper ecGoodsMapper;
    public void setEcGoodsMapper(ECGoodsMapper ecGoodsMapper) {
        this.ecGoodsMapper = ecGoodsMapper;
    }
    //endregion

    @Override
    public String insertOne(ECGoods ecGoods) {
        ECGoods ec = ecGoodsMapper.selectBySKUAndPlatform(ecGoods.getSkuId(), ecGoods.getPlatformId());
        if (null != ec) {
            log.info("商品已存在, 对应sku:{}, 平台:{}", ecGoods.getSkuId(), ecGoods.getPlatformId());
            ecGoods.setGoodsId(ec.getGoodsId());
            ecGoods.setSkuState(0);
            ecGoods.setUpdateTime(new Date());
            ecGoodsMapper.updateById(ecGoods);
            return ec.getGoodsId();
        }
        String id = new RandomGUID().toString();
        ecGoods.setGoodsId(id);
        ecGoods.setSkuState(0);
        ecGoods.setCreateTime(new Date());
        ecGoodsMapper.insertOne(ecGoods);
        return id;
    }

    @Override
    public PageRes<ECGoods> selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ECGoods> ecGoodsList = ecGoodsMapper.selectList();
        PageRes<ECGoods> resList = new PageRes<ECGoods>(ecGoodsList);
        return resList;
    }

}

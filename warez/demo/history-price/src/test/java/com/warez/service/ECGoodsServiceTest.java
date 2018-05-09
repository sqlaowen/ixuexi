package com.warez.service;

import com.warez.bean.ECGoods;
import com.warez.bean.ECHistoryPrice;
import com.warez.utils.PageRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by iyou on 2016/12/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class ECGoodsServiceTest {

    @Autowired
    private ECGoodsService ecGoodsService;

    @Autowired
    private ECHistoryPriceService ecHistoryPriceService;

    @Test
    public void test01() {
        ECGoods ecGoods = new ECGoods();
        ecGoods.setPlatformId(2);
        ecGoods.setSkuId("1");
        String goodsId = ecGoodsService.insertOne(ecGoods);
        System.out.println("输出：" + goodsId);
    }

    @Test
    public void test011() {
        ECHistoryPrice ecHistoryPrice = new ECHistoryPrice();
        ecHistoryPrice.setGoodsId("1");
        ecHistoryPrice.setSkuPrice("1");
        ecHistoryPrice.setCreateUser("root");
        String priceId = ecHistoryPriceService.insertOne(ecHistoryPrice);
        System.out.println("输出：" + priceId);
    }


    @Test
    public void test02() {
        PageRes<ECGoods> list = ecGoodsService.selectList(3, 60);
        System.out.println(list.getTotal());
        System.out.println(list.getPages());
    }

    @Autowired
    private GrabPriceService grabPriceService;

    @Test
    public void test03(){
        grabPriceService.grabSku();
    }

    @Test
    public void test04(){
        grabPriceService.grabPrice();
    }
}

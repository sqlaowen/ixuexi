package service;

import com.alibaba.fastjson.JSON;
import impl.laowen.bean.ECGoods;
import impl.laowen.bean.ECHistoryPrice;
import impl.laowen.dao.ECGoodsMapper;
import impl.laowen.service.ECGoodsService;
import impl.laowen.service.ECHistoryPriceService;
import impl.laowen.service.PaChongService;
import impl.laowen.utils.PageRes;
import impl.laowen.utils.RandomGUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

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
    private PaChongService jdPaChongService;

    @Test
    public void test03() {
        jdPaChongService.grabSku();
    }

    @Test
    public void test04() {
        jdPaChongService.grabPrice();
    }

    @Autowired
    private ECGoodsMapper ecGoodsMapper;

    @Test
    public void test10() {
        List<String> list = ecGoodsMapper.selectBatchSKUListBySKUList(1, new ArrayList<String>() {{
            add("10000009132");
            add("10000009133");
            add("10000012856");
            add("10000053");
            add("10000067820");
            add("10000085201");
            add("10000085753");
            add("10000086061");
            add("10000091621");
            add("10000098471");
        }});
        System.out.println(list);
    }

    @Test
    public void test11() {

        Integer affects = ecGoodsMapper.insertBatch(new ArrayList<ECGoods>() {{
            add(new ECGoods() {{
                setGoodsId(new RandomGUID().toString());
            }});
            add(new ECGoods() {{
                setGoodsId(new RandomGUID().toString());
            }});
            add(new ECGoods() {{
                setGoodsId(new RandomGUID().toString());
            }});
        }});
        System.out.println("==================" + affects);
    }

    @Test
    public void test05() {
        List<String> la = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("g");
            }
        };
        System.out.println(JSON.toJSONString(la.subList(0, 5)));
        System.out.println(JSON.toJSONString(la.subList(2, 4)));
        List<String> lb = new ArrayList<String>() {
            {
                add("a");
                add("c");
            }
        };
        List<String> lc = new ArrayList<String>() {
            {
                add("d");
                add("a");
            }
        };
//        la.retainAll(lb);
        la.removeAll(lb);
        la.removeAll(lc);
//        la.addAll(lb);
        System.out.println(JSON.toJSONString(la));
        System.out.println(JSON.toJSONString(lb));
    }

}

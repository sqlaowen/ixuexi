package api.pay.combine.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.combine.service.PayNotifyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class PayNotifyServiceTest {

  @Autowired
  private PayNotifyService payNotifyService;
  
  //ali支付同步返回
  @Test
  public void test01(){
    Map<String, String> map=new HashMap<String,String>();
    map.put("body","huwei8650+");
    map.put("buyer_email","wenshiwei8888@163.com");
    map.put("buyer_id","2088402820936615");
    map.put("exterface","create_direct_pay_by_user");
    map.put("extra_common_param","{'id':1,'name':'wenshiwei'}");
    map.put("is_success","T");
    map.put("notify_id","RqPnCoPT3K9%2Fvwbh3InUFZeRajJ31ZvD6g8k06RoBmE%2FUFlUxFibiyTlNvoUfo4piX1t");
    map.put("notify_time","2016-01-19+14:13:19");
    map.put("notify_type","trade_status_sync");
    map.put("out_trade_no","326ae5e9dd304e489d4b6d9af5ce2abf");
    map.put("payment_type","1");
    map.put("seller_email","zhang9192@vip.qq.com");
    map.put("seller_id","2088211554961671");
    map.put("subject","手机");
    map.put("total_fee","0.01");
    map.put("trade_no","2016011900001000610064347438");
    map.put("trade_status","TRADE_SUCCESS");
    map.put("sign","cb3a85b3daa2e1f6b5a53447b73b7ba4");
    map.put("sign_type","MD5");
    String url= payNotifyService.aliReturn(map);
    System.out.println(url);
  }
}

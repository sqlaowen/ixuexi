package cn.ziran.xredis;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;

import com.tencent.common.WXHttpsRequest;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.ScanPayReqData;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.common.WXConfigure;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class PayTest {

  public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
    ScanPayReqData req= new ScanPayReqData();
    req.setAppid(WXConfigure.getAppid());
    req.setMch_id(WXConfigure.getMchid());
    req.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
    req.setBody("测试商品1");
    req.setTotal_fee(1);
    String orderid="test20160323109";
    req.setOut_trade_no(orderid);
    //req.setProduct_id(orderid);
    req.setSpbill_create_ip("127.0.0.1");
    req.setNotify_url("http://101.36.73.229:11007/paynotify/wx/async");
    req.setTrade_type("JSAPI");
    req.setOpenid("oaVwYwwqPUFvPhFbhyUjZkUY8AsI");
    String sign= Signature.getSign(Util.Class2Map(req));
    System.out.println(sign);
    req.setSign(sign);
    WXHttpsRequest request=new WXHttpsRequest();
    String res= request.sendPost(WXConfigure.PAY_API, req,false,"");
    System.out.println(res);
//    // 解决XStream对出现双下划线的bug
//    XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
//
//    // 将要提交给API的数据对象转换成XML格式数据Post给API
//    String xml = xStreamForRequestPostData.toXML(req);
//    System.out.println(xml);
//    
//    Map<String, Object> map=new HashMap<>();
//    Long l=System.currentTimeMillis();
//    map.put("timeStamp",l);
//    map.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
//    map.put("package", "prepay_id=wx20160324180839b3c789b77c0018059200");
//    map.put("signType", "MD5");
//    System.out.println(map);
//    String xs=Signature.getSign(map);
//    System.out.println(xs);
  }
  
}

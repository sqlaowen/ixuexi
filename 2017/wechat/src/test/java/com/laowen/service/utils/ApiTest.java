package com.laowen.service.com.laowen.utils;

import com.laowen.utils.HttpUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iyou on 2017/4/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class ApiTest {

    //CMIOT_API2005 －用户当月 －用户当月 GPRSGPRS 查
    //集团客户可查询所属物联卡当月截止到前一天 24 点为止的 GPRS使用 量（单位： KB)
    @Test
    public void test2_1_1() {
        String url = "http://183.230.96.66:8087/v2/gprsusedinfosingle";
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("appid", "MCWR0TN");
//        params.put("ebid", "0001000000012");
        String transid = "MCWR0TN" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "00000001";
        MessageDigest sha256Digest = DigestUtils.getSha256Digest();
        byte[] digest = sha256Digest.digest(("MCWR0TN"+"SZYCKJ"+transid).getBytes());
        String token = Hex.encodeHexString(digest);
        System.out.println("---------------token:" + token);
//        params.put("token", token);
//        params.put("msisdn", "1064803535019");
        url = String.format("http://183.230.96.66:8087/v2/gprsusedinfosingle?appid=MCWR0TN&transid=%s&ebid=0001000000012&token=%s&msisdn=1064803535019", transid, token);
        String rev = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", "application/www-form-urlencoded;charset=utf-8");
        }});
        System.out.println("---------------------------------------");
        System.out.println(rev);
    }
}

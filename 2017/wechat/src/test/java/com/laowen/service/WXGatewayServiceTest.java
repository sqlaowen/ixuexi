package com.laowen.service;

import com.laowen.utils.HttpUtil;
import com.laowen.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by iyou on 2017/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class WXGatewayServiceTest {

    //@Resource
    @Autowired
    private WXGatewayService wxGatewayService;

    @Test
    public void test01() {
        //Result<String> stringResult = wxGatewayService.getAccessToken("wx55321f628b4acc0a", "ab05782a1c4311e7abff705a0f1be2c6");
        Result<String> stringResult = wxGatewayService.getAccessToken("wxb8e1355bc321da3c", "ab05782a1c4311e7abff705a0f1be2c6");
        System.out.println(stringResult);
//        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s", stringResult.getT());
//        String menuStr = HttpUtil.sslPost(url, "GET", null, null);
//        System.out.println(menuStr);

    }

    @Test
    public void test02(){
        //测试
//        String params="{\"button\":[{\"name\":\"查询\",\"type\":\"view\",\"url\":\"https:\\/\\/open.weixin.qq.com\\/connect\\/oauth2\\/authorize?appid=wx55321f628b4acc0a&redirect_uri=http:\\/\\/wxlaowen.3w.net579.com/internet/oath2base?response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}]}";
//        Result<String> stringResult = wxGatewayService.getAccessToken("wx55321f628b4acc0a", "ab05782a1c4311e7abff705a0f1be2c6");
        //河南兆龙
        String params="{\"button\":[{\"name\":\"查询\",\"type\":\"view\",\"url\":\"https:\\/\\/open.weixin.qq.com\\/connect\\/oauth2\\/authorize?appid=wxb8e1355bc321da3c&redirect_uri=http:\\/\\/kehuyong1.36966.net/internet/oath2base?response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}]}";
        Result<String> stringResult = wxGatewayService.getAccessToken("wxb8e1355bc321da3c", "ab05782a1c4311e7abff705a0f1be2c6");

        System.out.println(stringResult);
        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", stringResult.getT());
        String menuStr = HttpUtil.sslPostString(url, "POST", params, null);
        System.out.println(menuStr);
    }
}

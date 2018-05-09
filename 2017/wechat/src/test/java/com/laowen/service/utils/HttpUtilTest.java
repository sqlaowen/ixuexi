package com.laowen.service.com.laowen.utils;

import com.laowen.utils.HttpUtil;
import com.laowen.wxmsg.ReceiveXmlMsg;
import com.laowen.wxmsg.ResolveReceiveXmlMsgUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.MessageDigest;
import java.util.HashMap;

/**
 * Created by iyou on 2017/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class HttpUtilTest {


    @Test
    public void x1() {
        System.out.println(Integer.valueOf("" + 11919474626L));
    }

    @Test
    public void test01() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", "wx55321f628b4acc0a", "c8dd71dc1eca577615cf8d0952314e1a");
        String result = HttpUtil.sslPost(url, "post", new HashMap<String, Object>() {{
            put("k1", "v1");
        }}, "utf-8");
        System.out.println(result);

    }

    @Test
    public void test02() {
        String xml = "<xml>\n" +
                "  <ToUserName><![CDATA[gh_d306845639cb]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[oFKMnwzhv83wK79L1rwfHbNniPkg]]></FromUserName>\n" +
                "  <CreateTime>1491705678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[/::)]]></Content>\n" +
                "  <MsgId>6406827102680901912</MsgId>\n" +
                "</xml>\n";
        xml = "<xml>\n" +
                "  <ToUserName><![CDATA[gh_d306845639cb]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[oFKMnwzhv83wK79L1rwfHbNniPkg]]></FromUserName>\n" +
                "  <CreateTime>1491666868</CreateTime>\n" +
                "  <MsgType><![CDATA[event]]></MsgType>\n" +
                "  <Event><![CDATA[subscribe]]></Event>\n" +
                "  <EventKey><![CDATA[]]></EventKey>\n" +
                "</xml>";
        xml = "<xml>\n" +
                "  <ToUserName><![CDATA[gh_d306845639cb]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[oFKMnwzhv83wK79L1rwfHbNniPkg]]></FromUserName>\n" +
                "  <CreateTime>1491666806</CreateTime>\n" +
                "  <MsgType><![CDATA[event]]></MsgType>\n" +
                "  <Event><![CDATA[unsubscribe]]></Event>\n" +
                "  <EventKey><![CDATA[]]></EventKey>\n" +
                "</xml>";
        ReceiveXmlMsg receiveXmlMsg = ResolveReceiveXmlMsgUtil.getMsgEntity(xml);
        System.out.println("-----------------------------------------");
        System.out.println(receiveXmlMsg);

    }

    @Test
    public void test03() {

        MessageDigest sha256Digest = DigestUtils.getSha256Digest();
        byte[] digest = sha256Digest.digest("wenshiwei".getBytes());
        System.out.println(Hex.encodeHexString(digest));

    }

    @Test
    public void test04() {
        String str = null;
        xx();
        System.out.println("==============================");
        System.out.println("true".equalsIgnoreCase(str));
    }

    void xx() {
        System.out.println("----------------------");
        throw new NoAllowExecuteException("测试异常....");
    }

    /**
     * 不允许执行
     */
    public class NoAllowExecuteException extends RuntimeException {
        public NoAllowExecuteException(String message) {
            super(message);
        }

        public NoAllowExecuteException(String message, Throwable cause) {
            super(message, cause);
        }

        public NoAllowExecuteException(Throwable cause) {
            super(cause);
        }
    }
}

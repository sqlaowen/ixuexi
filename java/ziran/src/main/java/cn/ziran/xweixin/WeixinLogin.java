package cn.ziran.xweixin;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.cookie.CookiePolicy;

public class WeixinLogin {

  static {
    Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
    Protocol.registerProtocol("https", myhttps);
  }

  public static void main(String[] args) throws Exception {

    System.setProperty("jsse.enableSNIExtension", "false");

    // 创造httpclient实例
    HttpClient client = new HttpClient();
    client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY); // 设置cookie管理策略
    client.getParams().setParameter("http.protocol.single-cookie-header", true);

    PostMethod post = new PostMethod();
    // 模拟浏览器
    post.setRequestHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22");
    // 这个必须设置 否则无法登录 还是尽量完全模拟浏览器的行为
    post.setRequestHeader("Referer", "https://mp.weixin.qq.com");



//    post.setURI(new URI("https://mp.weixin.qq.com/cgi-bin/verifycode?username=18911871350&r="
//        + System.currentTimeMillis(), true));
//    // post.setRequestHeader("Content-Type", "image/jpeg");
//    client.executeMethod(post);
//    // InputStream sm= post.getResponseBodyAsStream();
//    File storeFile = new File("d:/xx.jpg");
//    FileOutputStream output = new FileOutputStream(storeFile);
//    // 得到网络资源的字节数组,并写入文件
//    output.write(post.getResponseBody());
//    output.close();
//
//    post.releaseConnection();

    // System.out.println(Ocr1.getAllOcr("d:/xx.jpg"));

    // // 查看 cookie 信息
    // CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
    // Cookie[] cs=client.getState().getCookies();
    // System.out.println(cs);
    // Cookie[] cookies = cookiespec.match("mp.weixin.qq.com", 443, "/" , true ,
    // client.getState().getCookies());
    // if (cookies.length == 0) {
    // System.out.println( "None" );
    // } else {
    // for ( int i = 0; i < cookies.length; i++) {
    // System.out.println(cookies[i].toString());
    // }
    // }

    String imgcode = "rdlr";

    post.setURI(new URI("https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN", false));
    // 构造请求参数
    NameValuePair[] params = new NameValuePair[] {new NameValuePair("username","wxtz@zgxcw.com"),
        new NameValuePair("pwd", DigestUtils.md5Hex("Zgxcw1121".getBytes())),
        new NameValuePair("f", "json")
//        , new NameValuePair("imgcode", imgcode)
        };
    post.setQueryString(params);


    try {
      int aa = client.executeMethod(post);
      System.out.println(aa);
      System.out.println(post.getResponseBodyAsString());

      /*
       * //接着发一个get请求来确认是否登录成功。
       * 因为我的微信还没有验证，所有进入的是acct/realnamepage?action=showsubmit&step=3&lang=zh_CN页面 GetMethod get =
       * new GetMethod(); get.setURI(new
       * URI("https://mp.weixin.qq.com/acct/realnamepage?action=showsubmit&step=3&lang=zh_CN"));
       * client.executeMethod(get); System.out.println(get.getResponseBodyAsString());
       */
    } catch (Exception e) {
      System.out.println("异常:" + e.getMessage());
    }
  }
}

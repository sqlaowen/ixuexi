package com.x2016.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;

/**
 * java通过模拟post方式提交表单实现图片上传功能实例 其他文件类型可以传入 contentType 实现
 * 
 * @author zdz8207 {@link http://www.cnblogs.com/zdz8207/}
 * @version 1.0
 */
public class HttpUploadFileUtil {

  public static void main(String[] args) {
    testUploadImage();
  }

  /**
   * 测试上传png图片
   * 
   */
  public static void testUploadImage() {
    //String ak="TxWZ0Guh-jFB1ZyT1l1_-FNZqInL0OC15yumZhpuWkqK0Xn0d6NEVVmwd-D5lHLDudvWQp4j-g8WcmgTfatxi4b69GNx5wdYHJicTngaE2aP0p-j5oreJvmn8E0ghbuJROTcACANKP";
    String ak="bWCN3lzARRXvdNFIqfCL36Nbo5i23FLcqxxNp3PUWJ7vsQhyVUhAXDddBVg64QVSGhYm2QCDTZxKqdQUs7ToSjdxH7paLDbrKjw495reIpgieBuZeeUZxsYC-kmtSNTMZISaAFATCM";
    String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="
        + ak;
    //上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】
//    String ret = formUpload(url, "D:/sm.jpg");
//    System.out.println(ret);
//    String u2="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+ak+"&type=image";
//    String r2=formUpload(u2, "D:/sm.jpg");//上传临时素材获取media-id
//    System.out.println("r2\r\n");
//    System.out.println(r2);
//    String r3=formUpload(u2, "D:/jh.jpg");//上传临时素材获取media-id
//    System.out.println("r3\r\n");
//    System.out.println(r3);
    //上传消息素材
    String news="";
    news+="{";
    news+="\"articles\": [";
    news+="{";
//    news+="\"thumb_media_id\":\"wvstDIjn0HhmOid_C-WzHeh-6T_I20KWDnDWGsdYp3oZpJG8OtL02Zv1zIW1YlDv\",";
    news+="\"thumb_media_id\":\"sEVg2dZIVvYhd-OzHf26V6hE6EiL7vXnW_NI77mee95trvuMunGzh4P2gs7FPPHk\",";
    news+="\"author\":\"作者001\",";
    news+="\"title\":\"标题1\",";
    news+="\"content_source_url\":\"http://www.baidu.com\",";
    news+="\"content\":\"\"";
    news+="\"digest\":\"这里是描述001\",";
    news+="\"show_cover_pic\":1";
    news+="},";
    news+="{";
    //news+="\"thumb_media_id\":\"QaxdWynajLvBaaNDWyt8T0PVBG6m1s63Vs0Hhp4mDPIfiow1Zqu4yi8QfUKrtveG\",";
    news+="\"thumb_media_id\":\"TBUCPVm-E-ga8L7QcsxMyQeWgWEs2hhapxhEJ4KMVWJ-XI3IjxJQKVYYPjGpshKy\",";
    news+="\"author\":\"作者002\",";
    news+="\"title\":\"标题2\",";
    news+="\"content_source_url\":http://\"www.qq.com\",";
    news+="\"content\":\"<section id=\"1_609\" class=\"yead_editor\" onmousedown=\"shifuMouseDown(&#39;1_609&#39;)\" label=\"Copyright © 2015 Yead All Rights Reserved.\" style=\"font-size:14px;font-family:&#39;Microsoft YaHei&#39;;margin: 5px auto;white-space: normal;\"><section class=\"yead_bgc\" style=\"position:static;box-sizing:border-box;padding:0 10px;background-color:#ff391f\"><section style=\"margin:0;padding:0;text-align:center\"><svg style=\"height:2em\" data-width=\"100%\"><text y=\"15.197%\" x=\"50%\" fill=\"#ffffff\" style=\"font-size:16px;text-anchor:middle;box-sizing:border-box\"><tspan>带背景向下滚动标题，抓不住可在HTML里替换</tspan><animate attributename=\"y\" from=\"0%\" to=\"150%\" begin=\"0s\" dur=\"5s\" repeatcount=\"indefinite\"></animate></text></svg></section></section></section>\"";
    news+="\"digest\":\"一样的描述002\",";
    news+="\"show_cover_pic\":0";
    news+="}";
    news+="]";
    news+="}";
    NetWorkHelper nh=new NetWorkHelper();
    String u="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
        +ak;
//    String r1= nh.getHttpsResponse(u, "POST", news);//上传图文素材
//    System.out.println(r1);
    //群发消息
    String qu="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+ak;
    news="";
    news+="{";
//    news+="\"filter\":{";
//    //news+="\"is_to_all\":false,";
//    news+="\"group_id\":0";
//    news+="},";
    news+="\"touser\":[\"oFKMnw5VT2pnY40nJOL_4jVqP268\",\"oFKMnw0xJVc2ouHfpUZ4KZOjjzkI\"],";
    news+="\"mpnews\":{";
    //news+="\"media_id\":\"t59o9Mv_cpbz32z7amiQQgMDUwqtI9Tx4ZOsZi9LNT9UIuDKEqx9yRa_o-vVq8ts\"";
    news+="\"media_id\":\"AYstKSA5pwrXLSJXK7_oWRko92NK_q7FY-645GU4p7oIZOFFEwT1qEXXUIr9QRp8\"";
    news+="},";
    news+="\"msgtype\":\"mpnews\"";
    news+="}";
    for(int i=0;i<10000;i++){
    String qr2= nh.getHttpsResponse(qu, "POST",news);
    System.out.println(qr2);
    }
    
    //查询分组
//    String fu="https://api.weixin.qq.com/cgi-bin/groups/get?access_token="+ak;
//    String fr=nh.getHttpsResponse(fu, "POST",null);
//    System.out.println(fr);
  }

  /**
   * 上传图片
   * 
   * @param urlStr
   * @param textMap
   * @param fileMap
   * @param contentType 没有传入文件类型默认采用application/octet-stream contentType非空采用filename匹配默认的图片类型
   * @return 返回response数据
   */
  public static String formUpload(String urlStr, String filePath) {
    String res = null;
    HttpURLConnection conn = null;
    // boundary就是request头和上传文件内容的分隔符
    String BOUNDARY = "---------------------------" + System.currentTimeMillis();
    try {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection) url.openConnection();
      conn.setConnectTimeout(5000);
      conn.setReadTimeout(30000);
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Connection", "Keep-Alive");
      conn.setRequestProperty("Content-Type", String.format("multipart/form-data; boundary=%s",BOUNDARY));
      OutputStream out = new DataOutputStream(conn.getOutputStream());
      out.write(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", System.currentTimeMillis()).getBytes());
      // file
      if (StringUtils.isNotBlank(filePath)) {
        File file = new File(filePath);
        String filename = file.getName();
        // 获取不到类型，默认采用application/octet-stream
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        if (StringUtils.isBlank(contentType)) {
          contentType = "application/octet-stream";
        }
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
        strBuf.append(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n",System.currentTimeMillis(), filename));
        strBuf.append(String.format("Content-Type:%s\r\n\r\n", contentType));
        out.write(strBuf.toString().getBytes());
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
          out.write(bufferOut, 0, bytes);
        }
        if(null !=in){
          in.close();
        }
      }
      byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
      out.write(endData);
      out.flush();
      if (null != out){
        out.close();
      }
      // 读取返回数据
      StringBuffer strBuf = new StringBuffer();
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line = null;
      while ((line = reader.readLine()) != null) {
        strBuf.append(line).append("\n");
      }
      res = strBuf.toString();
      if (null != reader){
        reader.close();
      }
    } catch (Exception e) {
      System.out.println("发送POST请求出错。" + urlStr);
      e.printStackTrace();
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
    return res;
  }
}

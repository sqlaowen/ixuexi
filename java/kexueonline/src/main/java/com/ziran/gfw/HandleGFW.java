package com.ziran.gfw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleGFW {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  private ConfPojo confPojo;
  public void setConfPojo(ConfPojo confPojo) {
    this.confPojo = confPojo;
  }

  // 开始进程
  public void startProcess() {
    log.debug("---- 启动Shadowsocks进程 start...");
    Runtime rt = Runtime.getRuntime();
    try {
      rt.exec(confPojo.getStartProcess());
    } catch (IOException e) {
      log.error("启动进程失败：" + e.getMessage());
    }
    log.debug("---- 启动Shadowsocks进程 end...");
  }

  // 结束进程
  public void killProcess() {
    log.debug("---- 杀死Shadowsocks进程 start...");
    Runtime rt = Runtime.getRuntime();
    try {
      Process p = rt.exec(confPojo.getStopProcess());
      InputStream inputStream = p.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
      StringBuffer sb = new StringBuffer();
      String str = "";
      while ((str = br.readLine()) != null) {
        sb.append(str + "\n");
      }
      log.debug(sb.toString().trim());
    } catch (IOException e) {
      log.error("杀死进程失败：" + e.getMessage());
    }
    log.debug("---- 杀死Shadowsocks进程 end...");
  }

  // 初始化配制 gui-config.json
  public void initConfig() {
    log.debug("---- http请求配制初始化参数 start...");

    HttpMethodBase method = new GetMethod("http://www.ishadowsocks.com/");
    method.setRequestHeader("Content-Type", "text/html;charset=utf-8");
    HttpClient client = new HttpClient();
    int status = 0;
    try {
      status = client.executeMethod(method);
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String html = "";
    if (status != 200) {
      log.error("http请求失败，当前状态：" + status);
    } else {
      try {
        InputStream resStream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(resStream, "UTF-8"));
        StringBuffer resBuffer = new StringBuffer();
        String resTemp = "";
        while ((resTemp = br.readLine()) != null) {
          resBuffer.append(resTemp);
        }
        html = resBuffer.toString();

        if (br != null)
          br.close();
        if (resStream != null)
          resStream.close();
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        method.releaseConnection();
      }
    }
    System.out.println(html);
    // 解析html
    Document doc = Jsoup.parse(html);
    Elements elements = doc.getElementById("free").getElementsByClass("row").last().children();
    String _config = "";
    for (Element e : elements) {
      String server = "", serverPort = "", password = "", deal = "",zht="";
      try {
        // 主机
        server = e.getElementsByTag("h4").get(0).html().split(":")[1].trim();
        // 端口
        serverPort = e.getElementsByTag("h4").get(1).html().split(":")[1].trim();
        // 密钥
        password = e.getElementsByTag("h4").get(2).html().split(":")[1].trim();
        // 协议/加密方式
        deal = e.getElementsByTag("h4").get(3).html().split(":")[1].trim().toLowerCase();
        //状态
        zht= e.getElementsByTag("h4").get(4).getElementsByTag("font").text().trim();
      } catch (Exception ex) {
        log.error("账号信息解析错误:{}", ex.getMessage());
        continue;
      }
      if(null==zht||"".equals(zht.trim())||!"正常".equals(zht.trim())){
        log.info("状态异常,对应账号[主机:{},端口:{},密钥:{},协议:{},状态:{}]",server,serverPort,password,deal,zht);
        continue;
      }
      // 备注
      String remarks = "";
      if (_config.equals("")) {
        _config += " {\r\n";
        _config += " \"server\":\"" + server + "\",\r\n";
        _config += " \"server_port\":\"" + serverPort + "\",\r\n";
        _config += " \"password\":\"" + password + "\",\r\n";
        _config += " \"method\":\"" + deal + "\",\r\n";
        _config += " \"remarks\":\"" + remarks + "\"\r\n";
        _config += " }\r\n";
      } else {
        _config += " ,{\r\n";
        _config += " \"server\":\"" + server + "\",\r\n";
        _config += " \"server_port\":\"" + serverPort + "\",\r\n";
        _config += " \"password\":\"" + password + "\",\r\n";
        _config += " \"method\":\"" + deal + "\",\r\n";
        _config += " \"remarks\":\"" + remarks + "\"\r\n";
        _config += " }\r\n";
      }
      log.debug("主机：" + server);
      log.debug("端口：" + serverPort);
      log.debug("密钥：" + password);
      log.debug("协议：" + deal);
      log.debug("-----------------------");
    }

    // 生成配制文件
    File file = new File(confPojo.getGuiConfig());
    try {
      file.createNewFile();
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      String config = "";
      config = "{\r\n";
      config += " \"configs\": [\r\n";
      config += _config;
      config += " ],\r\n";
      config += " \"strategy\": \"com.shadowsocks.strategy.ha\",\r\n";
      config += " \"index\": -1,\r\n";
      config += " \"global\": false,\r\n";
      config += " \"enabled\": true,\r\n";
      config += " \"shareOverLan\": false,\r\n";
      config += " \"isDefault\": false,\r\n";
      config += " \"localPort\": 1080,\r\n";
      config += " \"pacUrl\": null,\r\n";
      config += " \"useOnlinePac\": false,\r\n";
      config += " \"availabilityStatistics\": false\r\n";
      config += "}";
      log.info("配置文件:{}",config);
      out.write(config);
      out.flush();
      out.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    log.debug("---- http请求配制初始化参数 end...");
  }
}

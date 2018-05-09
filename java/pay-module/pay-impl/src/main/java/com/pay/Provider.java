package com.pay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动 Created by suntao on 15/8/9.
 */
public class Provider {
  private static final Logger logger = LoggerFactory.getLogger(Provider.class);

  public static void main(String[] args) throws Exception {
    startService();
    logger.debug("服务已启动，输入stop结束...");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    do {
      String input = "";
      if (br.ready()) {
        input = br.readLine();
      }
      if (input.equals("stop")) {
        break;
      }
    } while (br != null);
    br.close();
  }

  @SuppressWarnings("resource")
  static void startService() {
    new ClassPathXmlApplicationContext("notenv/applicationContext.xml", "notenv/provider.xml");
  }
}

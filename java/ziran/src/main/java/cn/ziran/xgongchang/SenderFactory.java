package cn.ziran.xgongchang;

import org.apache.commons.lang.StringUtils;

//普通工厂
public class SenderFactory {

  public Sender producer(String t) {
    if (StringUtils.equalsIgnoreCase(t, "sms")) {
      return new SMSSender();
    } else if (StringUtils.equalsIgnoreCase(t, "mail")) {
      return new MailSender();
    }
    System.out.println("未知type, 失败...");
    return null;
  }
}

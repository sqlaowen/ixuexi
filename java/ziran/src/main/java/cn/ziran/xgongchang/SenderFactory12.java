package cn.ziran.xgongchang;

//普通工厂
public class SenderFactory12 {

  public static Sender productSMS() {
      return new SMSSender();
  }
  
  public static Sender productMail() {
      return new MailSender();
  }
  
}

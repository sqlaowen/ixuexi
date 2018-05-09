package cn.ziran.xgongchang;

//普通工厂
public class SenderFactory11 {

  public Sender productSMS() {
      return new SMSSender();
  }
  
  public Sender productMail() {
      return new MailSender();
  }
  
}

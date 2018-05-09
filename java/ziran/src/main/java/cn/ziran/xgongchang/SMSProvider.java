package cn.ziran.xgongchang;

public class SMSProvider implements GongChProvider {

  @Override
  public Sender produce() {
    return new SMSSender();
  }

}

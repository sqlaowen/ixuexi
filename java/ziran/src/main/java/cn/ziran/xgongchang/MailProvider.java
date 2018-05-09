package cn.ziran.xgongchang;

public class MailProvider implements GongChProvider {

  @Override
  public Sender produce() {
    return new MailSender();
  }

}

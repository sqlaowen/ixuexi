package cn.ziran.xgongchang;

public class GongChTest {

  public static void main(String[] args) {
    GongChTest t = new GongChTest();
    // t.test01();
    // t.test02();
    // t.test03();
    t.test04();
  }

  // 1.普通工厂
  public void test01() {
    SenderFactory factory = new SenderFactory();
    Sender sender = factory.producer("sms");
    sender.send();
  }

  // 11.多工厂方法
  public void test02() {
    SenderFactory11 factory = new SenderFactory11();
    Sender sender = factory.productMail();
    sender.send();
  }

  // 12.多工厂方法
  public void test03() {
    Sender sender = SenderFactory12.productMail();
    sender.send();
  }

  // 2.抽象工厂
  public void test04() {
    GongChProvider p = new MailProvider();
    Sender sender = p.produce();
    sender.send();
  }
}

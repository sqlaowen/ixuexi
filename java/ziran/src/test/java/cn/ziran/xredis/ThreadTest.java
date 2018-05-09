package cn.ziran.xredis;

public class ThreadTest {

  public static void main(String[] args) {
    Thread t = new Thread() {
      public void run() {
        pong();
      }
    };
    t=new Thread(new Runnable() {
      @Override
      public void run() {
        pong();
      }
    });
    t.run();
    //t.start();
    System.out.print("ping");
  }

  static void pong() {

    System.out.print("pong");

  }
}

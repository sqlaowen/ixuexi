package cn.ziran.xguanchazhe;

public class ConcreteSubject extends XSubject {

  private String state;
  public String getState() {
      return state;
  }

  public void change(String newState){
      state = newState;
      System.out.println("变更前主题状态为：" + state);
      //状态发生改变，通知各个观察者
      this.nodifyObservers(state);
  }
}

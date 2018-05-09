package cn.ziran.xguanchazhe;

import java.util.ArrayList;
import java.util.List;

public abstract class XSubject {

  /**
   * 用来保存注册的观察者对象
   */
  private List<XObserver> list = new ArrayList<XObserver>();

  /**
   * 注册观察者对象
   * 
   * @param observer 观察者对象
   */
  public void attach(XObserver observer) {

    list.add(observer);
    System.out.println("Attached an observer");
  }

  /**
   * 删除观察者对象
   * 
   * @param observer 观察者对象
   */
  public void detach(XObserver observer) {

    list.remove(observer);
  }

  /**
   * 通知所有注册的观察者对象
   */
  public void nodifyObservers(String newState) {

    for (XObserver observer : list) {
      observer.update(newState);
    }
  }
}

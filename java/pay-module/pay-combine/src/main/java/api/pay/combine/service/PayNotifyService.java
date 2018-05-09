package api.pay.combine.service;

import java.util.Map;

public interface PayNotifyService {

  /**
   * ali同步通知
   * @param map Map<String, String>
   * 
   * @return String 返回即将跳转到的url
   */
  public String aliReturn(Map<String, String> map);
  
  /**
   * ali异步通知
   * @param map Map<String, String>
   * 
   * @return boolean true:成功,false:失败
   */
  public boolean aliNotify(Map<String, String> map);
}

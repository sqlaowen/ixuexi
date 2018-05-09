package com.zgxcw.service.constants;

/**
 * 微信事件类型
 * @author wensw
 *
 */
public final class WXEventTypeConstant {

  /**
   * 关注事件/扫描带参数二维码事件(未关注时)
   */
  public static final String EVENTTYPE_SUBSCRIBE="SUBSCRIBE";
  
  /**
   * 取消关注事件
   */
  public static final String EVENTTYPE_UNSUBSCRIBE="UNSUBSCRIBE";
  
  /**
   * 扫描带参数二维码事件(已关注时)
   */
  public static final String EVENTTYPE_SCAN="SCAN";
  
  /**
   * 上报地理位置事件
   */
  public static final String EVENTTYPE_LOCATION="LOCATION";
  
  /**
   * 点击菜单拉取消息时的事件推送
   */
  public static final String EVENTTYPE_CLICK="CLICK";
  
  /**
   * 点击菜单跳转链接时的事件推送
   */
  public static final String EVENTTYPE_VIEW="VIEW";
  
}

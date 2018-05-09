package cn.ziran.xutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class XContextUtil {
  /**
   * spring mvc 取上下文request
   * 
   * @return HttpServletRequest
   */
  public static HttpServletRequest getRequest() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request;
  }
  
  /**
   * spring mvc 取上下文session
   * 
   * @return HttpSession
   */
  public static HttpSession getSession() {
    HttpSession session = getRequest().getSession();
    return session;

  }
}

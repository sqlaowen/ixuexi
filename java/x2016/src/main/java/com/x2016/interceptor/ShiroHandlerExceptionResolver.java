package com.x2016.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ShiroHandlerExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    String isAjaxReq = request.getHeader("X-Requested-With");
    if (ex instanceof AuthorizationException) {
      if ("XMLHttpRequest".equals(isAjaxReq)) {
        response.setStatus(403);// 未授权 主要用于ajax请求返回
        response.setContentType("text/html;charset=utf-8");
        return new ModelAndView();
      }
      return new ModelAndView("redirect:/error/403.html");
    }
    return null;
  }

}

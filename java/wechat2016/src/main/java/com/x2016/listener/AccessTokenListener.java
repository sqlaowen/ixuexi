package com.x2016.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.x2016.util.AccessTokenThread;


public class AccessTokenListener implements ServletContextListener {
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    //初始化AccessToken
    new Thread(new AccessTokenThread()).start();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    
  }

}

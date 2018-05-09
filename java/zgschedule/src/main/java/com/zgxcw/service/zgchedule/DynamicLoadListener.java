package com.zgxcw.service.zgchedule;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.catalina.loader.WebappClassLoader;

public class DynamicLoadListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    addJar(context);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
   
  }

  synchronized private void addJar(ServletContext context) {

    String spt = File.separator;// 路径分割符
    String extlibs = context.getRealPath("/") + spt + "jar";

    File jarDir = new File(extlibs);
    if(!jarDir.exists())
      jarDir.mkdirs();
    
    File[] jarFiles = jarDir.listFiles(new JarFileNameFilter());
    
    WebappClassLoader loader = (WebappClassLoader) getClass().getClassLoader();
    for (File jarFile : jarFiles) {
      try {
        loader.addRepository(jarFile.toURI().toURL().toString());
      } catch (MalformedURLException e) {
        System.out.println("----加载失败："+e.getMessage());
        e.printStackTrace();
      }
    }
    
  }

  class JarFileNameFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      return name.toLowerCase().endsWith(".jar");
    }
  }

}

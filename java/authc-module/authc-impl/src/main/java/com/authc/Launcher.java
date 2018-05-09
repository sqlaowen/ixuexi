package com.authc;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作为liunx守护进程入口 Created by suntao on 15/8/9.
 */
public class Launcher implements Daemon {

  private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

  @Override
  public void init(DaemonContext daemonContext) throws DaemonInitException, Exception {
    logger.info("init");
  }

  @Override
  public void start() throws Exception {
    logger.info("services started");
    Provider.startService();
  }

  @Override
  public void stop() throws Exception {
    logger.info("stop");
  }

  @Override
  public void destroy() {
    logger.info("destroy");
  }
}

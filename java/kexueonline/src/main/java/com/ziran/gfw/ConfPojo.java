package com.ziran.gfw;

import java.io.Serializable;

public class ConfPojo implements Serializable {

  private static final long serialVersionUID = -4430119054078080966L;
  private volatile String shadowPath;// Shadowsocks路径
  private volatile String guiConfig;// gui配置文件路径
  private volatile String cronExpression;// cron表达式
  private volatile String startProcess;// windows/linux开启进程命令
  private volatile String stopProcess;// windows/linux结束进程命令

  /**
   * Shadowsocks路径
   * 
   * @return
   */
  public String getShadowPath() {
    return shadowPath;
  }

  /**
   * Shadowsocks路径
   * 
   * @param shadowPath
   */
  public void setShadowPath(String shadowPath) {
    this.shadowPath = shadowPath;
  }

  /**
   * gui配置文件路径
   * 
   * @return
   */
  public String getGuiConfig() {
    return guiConfig;
  }

  /**
   * gui配置文件路径
   * 
   * @param shadowConfig
   */
  public void setGuiConfig(String guiConfig) {
    this.guiConfig = guiConfig;
  }

  /**
   * cron表达式
   * 
   * @return
   */
  public String getCronExpression() {
    return cronExpression;
  }

  /**
   * cron表达式
   * 
   * @param cronExpression
   */
  public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
  }

  /**
   * windows/linux开启进程命令
   * 
   * @return
   */
  public String getStartProcess() {
    return startProcess;
  }

  /**
   * windows/linux开启进程命令
   * 
   * @param startProcess
   */
  public void setStartProcess(String startProcess) {
    this.startProcess = startProcess;
  }

  /**
   * windows/linux结束进程命令
   * 
   * @return
   */
  public String getStopProcess() {
    return stopProcess;
  }

  /**
   * windows/linux结束进程命令
   * 
   * @param stopProcess
   */
  public void setStopProcess(String stopProcess) {
    this.stopProcess = stopProcess;
  }
}

package com.ziran.gfw;

public class GFWJob {

  private HandleGFW handleGFW;
  public void setHandleGFW(HandleGFW handleGFW) {
    this.handleGFW = handleGFW;
  }

  public void execute() {
    handleGFW.killProcess();
    handleGFW.initConfig();
    handleGFW.startProcess();
  }

}


//public class GFWJob implements Job {
//
//  private HandleGFW handleGFW;
//  public void setHandleGFW(HandleGFW handleGFW) {
//    this.handleGFW = handleGFW;
//  }
//  
//  public void execute(JobExecutionContext context) throws JobExecutionException {
//    handleGFW.killProcess();
//    handleGFW.initConfig();
//    handleGFW.startProcess();
//  }

package com.ziran.gfw;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GFWScheduler {

  private static Logger log = LoggerFactory.getLogger(GFWScheduler.class);

  @SuppressWarnings("resource")
  public static void main(String[] args) throws IOException {
    AbstractApplicationContext ctxt = new ClassPathXmlApplicationContext("notenv/applicationContext.xml");
    log.info("服务已启动...");
    HandleGFW handleGFW = (HandleGFW) ctxt.getBean("handleGFW");
    // 启动时初始化一次配制
    handleGFW.killProcess();
    handleGFW.initConfig();
    handleGFW.startProcess();
  }

  // 以下为代码实现计划任务
  // private static AbstractApplicationContext ctxt;
  // public static void main(String[] args) throws IOException {
  // ctxt = new ClassPathXmlApplicationContext("notenv/applicationContext.xml");
  //
  // GFWScheduler gfwScheduler=new GFWScheduler();
  // gfwScheduler.makeTrigger();
  //
  // }
  // private HandleGFW handleGFW;
  // private ConfPojo confPojo;
  // private GFWJob gfwJob;
  //
  // public void setGfwJob(GFWJob gfwJob) {
  // this.gfwJob = gfwJob;
  // }
  //
  // public void setHandleGFW(HandleGFW handleGFW) {
  // this.handleGFW = handleGFW;
  // }
  //
  // public void setConfPojo(ConfPojo confPojo) {
  // this.confPojo = confPojo;
  // }

  // public void makeTrigger() {
  // //HandleGFW handleGFW=(HandleGFW)ctxt.getBean("handleGFW");
  // // 启动时初始化一次配制
  // handleGFW.killProcess();
  // handleGFW.initConfig();
  // handleGFW.startProcess();
  //
  // SchedulerFactory factory = new StdSchedulerFactory();
  // try {
  // // 获取调度器
  // Scheduler scheduler = factory.getScheduler();
  // JobDetail job = JobBuilder.newJob(gfwJob.getClass()).withIdentity("GFW_Job",
  // "x2015_Job").build();
  // // Trigger trigger = TriggerBuilder.newTrigger().withIdentity("GFW_Triger", "x2015_Triger")
  // // .withSchedule(SimpleScheduleBuilder.repeatHourlyForever().withRepeatCount(8)).startNow()
  // // .build();
  //
  // // 5 0 0-23 * * ? *
  // //ConfPojo confPojo=(ConfPojo)ctxt.getBean("confPojo");
  // Trigger trigger = TriggerBuilder.newTrigger().withIdentity("GFW_Triger", "x2015_Triger")
  // .withSchedule(CronScheduleBuilder.cronSchedule(confPojo.getCronExpression())).startNow()
  // .build();
  // // 把作业和触发器注册到任务调度中
  // scheduler.scheduleJob(job, trigger);
  // // 启动调度
  // scheduler.start();
  //
  // } catch (SchedulerException e) {
  // e.printStackTrace();
  // }
  // }
}
